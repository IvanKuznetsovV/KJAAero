package com.ivankuznetsov.kjaaero.InternetConnection

import android.content.Context
import androidx.lifecycle.LifecycleOwner

class ConnectivityManager(context: Context) {
    private val connectionLiveData = ConnectionLiveData(context)

    fun registerConnectionObserver(lifecycleOwner: LifecycleOwner){

    }
    fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner){
        connectionLiveData.removeObservers(lifecycleOwner)
    }
}