package com.ivankuznetsov.kjaaero.fragments



import android.view.LayoutInflater
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.FragmentLoadBinding

class LoadDialogFragment: BaseDialogFragment<FragmentLoadBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentLoadBinding.inflate(inflater)
    override fun initDrawableResource() = R.drawable.progress_bar_shape
}