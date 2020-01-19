package com.example.troublesgetaway;

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

}