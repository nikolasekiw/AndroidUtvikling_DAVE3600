package com.example.merfragmenter;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class VisScriptActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);

        //i stående modus på telefonen. Hvis vi klikker inn på en av radene blir vi sendt til scriptactivity.
        Intent i = this.getIntent(); //får tak i intenten med getIntent
        String bnavn = i.getExtras().getString("scriptnavn"); //får tak i navnet på scriptet som skal vises. Dataene jeg sendte med denne aktiviteten fra hocedaktkviteten. Nøkkelen er scriptnavn og
        VisScriptFragment visscript = new VisScriptFragment(); //må lage denne og det har vi kodet. Den vil kjøre onCreate metoden sin og lage webview for oss.
        visscript.init(bnavn); //visscript er kalt fra mainactiviry, sender med hvilket filnavn som er trykket på. Her sender vi med navnet på scriptet som skal vises og den blir satt til lokal variabel i fragmentet og det er det innholdet som vil vises i webview
        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager(); //må sette i gang transaksjon slik at den legger vårt fragment i aktiviteten
        fragmentManager.beginTransaction().add(android.R.id.content, visscript).commit(); //content = hvilket fragment vi skal legge og vissctipt =  hvilket container vi skal legge
    }
}
