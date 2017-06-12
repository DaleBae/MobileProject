package com.example.jermy.securityzone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks
, GoogleApiClient.OnConnectionFailedListener, LocationListener{
    MapFragment mapFr;
    GoogleMap map;
    double latitude;
    double longitude;
    int nearcctv;
    Marker markers;
    EditText txt;
    Geocoder gc;

    int Awning;
    int Airinjector;
    int Fix;


    private final static int MY_LOCATION_REQUEST_CODE = 1000;
    private final static int REQ_PERMISSION = 1000;

    GoogleApiClient mGoogleApiClient = null;
    Location mLastLocation;
    LocationRequest mLocationRequest;

    DatabaseReference storeTable;
    DatabaseReference cctvTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init();
    }
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void init() {


        Intent intent = getIntent();
        Awning = intent.getIntExtra("Awning",0);
        Airinjector = intent.getIntExtra("Airinjector",0);
        Fix = intent.getIntExtra("Fix",0);

        txt = (EditText) findViewById(R.id.txt);
        mapFr = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFr.getMapAsync(this);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000)
                .setSmallestDisplacement(30);
        gc = new Geocoder(this, Locale.KOREAN);

        cctvTable=FirebaseDatabase.getInstance().getReference("SecurityZone/cctv");
        storeTable= FirebaseDatabase.getInstance().getReference("SecurityZone/store");
        Query query=storeTable.orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    try {
                        boolean set=true;

                        JSONObject json=new JSONObject(data.getValue().toString());
                        latitude = Double.parseDouble(json.getString("latitude"));
                        longitude = Double.parseDouble(json.getString("longitude"));
                        nearcctv = Integer.parseInt(json.getString("nearcctv"));
                        if(Awning==1&&json.getString("Awnings").equals("N")) {
                            set=false;
                        }
                        if(Awning==2&&json.getString("Awnings").equals("Y")){
                            set=false;
                        }
                        if(Airinjector==1&&json.getString("Airinjector").equals("N")){
                            set=false;
                        }
                        if(Airinjector==2&&json.getString("Airinjector").equals("Y")){
                            set=false;
                        }
                        if(Fix==1&&json.getString("fix").equals("N")){
                            set=false;
                        }
                        if(Fix==2&&json.getString("fix").equals("Y")){
                            set=false;
                        }
                        if(set) {
                            setMarkers();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void setMarkers(){
        LatLng temp = new LatLng(latitude,longitude);
        markers = map.addMarker(new MarkerOptions()
                .position(temp)
                .title("주변 cctv 개수 : "+nearcctv)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bikemarker)));
    }

    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (checkPermission()) {
            map.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQ_PERMISSION
            );
        }
        UiSettings ui = map.getUiSettings();
        ui.setZoomControlsEnabled(true);


    }
    public void updateMap() {
        final LatLng Loc = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Loc, 16));
    }
    private boolean checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (checkPermission()) {
                    map.setMyLocationEnabled(true);
                    return;
                }
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            Log.i("Location", (String.valueOf("LastLocation" + mLastLocation.getLatitude() + "::" + mLastLocation.getLongitude())));
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            updateMap();
        }
    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        }

    public void searchClick(View view) {
        String p = txt.getText().toString();
        searchPlace(p);
    }
    void searchPlace(String place) {
        try {
            List<Address> addr = gc.getFromLocationName(place, 5);
            if (addr != null) {
                latitude = addr.get(0).getLatitude();
                longitude = addr.get(0).getLongitude();
                updateMap();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
