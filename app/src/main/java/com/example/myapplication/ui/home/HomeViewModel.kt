package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trydosomething.ui.home.adapters.WetherModel

class HomeViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<WetherModel>()
    val liveDataList = MutableLiveData<List<WetherModel>>()
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}