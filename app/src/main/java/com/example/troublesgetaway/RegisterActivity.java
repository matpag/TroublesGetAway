package com.example.troublesgetaway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, nome, cognome, telefono, comune, email;
    TextView usr;
    TextView pswrd;
    Button registra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username =findViewById(R.id.usrPlain);
        password= findViewById(R.id.pswrdPlain);
        nome = findViewById(R.id.nomePlain);
        cognome = findViewById(R.id.cognomePlain);
        telefono = findViewById(R.id.telPlain);
        comune = findViewById(R.id.comunePlain);
        email = findViewById(R.id.mailPlain);
        usr = findViewById(R.id.usrText);
        pswrd = findViewById(R.id.pswrdText);
        registra = findViewById(R.id.btnregistra);

        registra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrati = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(registrati);
            }
        });
    }
}
