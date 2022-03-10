package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.UserViewModel
import com.ivankuznetsov.kjaaero.adapters.ArrivalAdapter
import com.ivankuznetsov.kjaaero.databinding.FragmentArrivalBinding

class ArrivalFragment(private val day: String) : Fragment(){
    private var progressBar : DialogFragment = LoadFragment()
    private lateinit var binding : FragmentArrivalBinding
    private lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
            binding = FragmentArrivalBinding.inflate(inflater)
            val adapter = ArrivalAdapter(context)
            val recyclerView = binding.arrivalRecycle
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            val userViewModel = ViewModelProvider(this)[UserViewModel :: class.java]

            userViewModel.day = day
            userViewModel.progressBar = progressBar
            userViewModel.dialog = childFragmentManager

            activity?.let{searchView = it.findViewById(R.id.search)}

            userViewModel.getAllArrival()
                progressBar.showNow(childFragmentManager, "load")
                    userViewModel.allArrivalFlight.observe(viewLifecycleOwner,
                        { allArrivalFlight ->
                            adapter.setData(allArrivalFlight)
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
}