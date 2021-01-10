package com.example.testavbokcp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HovedTest extends AppCompatActivity {
    public final static String PROVIDER="com.example.contentproviderbok"; //det er det jeg har sagt er autoriteten
    public static final Uri CONTENT_URI= Uri.parse("content://"+ PROVIDER + "/bok"); //lager meg en URI, den virtuelle path jeg har kalt bok
    public static final String TITTEL="Tittel"; //må også vite feltene i tabellen min
    public static final String ID="_id";
    EditText tittel;
    TextView visbok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tittel = findViewById(R.id.tittel);
        Button leggtil = findViewById(R.id.leggtil);
        visbok = findViewById(R.id.vis);
    }

    /**
     * Viktig at leggTIl har et view som parameter, hvis ikke så virker det ikke.
     * View er noe android sender med, vi har sagt at vi har en knapp og at den reagerer på
     * leggTIl og da sender android med view-et til denne metoden. Legger til bok i bok.db.
     * Leser inn tittelen fra EditText. Putter nøkkel/verdi parret i contentvalues ogjentet.
     * Metoden som legger inn vil returneree en URI og den vil være URI til content provider + tallet på id-en
     * til den den har lagt inn. Kaller på content resolver. Bruker insert. Sender med uri
     * og nøkkel/verdi par som skal settes inn.
     */
    public void leggtil(View v){
        ContentValues values = new ContentValues();
        String inn=tittel.getText().toString();
        values.put(TITTEL, inn);
        Uri uri = getContentResolver().insert( CONTENT_URI, values);
        tittel.setText("");
    }

    /**
     * Sjker når jeg trykker på knappen. Har knappen inn som parameter. query-etmdoen retornerer
     * en cursor og det er en peker til et datasett. Når jeg ber CR utføre wuery mot URI-en min, så
     * vil den returnere en cursor som peker på et sett med verdier som er et sett med titler som ligger
     * i bok.db. Legger det i textview og lukker cursor.
     */
    public void visalle(View v) {
        String tekst;
        tekst = "";
        Cursor cur = getContentResolver().query(CONTENT_URI, null, null, null, null); if (cur.moveToFirst()) {
            do {
                tekst = tekst + (cur.getString(1)) + "\r\n"; }
            while (cur.moveToNext());
            cur.close();
            visbok.setText(tekst);
        }
    }
}