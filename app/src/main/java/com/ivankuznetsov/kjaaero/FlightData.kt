package com.ivankuznetsov.kjaaero

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlightData(
    val plan_time: String?,
    val fact_time: String?,
    val purpose: String?,
    val status: String?,
    val airplane: String?,
    val flight: String?,
    val company: String?,
    val purposeAD: String?,
    val phone: String?
): Parcelable



