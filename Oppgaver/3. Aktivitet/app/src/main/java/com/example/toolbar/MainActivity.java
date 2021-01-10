package com.example.toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.mintoolbar); //finner Toolbaren
        myToolbar.inflateMenu(R.menu.minmeny); //her sier jeg at Toolbar skal ha en meny
        setActionBar(myToolbar); //her vil den lage menyen
    }

    //Metode for å lage Toolbar fra minmeny.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.minmeny, menu);
        return true;
    }

    @Override
    //Metode for å legge inn valg i menyen
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.aktivitet2:
                Intent i2 = new Intent(this, Aktivitet2.class); //lager intent
                EditText et2 = (EditText) findViewById(R.id.hovedtekst); //henter det som ble skrevet i EditText i MainActivity
                String tekst2 = et2.getText().toString();
                i2.putExtra("FRAHOVED", tekst2);
                startActivity(i2);
                break;
            case R.id.aktivitet3:
                Intent i3 = new Intent(this, Aktivitet3.class);
                EditText et3 = (EditText) findViewById(R.id.hovedtekst); //henter det som ble skrevet i EditText i MainActivity
                String tekst3 = et3.getText().toString();
                i3.putExtra("FRAHOVED", tekst3);
                startActivity(i3);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 5){
            if(resultCode == RESULT_OK){
                String resultat = data.getStringExtra("fraaktivitet3");
                EditText et = (EditText) findViewById(R.id.hovedtekst);
                et.setText(resultat);
            }
            }
        }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "Er i onCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "Er i onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "Er i onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "Er i onStart");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("TAG", "Er i onStop");
    }
}