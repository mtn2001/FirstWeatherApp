package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentDashboardBinding



class DashboardFragment : Fragment() {
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
        updateCurrentCard()
    }
    private fun updateCurrentCard() = with(binding) {
        model.liveDataCurrent2.observe(viewLifecycleOwner) {
            val maxMinTemp = "${it.maxTemp}°/${it.minTemp}"
            textView12.text = it.time
            textView13.text = it.city
            textView14.text = it.currentTemp.ifEmpty { maxMinTemp }+"°"
            textView15.text = it.condition
            textView16.text = if (it.currentTemp.isEmpty()) "" else maxMinTemp
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}