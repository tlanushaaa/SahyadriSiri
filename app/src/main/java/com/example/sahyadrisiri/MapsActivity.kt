package com.example.sahyadrisiri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.example.sahyadrisiri.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // 🔹 Get stored reports
        val sharedPref = getSharedPreferences("WaterData", MODE_PRIVATE)
        val reports = sharedPref.getString("reports", "") ?: ""

        val lines = reports.split("\n")

        for (line in lines) {

            when {

                // 📍 Bangalore
                line.contains("Bangalore", true) -> {
                    val loc = LatLng(12.9716, 77.5946)

                    val marker = MarkerOptions().position(loc).title(line)

                    if (line.contains("Clean", true)) {
                        marker.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN
                            )
                        )
                    } else {
                        marker.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED
                            )
                        )
                    }

                    mMap.addMarker(marker)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 8f))
                }

                // 📍 Mysore
                line.contains("Mysore", true) -> {
                    val loc = LatLng(12.2958, 76.6394)

                    val marker = MarkerOptions().position(loc).title(line)

                    if (line.contains("Clean", true)) {
                        marker.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN
                            )
                        )
                    } else {
                        marker.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED
                            )
                        )
                    }

                    mMap.addMarker(marker)
                }

                // 📍 Tumkur
                line.contains("Tumkur", true) -> {
                    val loc = LatLng(13.3409, 77.1010)

                    val marker = MarkerOptions().position(loc).title(line)

                    if (line.contains("Clean", true)) {
                        marker.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN
                            )
                        )
                    } else {
                        marker.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED
                            )
                        )
                    }

                    mMap.addMarker(marker)
                }
            }
        }
    }
}