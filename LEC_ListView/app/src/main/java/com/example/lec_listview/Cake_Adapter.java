package com.example.lec_listview;

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


    Context context ;
    Cake[] cakes;
    public Cake_Adapter(Context context , Cake[] data){
        super(context , R.layout.singlerow , R.id.textView2 , data);

        context = context ;

        cakes = data ;
    }

    /*call every time for every row on the viewlist
    * */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup lstView) {

        View row = null ;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row  = inflater.inflate(R.layout.singlerow , lstView , false);

        ImageView img = row.findViewById(R.id.imageView);
        TextView txt_title = row.findViewById(R.id.textView2);
        TextView txt_desc = row.findViewById(R.id.textView3);


        img.setImageResource( cakes[position].getThumbline());
        txt_title.setText(cakes[position].getTitle());
        txt_desc.setText(cakes[position].getDescription());

        return row;
    }
}
