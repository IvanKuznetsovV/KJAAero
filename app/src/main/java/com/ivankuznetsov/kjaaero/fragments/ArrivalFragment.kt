package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.ViewModel.FragmentViewModel
import com.ivankuznetsov.kjaaero.adapter.ArrivalDepartAdapter
import com.ivankuznetsov.kjaaero.databinding.FragmentArrivalBinding

class ArrivalFragment: BaseFragment<FragmentArrivalBinding, FragmentViewModel>() {

    private lateinit var searchView: SearchView

    override fun initBinding(inflater: LayoutInflater) = FragmentArrivalBinding.inflate(inflater)
    override fun getAllArrivalDepart(d: Int) { vModel.getAllArrival(d) }
    override fun initViewModel() = ViewModelProvider(this)[FragmentViewModel :: class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrivalDepartAdapter()
        val recyclerView = binding.arrivalRecycle
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        activity?.let{searchView = it.findViewById(R.id.search)}
        vModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, R.string.connect_error, Snackbar.LENGTH_SHORT).show()

        }
        vModel.progressLiveData.observe(viewLifecycleOwner) { progress -> binding.arrivalSearchProgress.isVisible = progress }
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