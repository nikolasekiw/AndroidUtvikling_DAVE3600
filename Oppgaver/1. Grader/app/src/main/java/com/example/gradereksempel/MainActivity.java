package com.example.gradereksempel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends Activity {

    EditText tekst;

    @Override
    //create metoden utføres når applikasjonen starter
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Denne gjør sånn at appen starter på activity_main.xml? Slik at det er det første vi ser?
    setContentView(R.layout.activity_main);
        tekst=(EditText)findViewById(R.id.temp);

        /**
         * Her importeres begge knappene fra activity_main slik at vi kan bruke de i denne java klassen.
         * Vi har kalt knappene for graderknapp og fahrenheitknapp og det er de navnene som skal brukes videre
         * hvis det skal gjøres noen kall på disse/hente data fra dem (onClick).
         */
        final Button gradknapp=(Button)findViewById(R.id.tilgrader);
        final Button fahrenheitknapp=(Button)findViewById(R.id.tilfahrenheit);

        /**
         * Innverdi er verdien vi henter fra knappen som har blitt trykket på og gjør verdien om
         * fra string til float ved å parse den. Så bruker vi tekst.setText for å sette verdien vi hentet ut
         * inn i vindu igjen og bruker da metoden convertFahrenheitToCelcius for å få konvertert det vi
         * hentet ut til å bli til calcius, så sette resultatet ut igjen. Men hvor kommer det ut? Temp?
         */
        gradknapp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float innverdi = Float.parseFloat(tekst.getText().toString());
                tekst.setText(String.valueOf(convertFahrenheitToCelsius(innverdi)));
            } });

        fahrenheitknapp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            float innverdi = Float.parseFloat(tekst.getText().toString());
            tekst.setText(String.valueOf(convertCelsiusToFahrenheit(innverdi)));
        } });
    }

    //Disse metodene beregner dataene og gjør de om til enten celcius eller fahrenheit.
    float convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }
    float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }
}