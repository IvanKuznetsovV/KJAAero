package com.ivankuznetsov.kjaaero.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.ActivityMainBinding
import com.ivankuznetsov.kjaaero.fragments.ArrivalFragment
import com.ivankuznetsov.kjaaero.fragments.DepartFragment
import java.net.InetAddress

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val departReload = "Вылет"
    private var day = "KJA Красноярск Сегодня"

    override fun onCreate(savedInstanceState: Bundle?)  {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        createDepartFragment(day)
        binding.bottomNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.arrival -> {
                    createArrivalFragment(day)
                }
                R.id.depart -> {
                    createDepartFragment(day)
                }
            }
            true
        }

    }
    private  fun createArrivalFragment(d: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, ArrivalFragment(d))
            .commit()
    }
    private fun createDepartFragment(d: String){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, DepartFragment(d))
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.info ->{
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle(R.string.info).setMessage(R.string.info_message)
                alertDialog.setPositiveButton("ПОНЯТНО"){dialog, which -> }
                val dialog: AlertDialog = alertDialog.create()
                dialog.show()
            }
            R.id.reload ->{
               reload(day)
            }
            R.id.yesterday -> {
                day = "KJA Красноярск Вчера"
                binding.toolbar.title = day
                reload(day)
            }
            R.id.today -> {
                day = "KJA Красноярск Сегодня"
                binding.toolbar.title = day
                reload(day)
            }
            R.id.tomorrow -> {
                day = "KJA Красноярск Завтра"
                binding.toolbar.title = day
                reload(day)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun reload(dayReload: String){
        if(binding.bottomNav.menu.findItem(binding.bottomNav.selectedItemId).toString() == departReload){
            createDepartFragment(dayReload)
        }
        else{
            createArrivalFragment(dayReload)
        }
    }
}