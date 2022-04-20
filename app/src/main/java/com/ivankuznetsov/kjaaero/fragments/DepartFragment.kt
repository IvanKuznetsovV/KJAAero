package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivankuznetsov.kjaaero.DepartFragmentViewModel
import com.ivankuznetsov.kjaaero.ProgressBarProgress
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.adapter.ArrivalDepartAdapter
import com.ivankuznetsov.kjaaero.databinding.FragmentDepartBinding

class DepartFragment: BaseFragment<FragmentDepartBinding, DepartFragmentViewModel>(),
    ProgressBarProgress {
    private lateinit var searchView: SearchView

    override fun initBinding(inflater: LayoutInflater) =  FragmentDepartBinding.inflate(inflater)
    override fun getAllArrivalDepart(d: Int) { vModel.getAllDepart(d) }
    override fun initViewModel() = ViewModelProvider(this)[DepartFragmentViewModel :: class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrivalDepartAdapter()
        val recyclerView = binding.departRecycle
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        activity?.let { searchView = it.findViewById(R.id.search) }

        vModel.allDepartFlight.observe(viewLifecycleOwner) { allDepartFlight ->
            adapter.setData(allDepartFlight)
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
    override fun progressBarProgress(visible: Boolean): Boolean {
        binding.departSearchProgress.isVisible = visible
        return binding.departSearchProgress.isVisible
    }
}