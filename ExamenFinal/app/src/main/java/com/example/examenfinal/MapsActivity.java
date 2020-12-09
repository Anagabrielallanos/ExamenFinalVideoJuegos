package com.example.examenfinal;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.examenfinal.Entity.PokemonClass;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        PokemonClass aClass = (PokemonClass) getIntent().getSerializableExtra("Pokemon");
        assert aClass != null;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(aClass.getLatitude(), aClass.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title(aClass.getNombre()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f));
    }
}