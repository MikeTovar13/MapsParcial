package com.example.mapsparcial

import android.os.Parcel
import android.os.Parcelable

class Coordenadas() : Parcelable{

    // Coords
    var lat: String = ""
    var lon: String = ""


    constructor(lat:String , lon:String) : this() {
        this.lat = lat
        this.lon = lon
    }

    constructor(parcel: Parcel) : this() {
        lat = parcel.readString().toString()
        lon = parcel.readString().toString()

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lat)
        parcel.writeString(lon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coordenadas> {
        override fun createFromParcel(parcel: Parcel): Coordenadas {
            return Coordenadas(parcel)
        }

        override fun newArray(size: Int): Array<Coordenadas?> {
            return arrayOfNulls(size)
        }
    }

}
