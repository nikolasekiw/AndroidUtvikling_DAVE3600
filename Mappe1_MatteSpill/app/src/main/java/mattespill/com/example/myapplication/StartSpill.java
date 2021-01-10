package mattespill.com.example.myapplication;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class StartSpill extends AppCompatActivity {
    List<String> oppgArray;
    List<String> svarArray;
    List<String> fullførteRiktigeSvar = new ArrayList<>();
    List<String> fullførteFeilSvar = new ArrayList<>();
    TextView textBrukersvar;
    TextView textRegnestykket;
    TextView textOppgaverIgjen;
    Integer oppgaverUtført = 0;
    Integer antallRiktig = 0;
    Integer antallFeil = 0;
    Integer antallStykker;
    Integer teller = 0;
    Integer indeks = -1;
    Random random;
    String brukersvar = "";
    SharedPreferences sp;

    /*
    @Override
    public void onYesClick(){
        finish();
    }

    @Override
    public void onNoClick(){
        return;
    }

    public void visDialog(View v){
        DialogFragment dialog = new MyDialog();
        dialog.show(getFragmentManager(),"Avslutt");
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String valgtSprak = pref.getString("velgSprak_preference", "no");
        settLand(valgtSprak);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_spill);

        SharedPreferences pref2 = PreferenceManager.getDefaultSharedPreferences(this);
        String antall = pref2.getString("antallstykker_preference", "0");

        antallStykker = Integer.valueOf(antall);

        oppgArray = Arrays.asList(getResources().getStringArray(R.array.regnestykker));
        svarArray = Arrays.asList(getResources().getStringArray(R.array.regnestykkerSvar));

        textRegnestykket = (TextView)findViewById(R.id.textRegnestykket);
        textBrukersvar = (TextView)findViewById(R.id.textBrukerSvar);
        textOppgaverIgjen = (TextView)findViewById(R.id.textOppgaverIgjen);
        fullførteRiktigeSvar = new ArrayList<>();
        fullførteFeilSvar = new ArrayList<>();

        random = new Random();
        randomGenerator();

        //Lytter på knappene
        final Button btn1 = (Button)findViewById(R.id.btn1);
        final Button btn2 = (Button)findViewById(R.id.btn2);
        final Button btn3 = (Button)findViewById(R.id.btn3);
        final Button btn4 = (Button)findViewById(R.id.btn4);
        final Button btn5 = (Button)findViewById(R.id.btn5);
        final Button btn6 = (Button)findViewById(R.id.btn6);
        final Button btn7 = (Button)findViewById(R.id.btn7);
        final Button btn8 = (Button)findViewById(R.id.btn8);
        final Button btn9 = (Button)findViewById(R.id.btn9);
        final Button btn0 = (Button)findViewById(R.id.btn0);


        btn1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                settNummer(1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                settNummer(2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                settNummer(3);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                settNummer(4);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                settNummer(5);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                settNummer(6);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                settNummer(7);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                settNummer(8);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                settNummer(9);
            }
        });

        btn0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                settNummer(0);
            }
        });


    }

    public void settLand(String landskode){
        Resources res = getResources();
        DisplayMetrics dm =  res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        Locale locale = new Locale(landskode);
        cf.locale = locale;
        res.updateConfiguration(cf, dm);

    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(StartSpill.this);
        builder.setMessage(getResources().getString(R.string.slettFremgang));
        builder.setCancelable(true);
        builder.setNegativeButton(getResources().getString(R.string.nei), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.ja), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                antallRiktig = 0;
                antallFeil = 0;
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void randomGenerator(){
        if(teller == antallStykker){ //Avslutter spillet dersom antall stykker er nådd

            sp = getSharedPreferences("Statistikk", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("antallRiktig", antallRiktig);
            editor.putInt("antallFeil", antallFeil);
            editor.commit();

           /* int i = 0;
            String riktigResultater = "Du svarte riktig på: n/";
            while(i < fullførteRiktigeSvar.size()){
                riktigResultater += fullførteRiktigeSvar.get(i)+"/n";
                i++;
            }
            i = 0;
            String feilResultater = "Du svarte feil på: n/";
            while(i < fullførteFeilSvar.size()){
                feilResultater += fullførteFeilSvar.get(i)+"/n";
                i++;
            }*/




            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage(getResources().getString(R.string.nyttSpill))
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.ja), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                StartSpill.this.finish();
                                Intent intent = new Intent(StartSpill.this, StartSpill.this.getClass());
                                StartSpill.this.startActivity(intent);
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.nei), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                StartSpill.this.finish();
                            }
                        })
                        .show();
            }
        else { //Spillet fortsetter, nytt regnestykke gis ut
            teller += 1;
            int øvreGrense = 25;
            int forrigeIndeks = indeks;
            indeks = random.nextInt(øvreGrense); //får ut random tall fra 0 til 24 som er indeks i arrayet med regnestykker

            while (indeks == forrigeIndeks) {
                indeks = random.nextInt(øvreGrense);
            }
            textRegnestykket.setText(oppgArray.get(indeks)+" = ");
        }
    }

    public void resetSvar(View v){
        brukersvar = "";
        textBrukersvar.setText(brukersvar);
    }

    //Metode som setter brukerens svar i applikasjonen
    public void settNummer(int nummer){
        String input = String.valueOf(nummer);
        brukersvar = brukersvar + input;
        textBrukersvar.setText(brukersvar);
    }

    //Metode som sjekker om svar er riktig/feil
    public void ok(View v){
        String svar = textBrukersvar.getText().toString();
        String riktigSvar = svarArray.get(indeks);
        Log.d("n i ok-metoden", String.valueOf(indeks));

        if(svar.equals(riktigSvar)){
            Toast.makeText(StartSpill.this, getResources().getString(R.string.riktig), Toast.LENGTH_SHORT).show();
            fullførteRiktigeSvar.add(oppgArray.get(indeks));
            antallRiktig = antallRiktig +  1;
            oppgaverUtført = oppgaverUtført + 1;
            brukersvar = "";
            textBrukersvar.setText(brukersvar);
            randomGenerator();
        }
        else{
            Toast.makeText(StartSpill.this, getResources().getString(R.string.feil), Toast.LENGTH_SHORT).show();
            brukersvar = "";
            fullførteFeilSvar.add(oppgArray.get(indeks));
            antallFeil = antallFeil + 1;
            oppgaverUtført = oppgaverUtført + 1;
            textBrukersvar.setText(brukersvar);
            randomGenerator();
        }
        textOppgaverIgjen.setText(oppgaverUtført.toString() + "/" + antallStykker);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("antallRiktig", antallRiktig);
        savedInstanceState.putInt("antallFeil", antallFeil);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        antallRiktig = savedInstanceState.getInt("antallRiktig");
        antallFeil = savedInstanceState.getInt("antallFeil");
    }

/*
    @Override
    public void onYesClick() {

    }

    @Override
    public void onNoClick() {

    }

    public void visDialog(View v){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }*/
}

