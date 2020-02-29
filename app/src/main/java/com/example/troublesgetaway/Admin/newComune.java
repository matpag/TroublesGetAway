package com.example.troublesgetaway.Admin;

import android.content.Intent;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.troublesgetaway.R;

public class newComune extends AppCompatActivity {

    TextView nome, cap, username, password;
    EditText name, CAP, usrnm, pswrd;
    Button annulla, inserisci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comune);

        nome = findViewById(R.id.textNome);
        cap = findViewById(R.id.textCap);
        username = findViewById(R.id.textUsrnm);
        password = findViewById(R.id.textPswrd);
        name = findViewById(R.id.editNome);
        CAP = findViewById(R.id.editCap);
        usrnm = findViewById(R.id.editUsrnm);
        pswrd = findViewById(R.id.editPswrd);
        annulla = findViewById(R.id.btnannulla);
        inserisci = findViewById(R.id.btninsert);

        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(newComune.this, Comuni.class);
                startActivity(cancel);
            }
        });
    }
}
