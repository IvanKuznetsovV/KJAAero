package com.ivankuznetsov.kjaaero.fragments

import android.view.LayoutInflater
import com.ivankuznetsov.kjaaero.R
import com.ivankuznetsov.kjaaero.databinding.FragmentDetailInfoDialogBinding


class DetailInfoDialogFragment : BaseDialogFragment<FragmentDetailInfoDialogBinding>(){
    override fun initBinding(inflater: LayoutInflater) = FragmentDetailInfoDialogBinding.inflate(inflater)
    override fun initDrawableResource() = R.drawable.check_internet_dialog



}