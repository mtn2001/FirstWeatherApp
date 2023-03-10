package com.example.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentDaysBinding
import com.example.myapplication.ui.home.adapters.WeatherAdapter
import com.example.myapplication.ui.home.adapters.WetherModel


class DaysFragment : Fragment(), WeatherAdapter.Listener {
    private lateinit var adapter: WeatherAdapter
    private lateinit var binding: FragmentDaysBinding
    private val model: HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDaysBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.liveDataList.observe(viewLifecycleOwner){
            adapter.submitList(it.subList(1 , it.size))
        }
    }
    private fun init() = with(binding){
        adapter = WeatherAdapter(this@DaysFragment)
        rcView2.layoutManager = LinearLayoutManager(activity)
        rcView2.adapter = adapter
    }

    companion object {

        fun newInstance() = DaysFragment()
    }

    override fun onClick(item: WetherModel) {
        model.liveDataCurrent.value = item
    }
}