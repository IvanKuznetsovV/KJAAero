package com.ivankuznetsov.kjaaero.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.ActivityDetailBinding
import com.ivankuznetsov.kjaaero.databinding.ActivityMainBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        binding.detailTimePlan.text = bundle?.get("tvPlanTime").toString()
        binding.detailTimeFact.text = bundle?.get("tvFactTime").toString()
        binding.detailPurpose.text = bundle?.get("tvPurpose").toString()
        binding.detailStatus.text = bundle?.get("tvStatus").toString()
        binding.detailAirplan.text = bundle?.get("tvAirplane").toString()
        binding.detailFlight.text = bundle?.get("tvFlight").toString()
        binding.detailCompany.text = bundle?.get("tvCompany").toString()
    }
}