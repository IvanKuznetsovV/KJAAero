package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.FragmentInfoDialogBinding

class InfoDialogFragment: BaseDialogFragment<FragmentInfoDialogBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentInfoDialogBinding.inflate(inflater)
    override fun initDrawableResource() = R.drawable.check_internet_dialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonOk.setOnClickListener { dismiss() }
    }
}