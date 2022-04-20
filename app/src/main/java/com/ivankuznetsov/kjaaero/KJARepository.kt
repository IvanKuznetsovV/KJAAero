package com.ivankuznetsov.kjaaero

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class KJARepository(var daysURL: Int?) {

    private val urLs: URLs = URLs()

    fun getArrival() : MutableList<FlightData> {
        val listFlightData = mutableListOf<FlightData>()
         val doc = Jsoup.connect(urLs.getArrivalDay(daysURL))
              .userAgent("Mozilla/5.0 (X11; Linux x86_64)")
              .method(Connection.Method.GET)
              .timeout(15000)
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

              val flightData = FlightData(
                  planTime,
                  factTime,
                  purpose,
                  status,
                  airplane,
                  flight,
                  company,
                  purposeAD,
              )
              listFlightData.add(flightData)
          }
        return listFlightData
    }

   fun getDepart() : MutableList<FlightData>{
        val listFlightData = mutableListOf<FlightData>()

        val doc = Jsoup.connect(urLs.getDepartDay(daysURL))
            .userAgent("Mozilla/5.0 (X11; Linux x86_64)")
            .method(Connection.Method.GET)
            .timeout(15000)
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

           val flightData = FlightData(
                    planTime,
                    factTime,
                    purpose,
                    status,
                    airplane,
                    flight,
                    company,
                    purposeAD,
           )
                listFlightData.add(flightData)

            }
        return listFlightData
    }
}