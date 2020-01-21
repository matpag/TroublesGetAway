package com.example.troublesgetaway;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.troublesgetaway.data.model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    EditText usernameTxt, passwordTxt;
    Button loginBtn;
    ProgressDialog progressDialog;
    Dialog dialog;

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
//                } else {
//                    dialog = new Dialog();
//                    dialog.setTitle("@string/invalidLoginParameters");
//                    dialog.setCancelable(true);
//                    dialog.show();
//
//                }
            }
        });
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
                        return;
                    } else {
                        return;
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                //risposta connessione
            }
        });
    }
}
