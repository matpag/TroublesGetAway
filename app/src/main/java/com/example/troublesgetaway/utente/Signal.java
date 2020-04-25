package com.example.troublesgetaway.utente;

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
import android.widget.Toast;

import com.example.troublesgetaway.MyApiService;
import com.example.troublesgetaway.R;
import com.example.troublesgetaway.RetrofitService;
import com.example.troublesgetaway.data.model.InserimentoGuastoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signal extends AppCompatActivity {

    TextView textIndrizzo, tipo, stima;
    EditText indirizzo;
    RadioGroup tipoGroup, stimaGroup;
    RadioButton radioButton, radioButton1;
    Button invia;
    String firstRadioButtonGroupChoice, secondRadioButtonGroupChoice;
    Integer firstChoiceInt, secondChoiceInt;

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

    private void signalSuccesfulDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("guasto segnalato con successo")
                .setPositiveButton(R.string.OK_Button_Text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent segnal = new Intent(Signal.this, GuastiUt.class);
                        startActivity(segnal);
                        finish();
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


        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luogo = indirizzo.getText().toString().trim();
                int radioId = tipoGroup.getCheckedRadioButtonId();
                int radioInt = stimaGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                radioButton1 = findViewById(radioInt);
                firstRadioButtonGroupChoice = (String) radioButton.getText();
                secondRadioButtonGroupChoice = (String) radioButton1.getText();


                switch (firstRadioButtonGroupChoice){
                    case "Altro":
                        firstChoiceInt = 0;
                        break;
                    case "Buca Asfalto":
                        firstChoiceInt = 1;
                        break;
                    case "Lampione Spento":
                        firstChoiceInt = 2;
                        break;
                    case "Semaforo Non Funzionante":
                        firstChoiceInt = 3;
                        break;
                }

                switch (secondRadioButtonGroupChoice){
                    case "Bassa":
                        secondChoiceInt = 0;
                        break;
                    case "Media":
                        secondChoiceInt = 1;
                        break;
                    case "Alta":
                        secondChoiceInt = 2;
                        break;
                }



                if (!TextUtils.isEmpty(luogo)
                ) {

                    trySegn(luogo, firstChoiceInt, secondChoiceInt);
                } else{
                    showMessage(R.string.missing_element);
                }
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
        apiService.inserisciGuasto(luogo, radioId, radioInt).enqueue(new Callback<InserimentoGuastoResponse>() {
            @Override
            public void onResponse(Call<InserimentoGuastoResponse> call, Response<InserimentoGuastoResponse> response) {
                if (response.isSuccessful()) {
                    InserimentoGuastoResponse resp = response.body();
                    if (resp.success) {
                        signalSuccesfulDialog();
                    }
                    else {
                        if (!resp.error.isEmpty()){
                            showCustomMessage(resp.error);
                        } else {
                            showMessage(R.string.Connection_Error);
                        }
                    }
                     }   else {
                    showCustomMessage(response.errorBody().toString());
                    }
                }

            @Override
            public void onFailure(Call<InserimentoGuastoResponse> call, Throwable t) {

            }
        });

    }
};


