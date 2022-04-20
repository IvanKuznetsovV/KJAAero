package com.ivankuznetsov.kjaaero.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import com.ivankuznetsov.kjaaero.FlightData
import com.ivankuznetsov.kjaaero.databinding.FlightItemBinding

object BindData {
    fun bind(index : Int,viewBinding: FlightItemBinding, filterADList: MutableList<FlightData>, context: Context?){
        viewBinding.tvPlanTime.text = filterADList[index].plan_time
        viewBinding.tvFactTime.text = filterADList[index].fact_time
        viewBinding.tvPurpose.text = filterADList[index].purpose
        if (filterADList[index].status == "отменен" || filterADList[index].status == "задержан") {
            viewBinding.tvStatus.setTextColor(ContextCompat.getColor(context!!,
                android.R.color.holo_red_dark))
        } else {
            viewBinding.tvStatus.setTextColor(ContextCompat.getColor(context!!,
                android.R.color.holo_green_dark))
        }
        viewBinding.tvStatus.text = filterADList[index].status
        viewBinding.tvAirplane.text = filterADList[index].airplane
        viewBinding.tvFlight.text = filterADList[index].flight
        viewBinding.tvCompany.text = filterADList[index].company
        viewBinding.flightIt.setOnClickListener {

        }
    }
}