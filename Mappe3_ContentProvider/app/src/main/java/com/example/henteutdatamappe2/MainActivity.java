package com.example.henteutdatamappe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static String PROVIDER="com.example.s331153s333975mappe2";
    public static final Uri CONTENT_URI= Uri.parse("content://"+ PROVIDER + "/kontakt");
    public static final Uri CONTENT_URI2= Uri.parse("content://"+ PROVIDER + "/kontakt/#");
    private static final String _KID = "_KID";
    private static final String KONTAKT_NAVN = "Navn";
    private static final String TELEFON = "Telefon";
    TextView txtkontaktID;
    EditText kontaktID;
    TextView visKontakt;
    ListView visKontakterLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtkontaktID = (TextView)findViewById(R.id.txtkontaktID);
        kontaktID = (EditText) findViewById(R.id.kontaktID);
        visKontakterLV = (ListView) findViewById(R.id.visKontakterLV);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("\tFinn kontakter");
        setActionBar(toolbar);
    }

    public void visalle(View v) {
        String tekst;
        ArrayList<String> visKontakter = new ArrayList<String>();

        Cursor cur =getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (cur.moveToFirst()) {
            do {
                tekst = "ID: " + (cur.getString(0)) + ", navn: " + (cur.getString(1)) + ", telefon: "+ (cur.getString(2))  + "\r\n";
                visKontakter.add(tekst);
            }
            while (cur.moveToNext());
            cur.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, visKontakter){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view =super.getView(position, convertView, parent);
                    TextView textView=(TextView) view.findViewById(android.R.id.text1);
                    textView.setTextColor(Color.BLACK);
                    return view;
                }
            };

            visKontakterLV.setAdapter(adapter);
        }
    }

    public void visEn(View v) {
        String tekst;
        ArrayList<String> visKontakter = new ArrayList<String>();

        String [] projection = new String[] { _KID, KONTAKT_NAVN, TELEFON };

        String kontaktid = kontaktID.getText().toString();
        Uri CONTENT_URI2= Uri.parse("content://"+ PROVIDER + "/kontakt/"+kontaktid);

        Cursor cur =getContentResolver().query(CONTENT_URI2, projection, null, null, null);

        if (cur.moveToFirst()) {
            do {
                tekst = "ID: " + (cur.getString(0)) + ", navn: " + (cur.getString(1)) + ", telefon: "+ (cur.getString(2))  + "\r\n";
                visKontakter.add(tekst);
            }
            while (cur.moveToNext());
            cur.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, visKontakter){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view =super.getView(position, convertView, parent);
                    TextView textView=(TextView) view.findViewById(android.R.id.text1);
                    textView.setTextColor(Color.BLACK);
                    return view;
                }
            };

            visKontakterLV.setAdapter(adapter);
        }
    }
}