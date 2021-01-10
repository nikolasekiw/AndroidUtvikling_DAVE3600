package mattespill.com.example.myapplication;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Statistikk extends AppCompatActivity {
    TextView antallRiktig, antallFeil;
    Integer totaltAntallRiktige, totaltAntallFeil, currentAntallRiktig, currentAntallFeil;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String valgtSprak = pref.getString("velgSprak_preference", "no");
        settLand(valgtSprak);

        setContentView(R.layout.activity_statistikk);

        antallRiktig = findViewById(R.id.txtStat_vunnet_svar);
        antallFeil = findViewById(R.id.txtStat_tapt_svar);

        sp = getApplicationContext().getSharedPreferences("Statistikk", Context.MODE_PRIVATE);

        currentAntallRiktig = sp.getInt("antallRiktig", 0);
        currentAntallFeil = sp.getInt("antallFeil", 0);

        totaltAntallRiktige = sp.getInt("totaltAntallRiktige", 0);
        totaltAntallFeil = sp.getInt("totaltAntallFeil", 0);

        totaltAntallRiktige += currentAntallRiktig;
        totaltAntallFeil += currentAntallFeil;

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("totaltAntallRiktige", totaltAntallRiktige);
        editor.putInt("totaltAntallFeil", totaltAntallFeil);
        editor.apply();

        antallRiktig.setText(" "+ totaltAntallRiktige);
        antallFeil.setText(" "+ totaltAntallFeil);
    }

    public void settLand(String landskode){
        Resources res = getResources();
        DisplayMetrics dm =  res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        Locale locale = new Locale(landskode);
        cf.locale = locale;
        res.updateConfiguration(cf, dm);

    }

    public void slett(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.stat_slettBtn))
                .setPositiveButton(getResources().getString(R.string.ja), (dialogInterface, i) -> slettStatistikken())
                .setNegativeButton(getResources().getString(R.string.nei), null)
                .show();
    }
    public void slettStatistikken(){
        totaltAntallRiktige = 0;
        totaltAntallFeil = 0;
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("totaltAntallRiktige", totaltAntallRiktige);
        editor.putInt("totaltAntallFeil", totaltAntallFeil);
        editor.putInt("antallRiktig", 0);
        editor.putInt("antallFeil", 0);
        editor.apply();

        antallRiktig.setText(totaltAntallRiktige.toString());
        antallFeil.setText(totaltAntallFeil.toString());
    }
    @Override
    protected void onSaveInstanceState (Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt("totaltAntallRiktig", totaltAntallRiktige);
        saveInstanceState.putInt("totaltAntallFeil", totaltAntallFeil);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        totaltAntallRiktige = savedInstanceState.getInt("totaltAntallRiktig");
        totaltAntallFeil = savedInstanceState.getInt("totaltAntallFeil");
    }

}