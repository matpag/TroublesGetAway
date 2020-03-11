package com.example.troublesgetaway;

import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;

import com.example.troublesgetaway.data.model.InserimentoUtenteResponse;
import com.example.troublesgetaway.data.model.LoginResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class RetrofitService {

    private static final String BASE_URL = "http://troublegetawaydb.altervista.org/";

    private static MyApiService service = null;

    private ContentLoadingProgressBar showDialog(Context context) {
        ContentLoadingProgressBar contentLoadingProgressBar = new ContentLoadingProgressBar(context);
        contentLoadingProgressBar.show();
        return contentLoadingProgressBar;
    }

    public void dismissDialog(ContentLoadingProgressBar contentLoadingProgressBar) {
        contentLoadingProgressBar.hide();
    }

    public static MyApiService getInstance() {
        if (service == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
            service = retrofit.create(MyApiService.class);
        }
        return service;
    }
}

interface MyApiService {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("user") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("inserisci_utente.php")
    Call<InserimentoUtenteResponse> insertUser(@Field("user") String username, @Field("password") String password, @Field("nome") String nome, @Field("cognome") String cognome, @Field("email") String emailAddress, @Field("telefono") Integer phone, @Field("comune") String comune);

}