package com.ebingengeolocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MarkerDetail extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private TextView title;
    private TextView latlong;
    private ListView comments;
    private EditText commentInput;

    private ArrayList<String> commentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_detail);

        title = this.findViewById(R.id.title);
        latlong = this.findViewById(R.id.latlong);
        comments = this.findViewById(R.id.comments);
        commentInput = this.findViewById(R.id.commentInput);

        title.setText("Test title");
        latlong.setText("48.211211, 9.029968");

        commentsList = new ArrayList<>();

        ArrayAdapter<String> commentsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commentsList);

        comments.setAdapter(commentsAdapter);

//        GetComments("");
        commentsList.add("This is a comment");
        commentsList.add("This is another comment");
        commentsList.add("Some more comment");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(null);
        mMap.setOnMarkerDragListener(null);
        mMap.getUiSettings().setScrollGesturesEnabled(false);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(48.211582, 9.027737));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.211211, 9.029968), 15));
    }

    public void GetComments(final String locationId) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.dummy.com/comments/" + locationId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        commentsList.add(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Exception: %s", error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    public void addComment(View view) {
        commentsList.add(commentInput.getText().toString());

        commentInput.setText("");
    }
}
