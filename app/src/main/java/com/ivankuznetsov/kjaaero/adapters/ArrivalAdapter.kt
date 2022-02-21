package com.ivankuznetsov.kjaaero.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ivankuznetsov.kjaaero.FlightData
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.FlightItemBinding
import java.util.*

class ArrivalAdapter(val myContext: Context?) : RecyclerView.Adapter<ArrivalAdapter.ArrivalViewHolder>(),
    Filterable {

    lateinit var context: Context
    private var arrivalListArray = mutableListOf<FlightData>()
    private var filterArrivalListArray = mutableListOf<FlightData>()

    fun setData(arrivalList : MutableList<FlightData>){
        arrivalListArray = arrivalList
        filterArrivalListArray = arrivalListArray
        notifyDataSetChanged()
    }
    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterArrivalListArray = if(charSearch.isEmpty()){
                    arrivalListArray
                }else{
                    val resultList = mutableListOf<FlightData>()
                    for (x in 0 until arrivalListArray.size) {
                        if (arrivalListArray[x].company.lowercase(Locale.ROOT).contains(charSearch.lowercase(
                                Locale.ROOT))) {
                            resultList.add(arrivalListArray[x])
                            Log.d("MyLog",arrivalListArray[x].company + x.toString())
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterArrivalListArray
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterArrivalListArray = results?.values as MutableList<FlightData>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrivalViewHolder {
        val binding = FlightItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val sch = ArrivalViewHolder(binding)
        context = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: ArrivalViewHolder, position: Int) { holder.bind(position) }
    override fun getItemCount(): Int  = filterArrivalListArray.size

    inner class ArrivalViewHolder(var viewBinding: FlightItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root){
        fun bind(index : Int){
            viewBinding.tvPlanTime.text = filterArrivalListArray[index].plan_time
            viewBinding.tvFactTime.text = filterArrivalListArray[index].fact_time
            viewBinding.tvPurpose.text = filterArrivalListArray[index].purpose
            if (filterArrivalListArray[index].status == "отменен" || filterArrivalListArray[index].status == "задержан") {
                viewBinding.tvStatus.setTextColor(ContextCompat.getColor(myContext!!,
                    android.R.color.holo_red_dark))
            } else {
                viewBinding.tvStatus.setTextColor(ContextCompat.getColor(myContext!!,
                    android.R.color.holo_green_dark))
            }
            viewBinding.tvStatus.text = filterArrivalListArray[index].status
            viewBinding.tvAirplane.text = filterArrivalListArray[index].airplane
            viewBinding.tvFlight.text = filterArrivalListArray[index].flight
            viewBinding.tvCompany.text = filterArrivalListArray[index].company
        }
    }
}