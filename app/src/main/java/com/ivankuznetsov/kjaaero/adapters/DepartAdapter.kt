package com.ivankuznetsov.kjaaero.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ivankuznetsov.kjaaero.FlightData
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
                for(y in 0 until filterDepartListArray.size) {
                    Log.d("MyLog",filterDepartListArray[y].company + y.toString())

                }
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

    override fun onBindViewHolder(holder: DepartViewHolder, position: Int) {
        holder.viewBinding.tvPlanTime.text = filterDepartListArray[position].plan_time
        holder.viewBinding.tvFactTime.text = filterDepartListArray[position].fact_time
        holder.viewBinding.tvPurpose.text = filterDepartListArray[position].purpose
        if (filterDepartListArray[position].status == "отменен" || filterDepartListArray[position].status == "задержан") {
            holder.viewBinding.tvStatus.setTextColor(ContextCompat.getColor(myContext!!,
                android.R.color.holo_red_dark))
        } else {
            holder.viewBinding.tvStatus.setTextColor(ContextCompat.getColor(myContext!!,
                android.R.color.holo_green_dark))
        }
        holder.viewBinding.tvStatus.text = filterDepartListArray[position].status
        holder.viewBinding.tvAirplane.text = filterDepartListArray[position].airplane
        holder.viewBinding.tvFlight.text = filterDepartListArray[position].flight
        holder.viewBinding.tvCompany.text = filterDepartListArray[position].company
    }

    override fun getItemCount(): Int = filterDepartListArray.size

    inner class DepartViewHolder(var viewBinding: FlightItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

//        fun bind(index: Int) {
//            viewBinding.tvPlanTime.text = departListArray[index].plan_time
//            viewBinding.tvFactTime.text = departListArray[index].fact_time
//            viewBinding.tvPurpose.text = departListArray[index].purpose
//            if (departListArray[index].status == "отменен" || departListArray[index].status == "задержан") {
//                viewBinding.tvStatus.setTextColor(ContextCompat.getColor(myContext!!,
//                    android.R.color.holo_red_dark))
//            } else {
//                viewBinding.tvStatus.setTextColor(ContextCompat.getColor(myContext!!,
//                    android.R.color.holo_green_dark))
//            }
//            viewBinding.tvStatus.text = departListArray[index].status
//            viewBinding.tvAirplane.text = departListArray[index].airplane
//            viewBinding.tvFlight.text = departListArray[index].flight
//            viewBinding.tvCompany.text = departListArray[index].company
//        }
    }
}