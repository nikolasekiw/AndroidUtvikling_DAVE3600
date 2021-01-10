package com.example.fragmenter;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Preferanser extends PreferenceFragment {

    @Override
    //her skulle det egt. stå protected på onCreate, men da fikk jeg feil
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new
                PrefsFragment()).commit();
                }


    public static class PrefsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
