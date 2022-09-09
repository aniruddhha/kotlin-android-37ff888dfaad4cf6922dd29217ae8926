package com.ani.location.map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.ani.location.map.databinding.ActivityMapsBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.*
import java.util.concurrent.TimeUnit

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val mapPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private fun checkAllGranted() = mapPermissions.all {
        ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

    private val permissionsResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            when {
                (it[Manifest.permission.ACCESS_FINE_LOCATION] ?: false) and (it[Manifest.permission.ACCESS_FINE_LOCATION] ?: false)  -> {
                    Log.i("@ani", "Both permissions granted")
                }
                else -> {
                    Log.i("@ani", "Some permissions are missing")
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val locationRequest = LocationRequest.create().apply {
            interval = TimeUnit.SECONDS.toMillis(5)
            fastestInterval = TimeUnit.SECONDS.toMillis(2)
            maxWaitTime = TimeUnit.MINUTES.toMillis(1)
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                Log.i("@ani", "###################")
                Log.i("@ani", "Lat ${result.lastLocation?.latitude}")
                Log.i("@ani", "Lng ${result.lastLocation?.longitude}")

                result.lastLocation?.run {
                    val currLocation = LatLng(latitude, longitude)
                    mMap.addMarker(
                        MarkerOptions()
                            .position(currLocation)
                            .title("In India")
                    )

                    mMap.addCircle(
                        CircleOptions()
                            .center(currLocation)
                            .fillColor(Color.RED)
                            .radius(30.5)
                    )

//                    mMap.addPolyline(PolylineOptions())

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(currLocation))
                }
            }
        }

        if (
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
        } else {
            permissionsResultLauncher.launch(mapPermissions)
//            ActivityCompat.requestPermissions(this, mapPermissions, 1234)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        // Add a marker in Sydney and move the camera

    }
}