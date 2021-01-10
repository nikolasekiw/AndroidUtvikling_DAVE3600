package com.example.testlekebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void send(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.oslomet.no"));
        Intent velger = intent.createChooser(intent,"Velg");
        startActivity(velger);
    }
}