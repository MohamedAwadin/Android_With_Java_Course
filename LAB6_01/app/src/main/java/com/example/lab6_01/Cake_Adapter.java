package com.example.lab6_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Cake_Adapter extends ArrayAdapter<Cake> {
    private Cake[] cakes;

    public Cake_Adapter(Context context, Cake[] data) {
        super(context, R.layout.cakerow, R.id.textTitle, data);
        this.cakes = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup lstView) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.cakerow, lstView, false);
        }

        ImageView img = row.findViewById(R.id.imgThumbnail);
        TextView txt_title = row.findViewById(R.id.textTitle);
        TextView txt_desc = row.findViewById(R.id.textDesc);

        img.setImageResource(cakes[position].getThumbline());
        txt_title.setText(cakes[position].getTitle());
        txt_desc.setText(cakes[position].getDescription());

        return row;
    }
}