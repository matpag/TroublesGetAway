package com.example.troublesgetaway.Utente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.troublesgetaway.LoginActivity;
import com.example.troublesgetaway.MyApiService;
import com.example.troublesgetaway.R;
import com.example.troublesgetaway.RegisterActivity;
import com.example.troublesgetaway.RetrofitService;
import com.example.troublesgetaway.data.model.InserimentoGuastoResponse;
import com.example.troublesgetaway.data.model.InserimentoUtenteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Segnala extends AppCompatActivity {

    TextView textIndrizzo, tipo, stima;
    EditText indirizzo;
    RadioGroup tipoGroup, stimaGroup;
    RadioButton radioButton, radioButton1;
    Button invia, annulla;

    private void showMessage(int text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setPositiveButton(R.string.OK_Button_Text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showCustomMessage(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                //.setTitle(R.string.Error)
                .setPositiveButton(R.string.OK_Button_Text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void insertgSuccessfulDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.registration_successful)
                .setPositiveButton(R.string.OK_Button_Text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent insertg = new Intent(Segnala.this, GuastiUt.class);
                        startActivity(insertg);
                        finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segnala);

        textIndrizzo = findViewById(R.id.textInd);
        tipo = findViewById(R.id.textTipo);
        stima = findViewById(R.id.textStima);
        indirizzo = findViewById(R.id.editInd);
        tipoGroup = findViewById(R.id.radioTipo);
        stimaGroup = findViewById(R.id.radioStima);
        invia = findViewById(R.id.bntinvia);
        annulla = findViewById(R.id.btnannulla);


        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luogo = indirizzo.getText().toString().trim();
                int radioId = tipoGroup.getCheckedRadioButtonId();
               // radioId = tipo.;
                int radioInt = stimaGroup.getCheckedRadioButtonId();
                // radioInt = stima.;
                radioButton = findViewById(radioId);
                radioButton1 = findViewById(radioInt);
                if (!TextUtils.isEmpty(luogo)
                ) {

                    trySegn(luogo, radioId, radioInt);
                } else{
                    showMessage(R.string.missing_element);
                }

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
    public void checkButton (View v) {
        int radioId = tipoGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        int radioInt = stimaGroup.getCheckedRadioButtonId();
        radioButton1 = findViewById(radioInt);
    }


    public void trySegn(String luogo, int radioId, int radioInt){
        MyApiService apiService=RetrofitService.getInstance();
        apiService.insertGuasto(luogo, radioId, radioInt).enqueue(new Callback<InserimentoGuastoResponse>() {
            @Override
            public void onResponse(Call<InserimentoGuastoResponse> call, Response<InserimentoGuastoResponse> response) {
                if (response.isSuccessful()) {
                    InserimentoGuastoResponse resp = response.body();
                    if (resp.success) {
                        showCustomMessage("messaggio per guasto inserito correttamente");
                    }
                }
            }

            @Override
            public void onFailure(Call<InserimentoGuastoResponse> call, Throwable t) {

            }
        });

    }
};


