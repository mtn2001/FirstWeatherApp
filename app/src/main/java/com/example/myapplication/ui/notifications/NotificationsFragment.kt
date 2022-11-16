package com.example.myapplication.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvAdapter: RvAdapter
    private lateinit var city : List<CityModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
          _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root


    }

    private fun loadLanguage() {
        city = listOf(
            CityModel("Java" , "Exp : 3 years"),
            CityModel("Kotlin" , "Exp : 2 years"),
            CityModel("Python" , "Exp : 4 years"),
            CityModel("JavaScript" , "Exp : 6 years"),
            CityModel("PHP" , "Exp : 1 years"),
            CityModel("CPP" , "Exp : 8 years"),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}