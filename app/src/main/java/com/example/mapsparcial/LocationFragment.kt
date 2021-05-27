package com.example.mapsparcial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.mapsparcial.databinding.FragmentLocationBinding
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_location.*


@Suppress("UNREACHABLE_CODE")
class LocationFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: FragmentLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate<FragmentLocationBinding>(inflater, R.layout.fragment_location, container, false)

        return binding.root


    }


    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        buttonCoordenadas.setOnClickListener {

            val activity = requireActivity() as TabsActivity // Get data from activity

            // Set data to Class Coord
            activity.data = Coordenadas(
                    lat = value1.text.toString(),
                    lon = value2.text.toString()
            )

           activity.showFragment()
        }
        //1. LocationRequest
        val locationRequest = LocationRequest.create()
        locationRequest.interval = 1000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        //2. Callback
        val locationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(location: LocationResult) {
                super.onLocationResult(location)
                val latitude = location.lastLocation.latitude
                val longitude = location.lastLocation.longitude
                binding.latitudeTextView.text = latitude.toString()
                binding.longitudeTextView.text = longitude.toString()
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }
}