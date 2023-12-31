package com.noole.lab12;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.noole.lab12.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Marker pointA, pointB, pointC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.noole.lab12.databinding.ActivityMapsBinding binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        pointA = googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").snippet("Longer info text displayed under title"));
        pointA.setTag("A");
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,17));
        GroundOverlayOptions locationOverlay = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.overlay)).position(sydney,50);
        googleMap.addGroundOverlay(locationOverlay);

        googleMap.setOnMarkerClickListener(marker -> {
            if (marker.getTag() == "A"){
                LatLng start = new LatLng(-34, 151);
                LatLng end = new LatLng(-34, 151);
                pointB = googleMap.addMarker(new MarkerOptions().position(start).title("Starting point").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                pointC = googleMap.addMarker(new MarkerOptions().position(start).title("Ending point").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                Polyline polyline = googleMap.addPolyline(new PolylineOptions().add(
                        start,
                        new LatLng(-34, 151),
                        new LatLng(-34, 151),
                        end
                ).width(2).color(Color.RED));
                polyline.setTag("Route 1");

            }
            return true;
        });
    }
}