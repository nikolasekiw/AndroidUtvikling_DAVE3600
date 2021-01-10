package synne.com.example.database;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends Activity {
    EditText navninn;
    EditText telefoninn;
    EditText idinn;
    TextView utskrift;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navninn = (EditText) findViewById(R.id.navn);
        telefoninn = (EditText) findViewById(R.id.telefon);
        idinn = (EditText) findViewById(R.id.min_id);
        utskrift = (TextView) findViewById(R.id.utskrift);
        db = new DBHandler(this);
    }

    public void leggTil(View v){
        Kontakt kontakt = new Kontakt(navninn.getText().toString(),
                telefoninn.getText().toString());
        db.leggTilKontakt(kontakt);
        Log.d("Legg inn: ", "legger til kontakter");
    }

    public void visAlle(View v){
        String tekst = "";
        List<Kontakt> kontakter = db.finnAlleKontakter();
        for (Kontakt kontakt : kontakter) {
            tekst = tekst + "Id: " + kontakt.get_ID() + ",Navn: " +
                    kontakt.getNavn() + " ,Telefon: " +
                    kontakt.getTelefon();
            Log.d("Navn: ", tekst);
        }
        utskrift.setText(tekst);
    }

    public void slett(View v){
        Long kontaktid = (Long.parseLong(idinn.getText().toString()));
        db.slettKontakt(kontaktid);
    }
    public void oppdater(View v){
        Kontakt kontakt = new Kontakt();
        kontakt.setNavn(navninn.getText().toString());
        kontakt.setTelefon(telefoninn.getText().toString());
        kontakt.set_ID(Long.parseLong(idinn.getText().toString()));
        db.oppdaterKontakt(kontakt);
    }
}