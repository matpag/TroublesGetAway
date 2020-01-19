package com.example.troublesgetaway;

import java.util.List;

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
            Retrofit retrofit = new Retrofit.Builder()
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
    Call<List<Void>> login(@Field("user") String username, @Field("password") String password);

}