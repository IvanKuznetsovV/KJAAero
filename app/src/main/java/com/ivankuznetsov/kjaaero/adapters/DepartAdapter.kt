package com.ivankuznetsov.kjaaero.adapters

import android.content.Context
import android.content.Intent
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

class DepartAdapter(var myContext: Context?) : RecyclerView.Adapter<DepartAdapter.DepartViewHolder>(),
    Filterable {
    lateinit var context: Context
    private var departListArray = mutableListOf<FlightData>()
    private var filterDepartListArray = mutableListOf<FlightData>()

    fun setData(departList : MutableList<FlightData>){
        departListArray = departList
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
                        if (departListArray[x].company?.lowercase(Locale.ROOT)?.contains(charSearch.lowercase(Locale.ROOT))!! ||
                            departListArray[x].flight?.lowercase(Locale.ROOT)?.contains(charSearch.lowercase(Locale.ROOT))!! ||
                            departListArray[x].purpose?.lowercase(Locale.ROOT)?.contains(charSearch.lowercase(Locale.ROOT))!! ||
                            departListArray[x].plan_time?.lowercase(Locale.ROOT)?.contains(charSearch.lowercase(Locale.ROOT))== true) {
                            resultList.add(departListArray[x])
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
                intent.putExtra("flightData", filterDepartListArray[index] )
                intent.putExtra("depart","true")
                context.startActivity(intent)
            }
        }
    }
}