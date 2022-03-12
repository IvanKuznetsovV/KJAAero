package com.ivankuznetsov.kjaaero.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ivankuznetsov.kjaaero.FlightData
import com.ivankuznetsov.kjaaero.activity.DetailActivity

import com.ivankuznetsov.kjaaero.databinding.FlightItemBinding
import java.util.*

class ArrivalDepartAdapter: RecyclerView.Adapter<ArrivalDepartAdapter.ArrivalDepartViewHolder>(),
    Filterable {
    private var originalADListArray = mutableListOf<FlightData>()
    private var filterADListArray = mutableListOf<FlightData>()

    fun setData(ADList: MutableList<FlightData>) {
        originalADListArray = ADList
        filterADListArray = originalADListArray
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrivalDepartAdapter.ArrivalDepartViewHolder {
        val binding = FlightItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArrivalDepartViewHolder(binding, parent.context)
    }
    override fun onBindViewHolder(holder: ArrivalDepartViewHolder, position: Int) { holder.bind(position) }
    override fun getItemCount(): Int = filterADListArray.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                filterADListArray = FilterResult.filterResult(charSearch,originalADListArray)
                    val filterResults = FilterResults()
                    filterResults.values = filterADListArray
                    return filterResults
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterADListArray = results?.values as MutableList<FlightData>
                notifyDataSetChanged()
            }
        }
    }
    inner class ArrivalDepartViewHolder(var viewBinding: FlightItemBinding,val context: Context) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(index : Int){
            viewBinding.tvPlanTime.text = filterADListArray[index].plan_time
            viewBinding.tvFactTime.text = filterADListArray[index].fact_time
            viewBinding.tvPurpose.text = filterADListArray[index].purpose
            if (filterADListArray[index].status == "отменен" || filterADListArray[index].status == "задержан") {
                viewBinding.tvStatus.setTextColor(ContextCompat.getColor(context,
                    android.R.color.holo_red_dark))
            } else {
                viewBinding.tvStatus.setTextColor(ContextCompat.getColor(context,
                    android.R.color.holo_green_dark))
            }
            viewBinding.tvStatus.text = filterADListArray[index].status
            viewBinding.tvAirplane.text = filterADListArray[index].airplane
            viewBinding.tvFlight.text = filterADListArray[index].flight
            viewBinding.tvCompany.text = filterADListArray[index].company
            viewBinding.flightIt.setOnClickListener {
                val intent = Intent(context, DetailActivity :: class.java)
                intent.putExtra("flightData", filterADListArray[index])
                context.startActivity(intent)
            }
        }
    }
}