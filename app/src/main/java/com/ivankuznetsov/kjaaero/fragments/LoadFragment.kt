package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import com.ivankuznetsov.kjaaero.R

class LoadFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_INPUT,theme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
        val view = inflater.inflate(R.layout.fragment_load, container, false)
        val progressBar : ProgressBar  = view.findViewById(R.id.progress_bar)
        progressBar.visibility = View.VISIBLE
        return view
    }

}