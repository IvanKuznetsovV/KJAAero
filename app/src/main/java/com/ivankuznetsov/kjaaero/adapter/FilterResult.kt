package com.ivankuznetsov.kjaaero.adapter

import com.ivankuznetsov.kjaaero.FlightData
import java.util.*

object FilterResult {
    fun filterResult(charSearch:String,originalADList: MutableList<FlightData>):MutableList<FlightData> {
        return if(charSearch.isEmpty()){
            originalADList
        }else{
            val resultList = mutableListOf<FlightData>()
            for (x in 0 until originalADList.size) {
                if (originalADList[x].company?.lowercase(Locale.ROOT)?.contains(charSearch.lowercase(Locale.ROOT))!! ||
                    originalADList[x].flight?.lowercase(Locale.ROOT)?.contains(charSearch.lowercase(Locale.ROOT))!! ||
                    originalADList[x].purpose?.lowercase(Locale.ROOT)?.contains(charSearch.lowercase(Locale.ROOT)) == true) {
                    resultList.add(originalADList[x])
                }
            }
            resultList
        }
    }
}