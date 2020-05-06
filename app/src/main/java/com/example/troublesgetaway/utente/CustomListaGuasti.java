package com.example.troublesgetaway.utente;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.troublesgetaway.R;

public class CustomListaGuasti extends ArrayAdapter<String> {

    private String[] indirizzo;
    private String[] dataRiparazione;
    private String[] stato;
    private String[] tipo;
    private String[] stima;
    private Activity context;

    public CustomListaGuasti(@NonNull Activity context, String[] indirizzo, String[] dataRiparazione, String[] stato, String[] tipo,  String[] stima)
    {
        super(context, R.layout.layout_lguasti);
        this.context = context;
        this.indirizzo =indirizzo;
        this.dataRiparazione =dataRiparazione;
        this.stato =stato;
        this.tipo =tipo;
        this.stima =stima;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder=null;
        if (r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.layout_lguasti, null, true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);

        }

        else {
            viewHolder=(ViewHolder)r.getTag();

        }

        viewHolder.tvw1.setText(indirizzo[position]);
        viewHolder.tvw2.setText(dataRiparazione[position]);
        viewHolder.tvw3.setText(stima[position]);
        viewHolder.tvw4.setText(tipo[position]);
        viewHolder.tvw5.setText(stato[position]);

        return r;
    }

    class ViewHolder {
        TextView tvw1, tvw2, tvw3, tvw4, tvw5;

        ViewHolder(View v) {
            tvw1=(TextView)v.findViewById(R.id.indirizzo);
            tvw2=(TextView)v.findViewById(R.id.riparazione);
            tvw3=(TextView)v.findViewById(R.id.stima);
            tvw4=(TextView)v.findViewById(R.id.tipo);
            tvw5=(TextView)v.findViewById(R.id.stato);
        }
    }



}
