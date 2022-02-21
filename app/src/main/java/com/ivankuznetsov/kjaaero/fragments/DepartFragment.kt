package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.UserViewModel
import com.ivankuznetsov.kjaaero.adapters.DepartAdapter
import com.ivankuznetsov.kjaaero.databinding.FragmentDepartBinding

class DepartFragment(private val day: String) : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding : FragmentDepartBinding
    private var progressBar : DialogFragment = LoadFragment()
    private lateinit var searchView: SearchView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View {
        binding = FragmentDepartBinding.inflate(inflater)
        val adapter = DepartAdapter(context)
        val recyclerView = binding.departRecycle
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        val userViewModel = ViewModelProvider(this)[UserViewModel :: class.java]
        userViewModel.day = day

        userViewModel.getAllDepart()
        activity?.let{searchView = it.findViewById(R.id.search)}

        progressBar.showNow(childFragmentManager, "load")
        userViewModel.allDepartFlight.observe(viewLifecycleOwner, Observer {
                allDepartFlight -> adapter.setData(allDepartFlight)
                adapter.filter.filter(searchView.query.toString())
            progressBar.dismiss()
        })
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean { return true }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
        return binding.root
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }


}