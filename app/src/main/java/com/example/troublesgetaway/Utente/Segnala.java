package com.example.troublesgetaway.Utente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.troublesgetaway.R;

public class Segnala extends AppCompatActivity {

    TextView textIndrizzo, tipo, stima;
    EditText indirizzo;
    RadioButton altro, buca, luce, semaforo, bassa, alta, media;
    Button invia, annulla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segnala);

        textIndrizzo = findViewById(R.id.textInd);
        tipo = findViewById(R.id.textTipo);
        stima = findViewById(R.id.textStima);
        indirizzo = findViewById(R.id.editInd);
        altro = findViewById(R.id.radioAltro);
        buca = findViewById(R.id.radioBuca);
        luce = findViewById(R.id.radioLuce);
        semaforo = findViewById(R.id.radioSemaforo);
        bassa = findViewById(R.id.radioBassa);
        alta = findViewById(R.id.radioAlta);
        media = findViewById(R.id.radioMedia);
        invia = findViewById(R.id.bntinvia);
        annulla = findViewById(R.id.btnannulla);

        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent invia = new Intent(Segnala.this, UtenteMainActivity.class);
                startActivity(invia);



            }
        });

        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent annulla = new Intent(Segnala.this, UtenteMainActivity.class);
                startActivity(annulla);
            }
        });
    }
}
