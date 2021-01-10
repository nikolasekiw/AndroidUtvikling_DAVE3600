package com.example.fragmenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DialogActivity extends AppCompatActivity implements MyDialog.DialogClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onYesClick(){
        finish();
    }

    @Override
    public void onNoClick(){
        return;
    }

    //OnClick metoden fra layout
    public void visDialog(View v){
        DialogFragment dialog = new MyDialog();
        dialog.show(getFragmentManager(), "Avslutt");
    }

    //OnClick metoden fra layout
    public void visPreferences(View v){
        Intent intent = new Intent(this, Preferanser.class);
        startActivity(intent);
    }
}