package com.ivankuznetsov.kjaaero
import android.app.Application
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ivankuznetsov.kjaaero.fragments.ConnectErrorDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val allArrivalFlight = MutableLiveData<MutableList<FlightData>>()
    val allDepartFlight  = MutableLiveData<MutableList<FlightData>>()
    var day: String? = null
    var progressBar: DialogFragment? = null
    var dialog:FragmentManager? = null
    private val dialogConnectError = ConnectErrorDialogFragment()

    private suspend fun message(){
        viewModelScope.launch(Dispatchers.Main) {
            progressBar?.dismiss()
            dialogConnectError.show(dialog!!, "dialogConnectError")
        }.join()
    }
    private suspend fun progressBarShow(){
        viewModelScope.launch(Dispatchers.Main) {
            progressBar?.show(dialog!!, "PB")
        }.join()
    }
    private suspend fun progressBarDismiss(){
        viewModelScope.launch(Dispatchers.Main) {
            progressBar?.dismiss()
        }.join()
    }

    fun getAllArrival(dayUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
                 try {
                     progressBarShow()
                     val repository = UserRepository(day)
                     val x = repository.getArrival()
                     allArrivalFlight.postValue(x)
                     progressBarDismiss()
                 }
                 catch (e: Exception){message()}
         }
    }

     fun getAllDepart(dayUrl: String){
         viewModelScope.launch(Dispatchers.IO) {
                 try {
                     progressBarShow()
                     val repository = UserRepository(day)
                     val x = repository.getDepart()
                     allDepartFlight.postValue(x)
                     progressBarDismiss()
                 }
                 catch (e: Exception){message()}
         }
     }
}