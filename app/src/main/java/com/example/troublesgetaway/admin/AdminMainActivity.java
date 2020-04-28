package com.example.troublesgetaway.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.troublesgetaway.LoginActivity;
import com.example.troublesgetaway.R;
import com.example.troublesgetaway.utente.Guasti;

public class AdminMainActivity extends AppCompatActivity {

    Button gestUtenti;
    Button gestComuni;
    Button listaGuasti;
    Button esci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        gestUtenti = findViewById(R.id.btngestutenti);
        gestComuni = findViewById(R.id.btngestcomuni);
        listaGuasti = findViewById(R.id.btnguasti);
        esci = findViewById(R.id.btnannulla);

        gestUtenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gestioneUtenti = new Intent(AdminMainActivity.this, Utenti.class);
                startActivity(gestioneUtenti);
            }
        }
        );

        gestComuni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gestioneComuni = new Intent(AdminMainActivity.this, Comuni.class);
                startActivity(gestioneComuni);
            }
        });

        listaGuasti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guasti = new Intent(AdminMainActivity.this, Guasti.class);
                startActivity(guasti);
            }
        });

        esci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exit = new Intent(AdminMainActivity.this, LoginActivity.class);
                startActivity(exit);
            }
        });

    }
}
