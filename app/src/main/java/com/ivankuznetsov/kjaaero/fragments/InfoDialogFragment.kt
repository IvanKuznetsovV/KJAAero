package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.FragmentInfoDialogBinding


class InfoDialogFragment :DialogFragment() {
    lateinit var binding: FragmentInfoDialogBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentInfoDialogBinding.inflate(inflater)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.check_internet_dialog)
        binding.buttonOk.setOnClickListener(View.OnClickListener {
            dismiss()
        })
        return binding.root
    }
}