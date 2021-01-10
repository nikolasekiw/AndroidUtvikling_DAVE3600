package com.example.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class Aktivitet3 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivitet2);

        /*TextView tekst = (TextView) findViewById(R.id.aktivitet2tekst);
        tekst.setText(getIntent().getStringExtra("FRAHOVED"));*/

        Toolbar myToolbar = (Toolbar) findViewById(R.id.akt3bar);
        myToolbar.inflateMenu(R.menu.menu_akt3);
        setActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_akt3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.hoved:
                finish();
                break;
            case R.id.aktivitet2:
                Intent i2 = new Intent(this, Aktivitet2.class);
                startActivity(i2);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
