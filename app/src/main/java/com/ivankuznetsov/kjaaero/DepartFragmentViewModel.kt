package com.ivankuznetsov.kjaaero

import android.app.Application
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ivankuznetsov.kjaaero.fragments.ConnectErrorDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DepartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allDepartFlight  = MutableLiveData<MutableList<FlightData>>()
    val progressBar = this as ProgressBarProgress
    init { getAllDepart(R.string.app_name) }

    fun getAllDepart(dayUrl: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progressBar.progressBarProgress(true)
                val repository = KJARepository(dayUrl)
                val x = repository.getDepart()
                allDepartFlight.postValue(x)
                progressBar.progressBarProgress(false)
            }
            catch (e: Exception){ }
        }
    }
}