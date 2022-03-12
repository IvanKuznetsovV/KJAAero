package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.FragmentLoadBinding

class LoadDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentLoadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,): View {
        binding = FragmentLoadBinding.inflate(inflater)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.progress_bar_shape)

        return binding.root
    }
}