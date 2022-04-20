package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.FragmentConnectErrorBinding

class ConnectErrorDialogFragment: BaseDialogFragment<FragmentConnectErrorBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentConnectErrorBinding.inflate(inflater)
    override fun initDrawableResource() = R.drawable.check_internet_dialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonOk.setOnClickListener { dismiss() }
    }
}