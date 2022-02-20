package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivankuznetsov.kjaaero.UserViewModel
import com.ivankuznetsov.kjaaero.adapters.ArrivalAdapter
import com.ivankuznetsov.kjaaero.databinding.FragmentArrivalBinding



class ArrivalFragment(private val day: String) : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding : FragmentArrivalBinding
    private var progressBar : DialogFragment = LoadFragment()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?, ): View {
            binding = FragmentArrivalBinding.inflate(inflater)
            val adapter = ArrivalAdapter(context)
            val recyclerView = binding.arrivalRecycle
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            val userViewModel = ViewModelProvider(this)[UserViewModel :: class.java]
            userViewModel.day = day

            userViewModel.getAllArrival()

            progressBar.showNow(childFragmentManager, "load")
            userViewModel.allArrivalFlight.observe(viewLifecycleOwner, Observer {

                    allArrivalFlight -> adapter.setData(allArrivalFlight)
                    progressBar.dismiss()
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