package com.example.troublesgetaway.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.troublesgetaway.R;

public class UtenteMainActivity extends AppCompatActivity {

    Button listaGuasti;
    Button segnalaBtn;
    Button mieiGuasti;
    Button profiloBtn;
    Button esci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utente_main);

        listaGuasti = findViewById(R.id.btnlistaguasti);
        segnalaBtn = findViewById(R.id.btnsegnala);
        mieiGuasti = findViewById(R.id.btnguastiut);
        profiloBtn = findViewById(R.id.btnprofilo);
        esci = findViewById(R.id.btnannulla);

        listaGuasti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View u) {
                Intent guasti = new Intent(UtenteMainActivity.this, Guasti.class);
                startActivity(guasti);
                    }
                 });

        segnalaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent segnala = new Intent(UtenteMainActivity.this, Segnala.class);
                startActivity(segnala);
                    }
                });

        mieiGuasti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mieiguasti = new Intent(UtenteMainActivity.this, GuastiUt.class);
                startActivity(mieiguasti);
                    }
                });

        profiloBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profilo = new Intent(UtenteMainActivity.this, ProfiloUt.class);
                startActivity(profilo);
                    }
                });

    }

}
