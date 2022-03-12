package com.ivankuznetsov.kjaaero.activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.ivankuznetsov.kjaaero.InternetConnection.ConnectionLiveData
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.ActivityMainBinding
import com.ivankuznetsov.kjaaero.fragments.ArrivalFragment
import com.ivankuznetsov.kjaaero.fragments.ConnectErrorDialogFragment
import com.ivankuznetsov.kjaaero.fragments.DepartFragment
import com.ivankuznetsov.kjaaero.fragments.InfoDialogFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val dialogConnectError = ConnectErrorDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val connectionLiveData = ConnectionLiveData(this)
                connectionLiveData.observe(this, {
                    if(!it) dialogConnectError.show(supportFragmentManager, "connectError")
                    else{
                        if(supportFragmentManager.findFragmentByTag("connectError") != null) dialogConnectError.dismiss()
                        reload()
                    }
                })

        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        binding.bottomNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.arrival -> { createArrivalFragment() }
                R.id.depart -> { createDepartFragment() }
            }
            true
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.info -> {
                val dialogInfo = InfoDialogFragment()
                dialogInfo.show(supportFragmentManager, "info")
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private  fun createArrivalFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, ArrivalFragment())
            .commit()
    }
    private fun createDepartFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, DepartFragment())
            .commit()
    }
    private fun reload(){
        if(binding.bottomNav.selectedItemId == R.id.depart) createDepartFragment()
        else createArrivalFragment()
    }
}