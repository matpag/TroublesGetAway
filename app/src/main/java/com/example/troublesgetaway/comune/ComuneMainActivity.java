package com.example.troublesgetaway.comune;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.troublesgetaway.R;
import com.example.troublesgetaway.utente.Guasti;

public class ComuneMainActivity extends AppCompatActivity {

    Button gestioneGuasti;
    Button listaGuasti;
    Button esci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comune_main);

        gestioneGuasti = findViewById(R.id.btngestione);
        listaGuasti = findViewById(R.id.btnguasti);
        esci = findViewById(R.id.btnannulla);

        gestioneGuasti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gestGuasti = new Intent(ComuneMainActivity.this, com.example.troublesgetaway.comune.gestioneGuasti.class);
                startActivity(gestGuasti);
            }
        });

        listaGuasti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guasti = new Intent(ComuneMainActivity.this, Guasti.class);
                startActivity(guasti);
            }
        });


            }

}
