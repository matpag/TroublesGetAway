package com.example.troublesgetaway;

import com.example.troublesgetaway.data.model.InserimentoComuneResponse;
import com.example.troublesgetaway.data.model.InserimentoGuastoResponse;
import com.example.troublesgetaway.data.model.InserimentoUtenteResponse;
import com.example.troublesgetaway.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyApiService {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("user") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("inserisci_utente.php")
    Call<InserimentoUtenteResponse> inserisciUtente(@Field("user") String username, @Field("password") String password, @Field("nome") String nome, @Field("cognome") String cognome, @Field("email") String email, @Field("telefono") Integer phone, @Field("comune") String comune);

    @FormUrlEncoded
    @POST("inserisci_guasto.php")
    Call<InserimentoGuastoResponse> inserisciGuasto(@Field("luogo") String comune, @Field("tipo") int tipo, @Field("stima") int stima);

    @FormUrlEncoded
    @POST("inserisci_comune.php")
    Call<InserimentoComuneResponse> inserisciComune(@Field("user") String username, @Field("password") String password, @Field("nome") String nome, @Field("email") String emailAddress, @Field("telefono") Integer phone, @Field("tipoUtente") Integer tipoUtente);
}
