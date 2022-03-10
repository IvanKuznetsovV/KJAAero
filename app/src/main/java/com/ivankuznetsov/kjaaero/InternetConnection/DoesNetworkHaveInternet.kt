package com.ivankuznetsov.kjaaero.InternetConnection

import java.net.InetSocketAddress
import java.net.Socket

object DoesNetworkHaveInternet {
    fun check() : Boolean {
        return try {
            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8",53))
            socket.close()
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }
}