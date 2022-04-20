package com.ivankuznetsov.kjaaero

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArrivalFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allArrivalFlight = MutableLiveData<MutableList<FlightData>>()

    init { getAllArrival(R.string.app_name) }

    fun getAllArrival(dayUrl: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repository = KJARepository(dayUrl)
                val x = repository.getArrival()
                allArrivalFlight.postValue(x)
            }
            catch (e: Exception){  }
        }
    }
}