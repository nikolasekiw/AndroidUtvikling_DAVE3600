package mattespill.com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.ViewAnimator;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button startSpill;
    private Button statistikk;
    private Button preferanser;
    String aktland;

    public void settLand(String landskode){
        Resources res = getResources();
        DisplayMetrics dm =  res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        Locale locale = new Locale(landskode);
        cf.locale = locale;
        res.updateConfiguration(cf, dm);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String valgtSprak = pref.getString("velgSprak_preference", "no");
        settLand(valgtSprak);
        aktland=valgtSprak;

        setContentView(R.layout.activity_main);

        startSpill = findViewById(R.id.startSpill);
        startSpill.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, StartSpill.class);
                startActivity(intent);
            }
        });

        statistikk = findViewById(R.id.seStatistikk);
        statistikk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Statistikk.class);
                startActivity(intent);
            }
        });

        preferanser = findViewById(R.id.preferanser);
        preferanser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Preferanser.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String valgtSprak = pref.getString("velgSprak_preference", "no");
        if (valgtSprak.equals(aktland)==false) {
            recreate();
            aktland=valgtSprak;
        }

    }
}