package com.example.troublesgetaway.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.troublesgetaway.R;

public class Comuni extends AppCompatActivity {

    TextView nuovo, comuni;
    ListView listCom;
    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comuni);

        nuovo = findViewById(R.id.textNew);
        comuni = findViewById(R.id.textComuni);
        listCom = findViewById(R.id.listComuni);
        insert = findViewById(R.id.btninsert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inserisci = new Intent(Comuni.this, newComune.class);
                startActivity(inserisci);
            }
        });

    }
}
