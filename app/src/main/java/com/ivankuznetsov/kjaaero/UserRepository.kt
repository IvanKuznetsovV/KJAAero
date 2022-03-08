package com.ivankuznetsov.kjaaero

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.select.Elements


class UserRepository(var daysURL: String?) {
    private val yesterday = "KJA Красноярск Вчера"
    private val today = "KJA Красноярск Сегодня"
    private val tomorrow = "KJA Красноярск Завтра"

    private val departYesterday = "https://www.kja.aero/page-online-tablo/?day=2"
    private val departToday = "https://www.kja.aero/page-online-tablo/"
    private val departTomorrow = "https://www.kja.aero/page-online-tablo/?day=3"

    private val arrivalYesterday = "https://www.kja.aero/page-online-tablo/?day=2&type=2"
    private val arrivalToday = "https://www.kja.aero/page-online-tablo/?type=2"
    private val arrivalTomorrow = "https://www.kja.aero/page-online-tablo/?type=2&day=3"

    private fun getDepartDay(day: String?): String =
        when (day) {
            yesterday -> departYesterday
            today -> departToday
            tomorrow -> departTomorrow
            else -> departToday
        }

    private fun getArrivalDay(day: String?): String =
        when (day) {
            yesterday -> arrivalYesterday
            today -> arrivalToday
            tomorrow -> arrivalTomorrow
            else -> arrivalToday
        }

    fun getArrival() : MutableList<FlightData> {
        val listFlightData = mutableListOf<FlightData>()
         val doc = Jsoup.connect(getArrivalDay(daysURL))
              .userAgent("Mozilla/5.0 (X11; Linux x86_64)")
              .method(Connection.Method.GET)
              .timeout(15000)
              .ignoreHttpErrors(true)
              .get()
          val elements: Elements = doc.select("div.departureboardBlock")
              .select("ul.boardList")
              .select("div.dataListBlock")
              .select("ul.dataList")
          val elementsSecond: Elements = doc.select("div.departureboardBlock")
              .select("ul.boardList")
              .select("div.detailsBlock")
              .select("ul.charList")

        for (x in 0 until elements.size) {
              val element = elements[x]
              val planTime = element.select("li.item-plan").select("span.value").text().toString()
              val factTime = element.select("li.item-time").select("span.value").text().toString()
              val purpose = element.select("li.item-destination").select("span.value").text().toString()
              val status = element.select("li.item-status").select("span.value").text().toString()
              val airplane = elementsSecond[x].child(3).select("li.item").select("span.value").text().toString()
              val flight = element.select("li.item-flight").select("span.value").text().toString()
              val company = elementsSecond[x].child(4).select("li.item").select("span.value").text().toString()
              val purposeAD = elementsSecond[x].child(2).select("li.item").select("span.value").text().toString()
              val phone = elementsSecond[x].child(5).select("li.item").select("span.value").text().toString()

              val flightData = FlightData(
                  planTime,
                  factTime,
                  purpose,
                  status,
                  airplane,
                  flight,
                  company,
                  purposeAD,
                  phone )
              listFlightData.add(flightData)
          }
        return listFlightData
    }

   fun getDepart() : MutableList<FlightData>{
        val listFlightData = mutableListOf<FlightData>()

        val doc = Jsoup.connect(getDepartDay(daysURL))
            .userAgent("Mozilla/5.0 (X11; Linux x86_64)")
            .method(Connection.Method.GET)
            .timeout(15000)
            .ignoreHttpErrors(true)
            .get()
       val elementsFirst: Elements = doc.select("div.departureboardBlock")
           .select("ul.boardList")
           .select("div.dataListBlock")
           .select("ul.dataList")

       val elementsSecond: Elements = doc.select("div.departureboardBlock")
           .select("ul.boardList")
           .select("div.detailsBlock")
           .select("ul.charList")

       for(x in 0 until elementsFirst.size){
                val element = elementsFirst[x]
                val planTime = element.select("li.item-plan").select("span.value").text().toString()
                val factTime = element.select("li.item-time").select("span.value").text().toString()
                val purpose = element.select("li.item-destination").select("span.value").text().toString()
                val status = element.select("li.item-status").select("span.value").text().toString()
                val airplane = elementsSecond[x].child(3).select("li.item").select("span.value").text().toString()
                val flight = element.select("li.item-flight").select("span.value").text().toString() + " " + element.select("li.item-flight").select("div.value").text().toString()
                val company = elementsSecond[x].child(4).select("li.item").select("span.value").text().toString()
                val purposeAD = elementsSecond[x].child(2).select("li.item").select("span.value").text().toString()
                val phone = elementsSecond[x].child(5).select("li.item").select("span.value").text().toString()
           val flightData = FlightData(
                    planTime,
                    factTime,
                    purpose,
                    status,
                    airplane,
                    flight,
                    company,
                    purposeAD,
                    phone)
                listFlightData.add(flightData)

            }
        return listFlightData
    }
}