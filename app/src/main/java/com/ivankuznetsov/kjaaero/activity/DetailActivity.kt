package com.ivankuznetsov.kjaaero.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivankuznetsov.kjaaero.FlightData
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.ActivityDetailBinding
import com.ivankuznetsov.kjaaero.databinding.ActivityMainBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getParcelableExtra<FlightData>("flightData")
        val bundleSetTextView = intent.extras
        val phone = bundle?.phone?.replace(",", "\n")?.replace(" ", "")
        if(bundleSetTextView?.get("depart") == "true"){
            binding.purposeAD.text = "Прилет:"
        }else{
            binding.purposeAD.text = "Вылет:"
        }

        binding.detailTimePlan.text = bundle?.plan_time
        binding.detailTimeFact.text = bundle?.fact_time
        binding.detailPurpose.text = bundle?.purpose
        binding.detailStatus.text = bundle?.status
        binding.detailAirplan.text = bundle?.airplane
        binding.detailFlight.text = bundle?.flight
        binding.detailCompany.text = bundle?.company
        binding.detailTime.text = bundle?.purposeAD
        binding.phone.text = phone


    }
}