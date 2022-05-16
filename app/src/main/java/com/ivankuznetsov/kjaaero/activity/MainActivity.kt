package com.ivankuznetsov.kjaaero.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.ActivityMainBinding
import com.ivankuznetsov.kjaaero.InternetConnection.ConnectivityManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var connectivityManager: ConnectivityManager

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_holder) as NavHostFragment
            val navView: BottomNavigationView = binding.bottomNav
            val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar

            connectivityManager = ConnectivityManager(this, binding.root)

            navController = navHostFragment.navController
            navView.setupWithNavController(navController)
            setSupportActionBar(toolbar)

        navView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.arrival -> { navController.navigate(R.id.arrivalFragment) }
                R.id.depart -> { navController.navigate(R.id.departFragment) }
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }


}