package com.example.merfragmenter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListeFragment.UrlEndret { //her sier vi at vi implementerer interfacet vi har i listefragmentet.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //det er her det bestemmes om det er stående eller liggende modus.
    @Override
    public void linkEndret(String link) { //her kjøres metoden fra interfacet.
        if(findViewById(R.id.scriptfragment) != null){ //her er logikken for om det er stående eller liggende. Hvis den ikke er null så vil det si at den har laget dette webviewet vårt
            VisScriptFragment visscript = (VisScriptFragment) getSupportFragmentManager().findFragmentById(R.id.scriptfragment); //prøver å finne scriptfragmentet i linje over. Og hvis det ikke finnes... Sjekker at scriptfragmentet. Første gang den lager scriptfragment, initierer og sier hvilken fil som skal vises i det.
            if(visscript == null) {
                visscript = new VisScriptFragment();
                visscript.init(link);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                trans.replace(R.id.listefragment, visscript);
                trans.commit();
            } else {
                visscript.updateUrl(link);
                //hvis den ikke har scriptfragment er den i stående modus. Lager intet og sier hva vi s
            }
            } else { //den har listefragment og scriptfragment.
                Intent i = new Intent(this, VisScriptActivity.class);
                i.putExtra("scriptnavn", link); //link er navnet på filen som skal vises. Sender intenten med startActivity
                startActivity(i); //oppdaterer webview med det nye viewet.
            }
        }
    }
