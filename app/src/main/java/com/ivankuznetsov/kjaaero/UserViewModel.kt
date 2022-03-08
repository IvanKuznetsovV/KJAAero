package com.ivankuznetsov.kjaaero
import android.app.Application
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val allArrivalFlight = MutableLiveData<MutableList<FlightData>>()
    val allDepartFlight  = MutableLiveData<MutableList<FlightData>>()
    var day: String? = null
    var progressBar: DialogFragment? = null

    private fun checkStatusCode(daysURL: String?): Boolean {
        val response = Jsoup.connect(daysURL).followRedirects(false).execute()
        return response.statusCode() == 200
    }

    private suspend fun message(){
        viewModelScope.launch(Dispatchers.Main) {
            progressBar?.dismiss()
            Toast.makeText(getApplication(), "NO CONNECT", Toast.LENGTH_LONG).show()
        }.join()
    }

    fun getAllArrival() {
         viewModelScope.launch(Dispatchers.IO) {
             if(NetworkManger.checkConnect(getApplication()) && checkStatusCode("https://www.kja.aero") ) {
                 val repository = UserRepository(day)
                 val x = repository.getArrival()
                 allArrivalFlight.postValue(x)
             }
             else message()
         }
    }

     fun getAllDepart(){
        viewModelScope.launch(Dispatchers.IO) {
            if(NetworkManger.checkConnect(getApplication()) && checkStatusCode("https://www.kja.aero")) {
                val repository = UserRepository(day)
                val x = repository.getDepart()
                allDepartFlight.postValue(x)
            }
            else message()
        }
     }
}