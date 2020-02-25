package com.example.troublesgetaway.Comune;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.troublesgetaway.R;
import com.example.troublesgetaway.Utente.Guasti;

public class ComuneMainActivity extends AppCompatActivity {

    Button gestioneGuasti;
    Button listaGuasti;
    Button messaggi;
    Button esci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comune_main);

        gestioneGuasti = findViewById(R.id.btngestione);
        listaGuasti = findViewById(R.id.btnguasti);
        messaggi = findViewById(R.id.btnmsg);
        esci = findViewById(R.id.btnesci);

        gestioneGuasti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gestGuasti = new Intent(ComuneMainActivity.this, gestGuasti.class);
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

        messaggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msg = new Intent(ComuneMainActivity.this, Messaggi.class);
                startActivity(msg);
            }
        });



    }

}
