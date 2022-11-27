package com.example.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHoursBinding
import com.example.myapplication.ui.home.adapters.WeatherAdapter
import com.example.myapplication.ui.home.adapters.WetherModel

import org.json.JSONArray
import org.json.JSONObject

class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model: HomeViewModel by activityViewModels()
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoursBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        model.liveDataCurrent.observe(viewLifecycleOwner){
            adapter.submitList(getHoursList(it))
        }

    }
    private fun initRcView() = with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        rcView.adapter = adapter

    }

    private fun getHoursList(WItem: WetherModel): List<WetherModel>{
        val hoursArray = JSONArray(WItem.hours)
        val list = ArrayList<WetherModel>()
        for (i in 0 until hoursArray.length()){
            val item = WetherModel(
                WItem.city,
                (hoursArray[i] as JSONObject).getString("time"),
                (hoursArray[i] as JSONObject).getJSONObject("condition")
                    .getString("text"),
                (hoursArray[i] as JSONObject).getString("temp_c").toFloat().toInt().toString(),
                "",
                "",
                (hoursArray[i] as JSONObject).getJSONObject("condition")
                    .getString("icon"),
                ""
            )
            list.add(item)
        }
        return list
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
    }
