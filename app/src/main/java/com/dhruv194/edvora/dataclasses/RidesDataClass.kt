package com.dhruv194.edvora.dataclasses


import com.google.gson.annotations.SerializedName
import kotlin.properties.Delegates

data class RidesDataClass(
    val city: String,
    val date: String,
    @SerializedName("destination_station_code")
    val destinationStationCode: Int,
    val id: Int,
    @SerializedName("map_url")
    val mapUrl: String,
    @SerializedName("origin_station_code")
    val originStationCode: Int,
    val state: String,
    @SerializedName("station_path")
    val stationPath: List<Int>
)


class ComparatorOne : Comparator<RidesDataClass>{
    override fun compare(p0: RidesDataClass?, p1: RidesDataClass?): Int {
      return  p0!!.date.compareTo(p1!!.date)
    }
}
class ComparatorTwo : Comparator<RidesDataClass>{
    private var stationCode by Delegates.notNull<Int>()

    constructor(stationCode:Int){
        this.stationCode = stationCode
    }
    override fun compare(p0: RidesDataClass?, p1: RidesDataClass?): Int {
        var distO = getDistance(p0!!.stationPath)
        var dist1 = getDistance(p1!!.stationPath)
        return distO - dist1
    }

    private fun getDistance(p0: List<Int>?): Int {
         var min = Int.MAX_VALUE
        for (i in p0!!){
            var distance = stationCode-i
            if(distance<0){
                distance = 0-distance
            }
            if (distance< min){
                min = distance
            }
        }
        return min
    }
}

