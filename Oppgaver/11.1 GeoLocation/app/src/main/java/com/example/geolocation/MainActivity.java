package com.example.geolocation;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText navn;
    TextView koordinater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navn= (EditText)findViewById(R.id.lokasjon);
        koordinater = (TextView) findViewById(R.id.koordinater);
    }

    public String getLocationFromAddress(View v) {
        String strAddress="";
        strAddress = navn.getText().toString();
        Geocoder coder = new Geocoder(this);
        List<Address> address;

        try {
            address = coder.getFromLocationName(strAddress, 1);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            koordinater.setText(lat + "," + lng);
            return lat + "," + lng;
        } catch (Exception e) {
            return null;
        }
    }
}