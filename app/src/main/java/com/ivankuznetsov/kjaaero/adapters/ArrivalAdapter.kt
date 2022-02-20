package com.ivankuznetsov.kjaaero.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ivankuznetsov.kjaaero.FlightData
import com.ivankuznetsov.kjaaero.R

class ArrivalAdapter(val myContext: Context?) : RecyclerView.Adapter<ArrivalAdapter.ArrivalViewHolder>() {

    private var arrivalListArray = mutableListOf<FlightData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrivalViewHolder {
        val context : Context = parent.context
        val layoutID :Int = R.layout.flight_item
        val layoutInflater :LayoutInflater = LayoutInflater.from(context)
        val view : View =layoutInflater.inflate(layoutID, parent, false)
        return ArrivalViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArrivalViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int  = arrivalListArray.size

    inner class ArrivalViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var textViewScheduledTime : TextView = itemView.findViewById(R.id.tv_plan_time)
        var textViewDepartureTime : TextView = itemView.findViewById(R.id.tv_fact_time)
        var textViewDepartPurpose : TextView = itemView.findViewById(R.id.tv_purpose)
        var textViewStatus : TextView = itemView.findViewById(R.id.tv_status)
        var textViewAirplane : TextView = itemView.findViewById(R.id.tv_airplane)
        var textViewFlight : TextView = itemView.findViewById(R.id.tv_flight)
        var textViewCompany : TextView = itemView.findViewById(R.id.tv_company)


        fun bind(index : Int){
            textViewScheduledTime.text = arrivalListArray[index].plan_time
            textViewDepartureTime.text = arrivalListArray[index].fact_time
            textViewDepartPurpose.text = arrivalListArray[index].purpose
            if(arrivalListArray[index].status == "отменен" || arrivalListArray[index].status == "задержан") {
                textViewStatus.setTextColor(ContextCompat.getColor(myContext!!, android.R.color.holo_red_dark))
            }
            else{
                textViewStatus.setTextColor(ContextCompat.getColor(myContext!!,
                    android.R.color.holo_green_dark))
            }
            textViewStatus.text = arrivalListArray[index].status
            textViewAirplane.text = arrivalListArray[index].airplane
            textViewFlight.text = arrivalListArray[index].flight
            textViewCompany.text = arrivalListArray[index].company
        }
    }
    fun setData(arrivalList : MutableList<FlightData>){
        arrivalListArray = arrivalList
        notifyDataSetChanged()
    }
}