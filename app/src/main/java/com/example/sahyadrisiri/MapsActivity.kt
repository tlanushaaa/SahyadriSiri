package com.example.sahyadrisiri

import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true

        val sharedPref = getSharedPreferences("WaterData", MODE_PRIVATE)
        val reports = sharedPref.getString("reports", "") ?: ""

        val lines = reports.split("\n")

        for (line in lines) {

            try {

                // Get location name
                val locationName = line.substringBefore("-").trim()

                val geocoder = Geocoder(this)
                val addressList = geocoder.getFromLocationName(locationName, 1)

                if (addressList != null && addressList.isNotEmpty()) {

                    val address = addressList[0]

                    val latLng = LatLng(address.latitude, address.longitude)

                    val marker = MarkerOptions()
                        .position(latLng)
                        .title(line)

                    // Green marker for Clean water
                    if (line.contains("Clean", true)) {

                        marker.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN
                            )
                        )

                    } else {

                        // Red marker for polluted water
                        marker.icon(
                            BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED
                            )
                        )
                    }

                    mMap.addMarker(marker)

                    // Zoom to location
                    mMap.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(latLng, 5f)
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}