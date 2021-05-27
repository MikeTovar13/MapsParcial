package com.example.mapsparcial

import android.annotation.SuppressLint
import android.graphics.Camera
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener


class MapFragment : SupportMapFragment(), OnMapReadyCallback, OnSuccessListener<Location>,
    GoogleMap.OnMapLongClickListener {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        getMapAsync(this)

        val activity = requireActivity() as TabsActivity // Get data from activity

        // Set data to map
        var lat = activity.data!!.lat
        var lon = activity.data!!.lon


        println(lat)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap) {
        this.map = p0
        this.map.setOnMapLongClickListener(this)
        fusedLocationClient.lastLocation.addOnSuccessListener(this)
    }

    override fun onSuccess(p0: Location) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(p0.latitude, p0.longitude), 1f))
        map.addMarker(MarkerOptions().position(LatLng(p0.latitude, p0.longitude)).title("Posición Actual"))
    }

    override fun onMapLongClick(p0: LatLng) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(p0.latitude, p0.longitude), 1f))
        map.addMarker(MarkerOptions().position(LatLng(p0.latitude, p0.longitude)).title("Destino"))
    }

}