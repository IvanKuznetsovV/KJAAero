package com.ivankuznetsov.kjaaero
import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ivankuznetsov.kjaaero.fragments.ConnectErrorFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val allArrivalFlight = MutableLiveData<MutableList<FlightData>>()
    val allDepartFlight  = MutableLiveData<MutableList<FlightData>>()
    var day: String? = null
    var progressBar: DialogFragment? = null
    var dialog:FragmentManager? = null
    val dialogConnectError = ConnectErrorFragment()

    private suspend fun message(){
        viewModelScope.launch(Dispatchers.Main) {
            progressBar?.dismiss()
            dialogConnectError.show(dialog!!, "dialogConnectError")
        }.join()
    }

    fun getAllArrival() {
         viewModelScope.launch(Dispatchers.IO) {
                 try {
                     val repository = UserRepository(day)
                     val x = repository.getArrival()
                     allArrivalFlight.postValue(x)
                 }
                 catch (e: Exception){message()}
         }
    }

     fun getAllDepart(){
         viewModelScope.launch(Dispatchers.IO) {
                 try {
                     val repository = UserRepository(day)
                     val x = repository.getDepart()
                     allDepartFlight.postValue(x)
                 }
                 catch (e: Exception){message()}
         }
     }
}