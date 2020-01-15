package com.ebingengeolocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MarkerDetail extends AppCompatActivity {

    private TextView title;
    private TextView latlong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_detail);

        title = this.findViewById(R.id.title);
        latlong = this.findViewById(R.id.latlong);

        title.setText("Test title");
        latlong.setText("48.211211, 9.029968");
    }
}
