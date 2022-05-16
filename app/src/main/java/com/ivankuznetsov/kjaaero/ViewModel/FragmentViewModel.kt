package com.ivankuznetsov.kjaaero.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ivankuznetsov.kjaaero.FlightData
import com.ivankuznetsov.kjaaero.KJARepository
import com.ivankuznetsov.kjaaero.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allDepartFlight  = MutableLiveData<MutableList<FlightData>>()
    val allArrivalFlight = MutableLiveData<MutableList<FlightData>>()
    val progressLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<Boolean>()

    init {  getAllDepart(R.string.app_name)
            getAllArrival(R.string.app_name)
    }

    fun getAllDepart(dayUrl: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progressLiveData.postValue(true)
                val repository = KJARepository(dayUrl)
                val x = repository.getDepart()
                allDepartFlight.postValue(x)
                progressLiveData.postValue(false)
            }
            catch (e: Exception){ errorMessageLiveData.postValue(true)
                                    progressLiveData.postValue(false)}
        }
    }

    fun getAllArrival(dayUrl: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progressLiveData.postValue(true)
                val repository = KJARepository(dayUrl)
                val x = repository.getArrival()
                allArrivalFlight.postValue(x)
                progressLiveData.postValue(false)
            }
            catch (e: Exception){ errorMessageLiveData.postValue(true)
                                    progressLiveData.postValue(false)}
        }
    }
}