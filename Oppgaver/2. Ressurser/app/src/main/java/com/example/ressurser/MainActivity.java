package com.example.ressurser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Vår aktivitet utvides med aktiviteten i android systemet
 * Aktiviteten i androidsystemet kjører masse kode først, så vil den koden
 * vi legger til oppå den aktiviteten utføres her (R.layout.activity_main)
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Metoden onCreeate lages når aktiviteten opprettes. Først må vi kjøre all koden til grunnobjektet,
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * R står for ressursfil. I det øyeblikket vi kjører et program, så vil den lese alle ressurser
         * som ligger der, alle knappene, alle layputer, alle stringer osv. Så vil den lage en
         * ressursfil med alle ressursene vi bruker i prosjektet og nummerere de og gi de en unik Id.
         *
         * Det vi ber om i koden under er at den skal lese activity_main fila, så skal den generere skjermen videre.
         */
        setContentView(R.layout.activity_main);
    }
}