package com.example.myapplication.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    val liveDataCurrent2 = MutableLiveData<WeatherModelMoreDetalis>()
    val liveDataList2 = MutableLiveData<List<WeatherModelMoreDetalis>>()
}