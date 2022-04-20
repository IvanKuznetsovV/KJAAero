package com.ivankuznetsov.kjaaero

class URLs {
    private val departYesterday = "https://www.kja.aero/page-online-tablo/?day=2"
    private val departToday = "https://www.kja.aero/page-online-tablo/"
    private val departTomorrow = "https://www.kja.aero/page-online-tablo/?day=3"

    private val arrivalYesterday = "https://www.kja.aero/page-online-tablo/?day=2&type=2"
    private val arrivalToday = "https://www.kja.aero/page-online-tablo/?type=2"
    private val arrivalTomorrow = "https://www.kja.aero/page-online-tablo/?type=2&day=3"

     fun getDepartDay(day: Int?): String =
        when (day) {
            R.string.yesterday -> departYesterday
            R.string.app_name -> departToday
            R.string.tomorrow -> departTomorrow
            else -> departToday
        }

     fun getArrivalDay(day: Int?): String =
        when (day) {
            R.string.yesterday -> arrivalYesterday
            R.string.app_name -> arrivalToday
            R.string.tomorrow -> arrivalTomorrow
            else -> arrivalToday
        }
}