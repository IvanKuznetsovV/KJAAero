package com.ivankuznetsov.kjaaero.InternetConnection

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import com.ivankuznetsov.kjaaero.R

class ConnectivityManager(context: Context, private val view: View) {

    private val connectionLiveData = ConnectionLiveData(context)

    fun registerConnectionObserver(lifecycleOwner: LifecycleOwner){
        connectionLiveData.observe(lifecycleOwner){
            if(!it){
                Snackbar.make(view, R.string.connect_error, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner){
        connectionLiveData.removeObservers(lifecycleOwner)
    }
}