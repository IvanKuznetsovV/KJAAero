package com.ivankuznetsov.kjaaero
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val allArrivalFlight = MutableLiveData<MutableList<FlightData>>()
    val allDepartFlight  = MutableLiveData<MutableList<FlightData>>()
    var day: String? = null

    fun getAllArrival() {
        viewModelScope.launch(Dispatchers.IO) {
            val repository = UserRepository(day)
            val x = repository.getArrival()
            allArrivalFlight.postValue(x)
        }
     }

     fun getAllDepart(){
        viewModelScope.launch(Dispatchers.IO) {
            val repository = UserRepository(day)
            val x = repository.getDepart()
            allDepartFlight.postValue(x)
        }
     }
}