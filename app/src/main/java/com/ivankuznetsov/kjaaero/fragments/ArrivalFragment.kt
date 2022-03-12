package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.*

import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.UserViewModel
import com.ivankuznetsov.kjaaero.adapters.ArrivalDepartAdapter
import com.ivankuznetsov.kjaaero.databinding.FragmentArrivalBinding

class ArrivalFragment: Fragment(){
    private val day: String = "KJA Красноярск Сегодня"
    private var progressBar : DialogFragment = LoadDialogFragment()
    private lateinit var binding : FragmentArrivalBinding
    private lateinit var searchView: SearchView
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: ArrivalDepartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this)[UserViewModel :: class.java]
        userViewModel.day = day
        userViewModel.progressBar = progressBar
        userViewModel.dialog = childFragmentManager
        adapter = ArrivalDepartAdapter()
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater){
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu,menuInflater )
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.reload ->{
                userViewModel.getAllArrival(day)
            }
            R.id.yesterday -> {

            }
            R.id.today -> {

            }
            R.id.tomorrow -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
            binding = FragmentArrivalBinding.inflate(inflater)

            val adapter = ArrivalDepartAdapter()
            val recyclerView = binding.arrivalRecycle
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            val userViewModel = ViewModelProvider(this)[UserViewModel :: class.java]

            activity?.let{searchView = it.findViewById(R.id.search)}

            userViewModel.getAllArrival(day)
                userViewModel.allArrivalFlight.observe(viewLifecycleOwner, {
                            allArrivalFlight -> adapter.setData(allArrivalFlight)
                            adapter.filter.filter(searchView.query.toString())
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