package com.ivankuznetsov.kjaaero.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ivankuznetsov.kjaaero.FlightData
import com.ivankuznetsov.kjaaero.activity.DetailActivity
import com.ivankuznetsov.kjaaero.databinding.FlightItemBinding
import java.util.*

class DepartAdapter(var myContext: Context?) : RecyclerView.Adapter<DepartAdapter.DepartViewHolder>(),
    Filterable {
    lateinit var context: Context
    private var departListArray = mutableListOf<FlightData>()
    private var filterDepartListArray = mutableListOf<FlightData>()

    fun setData(arrivalList : MutableList<FlightData>){
        departListArray = arrivalList
        filterDepartListArray = departListArray
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterDepartListArray = if(charSearch.isEmpty()){
                    departListArray
                }else{
                    val resultList = mutableListOf<FlightData>()
                    for (x in 0 until departListArray.size) {
                        if (departListArray[x].company.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(departListArray[x])
                            Log.d("MyLog",departListArray[x].company + x.toString())
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterDepartListArray
                return filterResults
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterDepartListArray = results?.values as MutableList<FlightData>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartViewHolder {
        val binding = FlightItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val sch = DepartViewHolder(binding)
        context = parent.context
        return sch
    }
    override fun onBindViewHolder(holder: DepartViewHolder, position: Int) { holder.bind(position) }

    override fun getItemCount(): Int = filterDepartListArray.size

    inner class DepartViewHolder(var viewBinding: FlightItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(index: Int) {
            viewBinding.tvPlanTime.text = filterDepartListArray[index].plan_time
            viewBinding.tvFactTime.text = filterDepartListArray[index].fact_time
            viewBinding.tvPurpose.text = filterDepartListArray[index].purpose
            if (filterDepartListArray[index].status == "отменен" || filterDepartListArray[index].status == "задержан") {
                viewBinding.tvStatus.setTextColor(ContextCompat.getColor(myContext!!,
                    android.R.color.holo_red_dark))
            } else {
                viewBinding.tvStatus.setTextColor(ContextCompat.getColor(myContext!!,
                    android.R.color.holo_green_dark))
            }
            viewBinding.tvStatus.text = filterDepartListArray[index].status
            viewBinding.tvAirplane.text = filterDepartListArray[index].airplane
            viewBinding.tvFlight.text = filterDepartListArray[index].flight
            viewBinding.tvCompany.text = filterDepartListArray[index].company
            viewBinding.flightIt.setOnClickListener {
                val intent = Intent(context,DetailActivity :: class.java)
                intent.putExtra("tvPlanTime", filterDepartListArray[index].plan_time)
                intent.putExtra("tvFactTime", filterDepartListArray[index].fact_time)
                intent.putExtra("tvPurpose", filterDepartListArray[index].purpose)
                intent.putExtra("tvStatus", filterDepartListArray[index].status)
                intent.putExtra("tvAirplane", filterDepartListArray[index].airplane)
                intent.putExtra("tvFlight", filterDepartListArray[index].flight)
                intent.putExtra("tvCompany", filterDepartListArray[index].company)
                context.startActivity(intent)
            }
        }
    }
}