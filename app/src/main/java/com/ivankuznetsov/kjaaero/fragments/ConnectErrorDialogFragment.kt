package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.FragmentConnectErrorBinding

class ConnectErrorDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentConnectErrorBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentConnectErrorBinding.inflate(inflater)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.check_internet_dialog)
        binding.buttonOk.setOnClickListener(View.OnClickListener {
            dismiss()
        })
        return binding.root
    }

}