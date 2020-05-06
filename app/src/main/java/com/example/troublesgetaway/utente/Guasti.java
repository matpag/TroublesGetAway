package com.example.troublesgetaway.utente;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.troublesgetaway.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Guasti extends AppCompatActivity {

    String urladdress ="http://troublegetawaydb.altervista.org/lista_guasti.php";
    String[] indirizzo;
    String[] dataRiparazione;
    String[] stato;
    String[] tipo;
    String[] stima;
    ListView listView;
    BufferedInputStream list;
    String line = null;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guasti);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        listView=(ListView)findViewById(R.id.lguasti);

        collectData();
        CustomListaGuasti customListaGuasti=new CustomListaGuasti(this, indirizzo, dataRiparazione, stato, tipo, stima);
        listView.setAdapter(customListaGuasti);

    }

    private void collectData()
    {

        //connessione
        try {
            URL url = new URL(urladdress);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");

            list=new BufferedInputStream(conn.getInputStream());
        }

        catch (IOException e) {
           e.printStackTrace();
        }

        //contenuto
        try{
            BufferedReader br= new BufferedReader(new InputStreamReader(list));
            StringBuilder sb= new StringBuilder();
            while((line=br.readLine())!=null) {
                sb.append(line+"\n");
            }
            list.close();
            result=sb.toString();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        //json

        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            indirizzo= new String[ja.length()];
            dataRiparazione= new String[ja.length()];
            stato = new String[ja.length()];
            tipo = new String[ja.length()];
            stima = new String[ja.length()];

            for (int i=0; i<=ja.length(); i++) {
                jo = ja.getJSONObject(i);
                indirizzo[i] =jo.getString("luogo");
                dataRiparazione[i] =jo.getString("dataRiparazione");
                stato[i] =jo.getString("stato");
                tipo[i] =jo.getString("tipo");
                stima[i] =jo.getString("stima");

            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
