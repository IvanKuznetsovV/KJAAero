package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<B: ViewBinding>: DialogFragment() {

    private var viewBinding: B? = null
    protected val binding get() = checkNotNull(viewBinding)

    abstract fun initBinding(inflater: LayoutInflater): B?
    abstract fun initDrawableResource(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = initBinding(inflater)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.window?.setBackgroundDrawableResource(initDrawableResource())

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

}