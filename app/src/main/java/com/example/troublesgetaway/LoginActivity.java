package com.example.troublesgetaway;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.troublesgetaway.Admin.AdminMainActivity;
import com.example.troublesgetaway.Comune.ComuneMainActivity;
import com.example.troublesgetaway.Utente.UtenteMainActivity;
import com.example.troublesgetaway.data.model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    EditText usernameTxt, passwordTxt;
    Button loginBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameTxt = findViewById(R.id.username);
        passwordTxt = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                if (!TextUtils.isEmpty(username.trim()) && !TextUtils.isEmpty(password.trim())) {
                    tryLogin(username, password);
                }
            }
        });
    }

    private void showMessage(int text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setPositiveButton(R.string.OK_Button_Text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create();
        builder.show();
    }


    private void tryLogin(String username, String password) {
        MyApiService apiService = RetrofitService.getInstance();
        apiService.login(username, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //risposta affermativa
                if (response.isSuccessful()) {
                    LoginResponse resp = response.body();
                    if (resp.success) {
                        switch (resp.tipoUtente) {
                            case 0: // CASO UTENTE
                                intent = new Intent(LoginActivity.this, UtenteMainActivity.class);
                                startActivity(intent);
                                finish();
                                return;
                            case 1: // CASO COMUNE
                                intent = new Intent(LoginActivity.this, ComuneMainActivity.class);
                                startActivity(intent);
                                finish();
                                return;
                            case 2: // CASO ADMIN
                                intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                                startActivity(intent);
                                finish();
                                return;
                        }
                    } else {
                        showMessage(R.string.invalidLoginParameters);
                    }

                } else {
                    response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                showMessage(R.string.Connection_Error);
            }
        });
    }
}
