package com.example.surya.safeindia.Tabs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.surya.safeindia.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by surya on 27/7/16.
 */
public class MapTab extends android.support.v4.app.Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.maps_tab_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.mapTab);
       mapFragment.getMapAsync(this);
        return inflater.inflate(R.layout.maps_tab_layout,container,false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(40.7128,74.0059);
        LatLng India=new LatLng(40.7142700,-74.0059700);
        mMap.addMarker(new MarkerOptions().position(India).title("Marker in Sydney"));
        mMap.setMyLocationEnabled(true);

    }
}
