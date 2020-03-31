package com.example.troublesgetaway.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.troublesgetaway.MyApiService;
import com.example.troublesgetaway.R;
import com.example.troublesgetaway.RetrofitService;
//import com.example.troublesgetaway.data.model.InserimentoComuneResponse;
import com.example.troublesgetaway.data.model.InserimentoUtenteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

enum RegistrationState {
    wrongPassword,
    correctData
}

public class newComune extends AppCompatActivity {

    EditText nome, password, username, email, telefono;
    Button inserisci;

    private void showMessage(int text) {
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

    private void insertionSuccessfulDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.registration_successful)
                //      .setTitle(R.string.Successful)
                .setPositiveButton(R.string.OK_Button_Text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(newComune.this, Comuni.class);
                        startActivity(i);
                        finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comune);

        inserisci = findViewById(R.id.btnInsert);
        nome = findViewById(R.id.editNome);
        password = findViewById(R.id.editPswrd);
        username = findViewById(R.id.editUsrnm);
        telefono = findViewById(R.id.editTel);
        email = findViewById(R.id.editEmail);

        inserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrnm = username.getText().toString().trim();
                String pswrd = password.getText().toString().trim();
                String name = nome.getText().toString().trim();
                String tel = telefono.getText().toString().trim();
                String mail = email.getText().toString().trim();

                if (!TextUtils.isEmpty(usrnm) && !TextUtils.isEmpty(pswrd) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(tel) && !TextUtils.isEmpty(mail)
                ) {
                    tryInsert(usrnm, pswrd, name, tel, mail);
                } else {
                    showMessage(R.string.missing_element);
                }
            }
        });


    }

    public void tryInsert(String username, String password, String nome, String telefono, String email) {
        MyApiService apiService = RetrofitService.getInstance();

        apiService.insertUser(username, password, nome, Integer.valueOf(telefono), email).enqueue(new Callback<InserimentoUtenteResponse>() {
            @Override
            public void onResponse(Call<InserimentoUtenteResponse> call, Response<InserimentoUtenteResponse> response) {
                if (response.isSuccessful()) {
                    InserimentoUtenteResponse resp = response.body();
                    if (resp.success) {
                        insertionSuccessfulDialog();
                    } else {
                        if (!resp.error.isEmpty()) {
                            showCustomMessage(resp.error);
                        } else {
                            showMessage(R.string.Connection_Error);
                        }
                    }

                } else {
                    showCustomMessage(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<InserimentoUtenteResponse> call, Throwable t) {
                showCustomMessage(t.getMessage());
            }
        });

    }
}