package com.example.troublesgetaway.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.troublesgetaway.R;

public class Utenti extends AppCompatActivity {

    ListView listaUtenti;
    TextView utenti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utenti);

        utenti = findViewById(R.id.textUtenti);
        listaUtenti = findViewById(R.id.listUsers);

    }
}
