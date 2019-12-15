package com.example.troublesgetaway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    EditText usernameTxt, passwordTxt;
    Button loginBtn;

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
                } else {
                    //l'utente non ha riempito entrambi i campi
                }
            }
        });
    }

    private void tryLogin(String username, String password) {
        MyApiService apiService = RetrofitService.getInstance();
        apiService.login(username, password).enqueue(new Callback<List<Void>>() {
            @Override
            public void onResponse(Call<List<Void>> call, Response<List<Void>> response) {
                //risposta affermativa
                if (response.isSuccessful()) {
                    //vai avanti
                }
            }

            @Override
            public void onFailure(Call<List<Void>> call, Throwable t) {
                //risposta connessione
            }
        });
    }
}
