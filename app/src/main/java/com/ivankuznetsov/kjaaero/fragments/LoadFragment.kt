package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ivankuznetsov.kjaaero.databinding.FragmentLoadBinding

class LoadFragment : DialogFragment() {
    private lateinit var binding: FragmentLoadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,): View {
        binding = FragmentLoadBinding.inflate(inflater)
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }
}