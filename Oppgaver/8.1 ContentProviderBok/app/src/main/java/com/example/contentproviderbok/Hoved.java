package com.example.contentproviderbok;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;

public class Hoved extends AppCompatActivity {
    public static String PROVIDER = "com.example.contentproviderbok";
    public static final Uri CONTENT_URI = Uri.parse("content://"+ PROVIDER + "/bok"); //lager URI av det

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentValues v = new ContentValues(); //lager en Content value og setter inn tittel
        v.put("Tittel", "Sult");
        getContentResolver().insert(CONTENT_URI, v);
        //får tak i CR for URI-en vår skulle sendes til android content resolver objekt som skal gjøre det vi skulle
        //insert = trenger å vite URI til content provider vi skal buke og hvilke data som skal sendes med.
    }
}