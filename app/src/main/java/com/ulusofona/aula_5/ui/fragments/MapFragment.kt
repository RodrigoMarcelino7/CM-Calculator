package com.ulusofona.aula_5.ui.fragments

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.ulusofona.aula_5.data.sensors.location.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ulusofona.aula_5.R
import com.ulusofona.aula_5.ui.viewmodels.MapViewModel
import kotlinx.android.synthetic.main.fragment_maps.*
import kotlinx.android.synthetic.main.fragment_maps.view.map_view

const val REQUEST_CODE = 100

class MapsFragment : PermissionedFragment(REQUEST_CODE), OnMapReadyCallback, OnLocationChangedListener {

    private var map: GoogleMap? = null

    private lateinit var mapViewModel : MapViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_maps, container, false)
        view.map_view.onCreate(savedInstanceState)
        return view
    }

    override fun onRequestPermissionsSuccess() {
        mapViewModel.registerListener(this)
        FusedLocation.start(activity as Context)
        map_view.getMapAsync(this)
        map_view.onResume()
    }

    override fun onRequestPermissionsFailure() {
        //Implementar mensagem de erro
    }

    override fun onStart() {
        super.onRequestPermissions(activity?.baseContext!!, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))
        super.onStart()
    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map
        val lusofona = LatLng(38.757950, -9.153043)
        map?.addMarker( MarkerOptions().position(lusofona).title("Computação Móvel is my love MENTIRAAAAAAA"))
        map?.animateCamera((CameraUpdateFactory.newLatLngZoom(lusofona,15F)))
    }

    override fun onLocationChanged(locationResult: LocationResult) {
        val location = locationResult.lastLocation
        map?.clear()
        map?.addMarker(MarkerOptions().position(LatLng(location!!.latitude, location!!.longitude)).title("O Bruno está aqui!"))
        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location!!.latitude, location!!.longitude), 15F))
    }
}