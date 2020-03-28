package com.example.troublesgetaway;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.troublesgetaway.data.model.InserimentoUtenteResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

enum RegistrationState {
    wrongPassword,
    correctData
}


public class RegisterActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText,repeatedPasswordEditText, nameEditText, surnameEditText, phoneEditText, stateEditText, emailEditText;
    Button registrationButton;
    RegistrationState registrationState;
    Intent intent;


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

    private void registrationSuccessfulDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.registration_successful)
                //      .setTitle(R.string.Successful)
                .setPositiveButton(R.string.OK_Button_Text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText= findViewById(R.id.passwordEditText);
        repeatedPasswordEditText = findViewById(R.id.repeatPasswordEditText);
        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        stateEditText = findViewById(R.id.stateEditText);
        emailEditText = findViewById(R.id.emailEditText);
        registrationButton = findViewById(R.id.registrationButton);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String repeatedPassword = repeatedPasswordEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();
                String surname = surnameEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String state = stateEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(repeatedPassword) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname.trim()) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(state) && !TextUtils.isEmpty(email) && TextUtils.equals(password, repeatedPassword)
                ) {
                    tryRegistration(username, password, name, surname, phone, email, state);
                } else if (!TextUtils.equals(password, repeatedPassword) && !password.isEmpty() && !repeatedPassword.isEmpty() ) {
                    showMessage(R.string.not_same_password_message);
                } else{
                    showMessage(R.string.missing_element);
                }

            }
        });
    }

    public void tryRegistration(String username, String password, String name, String surname, String phone, String email, String state) {
        MyApiService apiService = RetrofitService.getInstance();

        apiService.insertUser(username, password, name, surname,email, Integer.valueOf(phone), state).enqueue(new Callback<InserimentoUtenteResponse>() {
            @Override
            public void onResponse(Call<InserimentoUtenteResponse> call, Response<InserimentoUtenteResponse> response) {
                if (response.isSuccessful()) {
                    InserimentoUtenteResponse resp = response.body();
                    if (resp.success) {
                        registrationSuccessfulDialog();
                    } else {
                        if (!resp.error.isEmpty()){
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
