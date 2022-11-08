package com.example.myapplication.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trydosomething.ui.home.adapters.WetherModel

class HomeViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<WetherModel>()
    val liveDataList = MutableLiveData<List<WetherModel>>()

}
