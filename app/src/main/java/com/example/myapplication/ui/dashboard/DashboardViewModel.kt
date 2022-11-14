package com.example.myapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trydosomething.ui.home.adapters.WetherModel

class DashboardViewModel : ViewModel() {

    val liveDataCurrent2 = MutableLiveData<WeatherModelMoreDetalis>()
    val liveDataList2 = MutableLiveData<List<WeatherModelMoreDetalis>>()
}