package com.example.myapplication.ui.dashboard

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.dialogManager
import com.example.myapplication.ui.home.API_KEY
import com.example.myapplication.ui.home.isPermissionGranted
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import org.json.JSONObject


class DashboardFragment : Fragment() {
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var fLocationClient: FusedLocationProviderClient
    private val model: DashboardViewModel by activityViewModels()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        updateCurrentCard()
        init()
    }
    private fun init() = with(binding) {
        fLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

    }
    override fun onResume() {
        super.onResume()
        checkLocation()
    }
    private fun isLocationEnabled(): Boolean{
        val lm = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun getLocation() {
        val ct = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, ct.token)
            .addOnCompleteListener {
                reqvestWeatherData("${it.result.latitude},${it.result.longitude}")
            }
    }
    private fun checkLocation(){
        if (isLocationEnabled()){
            getLocation()
        }else{
            dialogManager.locationSettingsDialog(requireContext(),object : dialogManager.Listener{
                override fun onClick(name: String?) {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
            })
        }
    }
    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }
    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
    private fun updateCurrentCard() = with(binding) {
        model.liveDataCurrent2.observe(viewLifecycleOwner) {
            textView12.text = it.sunrise
            textView13.text = it.sunset
            textView14.text = it.moonrise
            textView15.text = it.moonset
            textView17.text = it.moon_phase+" km/h"
            textView18.text = it.moon_illumination+" %"
            textView16.text = it.avghumidity+" %"
        }
    }
    private fun reqvestWeatherData(city: String) {
        val url = "https://api.weatherapi.com/v1/forecast.json?key=" +
                API_KEY +
                "&q=" +
                city +
                "&days=" +
                "10" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { result ->
                parseWeatherData(result)

            },
            {
                Log.d("MyLog", "Volley error: $it")
            }
        )
        queue.add(stringRequest)
    }
    private fun parseWeatherData(result: String) {
        val mainObject = JSONObject(result)
        val list = parseDays(mainObject)
        parseCurrentData(mainObject, list[0])

    }

    private fun parseDays(mainObject: JSONObject): List<WeatherModelMoreDetalis> {

        val list = ArrayList<WeatherModelMoreDetalis>()
        val daysArray = mainObject.getJSONObject("forecast")
            .getJSONArray("forecastday")
        for (i in 0 until daysArray.length()) {
            val day = daysArray[i] as JSONObject
            val item = WeatherModelMoreDetalis(
                day.getJSONObject("astro").getString("sunrise"),
                day.getJSONObject("astro").getString("sunset"),
                day.getJSONObject("astro").getString("moonrise"),
                day.getJSONObject("astro").getString("moonset"),
               day.getJSONObject("astro").getString("moon_phase"),
                day.getJSONObject("astro").getString("moon_illumination"),
                ""
            )
            list.add(item)
        }
        model.liveDataList2.value = list
        return list
    }

    private fun parseCurrentData(mainObject: JSONObject, weatherItem: WeatherModelMoreDetalis){

        val daysArray = mainObject.getJSONObject("forecast")
            .getJSONArray("forecastday")
        val day = daysArray[0] as JSONObject
        val item = WeatherModelMoreDetalis(
            day.getJSONObject("astro").getString("sunrise"),
            day.getJSONObject("astro").getString("sunset"),
            day.getJSONObject("astro").getString("moonrise"),
            day.getJSONObject("astro").getString("moonset"),
            day.getJSONObject("day").getString("avgvis_km"),
            day.getJSONObject("day").getString("daily_chance_of_rain"),
            day.getJSONObject("day").getString("avghumidity"),
        )
        model.liveDataCurrent2.value = item
    }
    }