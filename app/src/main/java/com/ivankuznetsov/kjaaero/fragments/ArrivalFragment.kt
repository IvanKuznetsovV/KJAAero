package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivankuznetsov.kjaaero.ArrivalFragmentViewModel
import com.ivankuznetsov.kjaaero.DepartFragmentViewModel
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.adapter.ArrivalDepartAdapter
import com.ivankuznetsov.kjaaero.databinding.FragmentArrivalBinding

class ArrivalFragment: BaseFragment<FragmentArrivalBinding, ArrivalFragmentViewModel>() {

    private lateinit var searchView: SearchView

    override fun initBinding(inflater: LayoutInflater) = FragmentArrivalBinding.inflate(inflater)
    override fun getAllArrivalDepart(d: Int) { vModel.getAllArrival(d) }
    override fun initViewModel() = ViewModelProvider(this)[ArrivalFragmentViewModel :: class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrivalDepartAdapter()
        val recyclerView = binding.arrivalRecycle
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        activity?.let{searchView = it.findViewById(R.id.search)}


        vModel.allArrivalFlight.observe(viewLifecycleOwner) { allArrivalFlight ->
            adapter.setData(allArrivalFlight)
            adapter.filter.filter(searchView.query.toString())
        }
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean { return true }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }


}