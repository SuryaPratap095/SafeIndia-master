package com.example.surya.safeindia;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
     //   double latitude=0.00;
       // double longitude=0.00;
        Geocoder geocoder=new Geocoder(this);
        mMap.setMyLocationEnabled(true);

//        // Add a marker in Sydney and move the camera
//        List<Address> addressList=null;
//        try {
//            addressList=geocoder.getFromLocationName("eiffel tower",1);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
      //  Address address=addressList.get(0);
       // LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());

        LatLng sydney = new LatLng(40.7128,74.0059);
        LatLng India=new LatLng(18.5204,73.8567);

        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            String provider;
            provider=locationManager.getBestProvider(criteria,true);
            //Location location = locationManager.getLastKnownLocation(locationManager
              //      .getBestProvider(criteria, false));
            Location myLocation=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            boolean isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNWEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

           if(!isNWEnabled) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Please select the network" );
                dialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        // TODO Auto-generated method stub
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 100);
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        // TODO Auto-generated method stub
                    }
                });
                dialog.show();
                // no network provider is enabled
               Log.e("Current Location", "Current Lat Lng is Null");
            }
            else
            {
                if(isGPSEnabled){
                    if(myLocation==null){
                        if(locationManager!=null){
                            myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            double latitude = myLocation.getLatitude();
                            double longitude = myLocation.getLongitude();
                            LatLng latlng = new LatLng(latitude, longitude);
                            //myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            //double latitude = myLocation.getLatitude();
                            //double longitude = myLocation.getLongitude();
                            //String lati = String.valueOf(latitude);
                            //String longi = String.valueOf(longitude);
                            // Create a LatLng object for the current location
                            //LatLng latlng = new LatLng(latitude, longitude);
                            // set map type
                            //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            //CameraPosition cameraPosition = new CameraPosition.Builder().target(latlng).zoom(12).build();

                            //mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                            // Show the current location in Google Map
                            // mMap.moveCamera(newLatLng(latlng));
                            // Zoom in the Google Map
                            //mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
                        }
                    }
                }
                else{
                    if(isNWEnabled){
                        if(myLocation==null){
                            if (locationManager != null) {
                                myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                double latitude = myLocation.getLatitude();
                                double longitude = myLocation.getLongitude();
                                LatLng latlng = new LatLng(latitude, longitude);
                                //double latitude = myLocation.getLatitude();
                                //double longitude = myLocation.getLongitude();

                                // Create a LatLng object for the current location
                                //LatLng latlng = new LatLng(latitude, longitude);
                                // set map type
                                //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                                // Zoom in the Google Map
                                //CameraPosition cameraPosition = new CameraPosition.Builder().target(latlng).zoom(12).build();

                                //mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                //mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
                                // Show the current location in Google Map
                                //mMap.moveCamera(newLatLng(latlng));
                            }
                        }
                    }
                }
            }
              final LocationListener locationListner=new LocationListener() {
                 @Override
                 public void onLocationChanged(Location location) {
                       double longitude=location.getLongitude();
                        double latitude=location.getLongitude();
                 }

                 @Override
                 public void onStatusChanged(String provider, int status, Bundle extras) {

                 }

                 @Override
                 public void onProviderEnabled(String provider) {

                 }

                 @Override
                 public void onProviderDisabled(String provider) {

                 }
             };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,10,locationListner);


           // Location location=mMap.getMyLocation();
             //latitude = location.getLatitude();
              //longitude = location.getLongitude();

            double latitude = myLocation.getLatitude();
            double longitude = myLocation.getLongitude();
            LatLng latlng = new LatLng(latitude, longitude);
            Toast.makeText(this,"latitude "+longitude,Toast.LENGTH_LONG).show();
            CameraPosition cameraPosition = new CameraPosition.Builder().target(latlng).zoom(15).build();
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            //mMap.addMarker(new MarkerOptions().position(cameraPosition).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        catch (SecurityException sc){
            sc.printStackTrace();
        }




//        mMap.addMarker(new MarkerOptions().position(India).title("Marker"));
//       mMap.setMyLocationEnabled(true);
//        //myLocation=mMap.getMyLocation();
//        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),15));
//        mMap.animateCamera(CameraUpdateFactory.newLatLng(India));
    }
}
