package com.example.lab6_01;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RCake_Adapter extends RecyclerView.Adapter<RCake_Adapter.ViewHolder> {

    private final Context context;
    private List<Cake> values;
    private static final String TAG = "RecyclerView";

    public RCake_Adapter(Context context, List<Cake> myDataset) {
        this.context = context;
        this.values = myDataset;
    }

    @NonNull
    @Override
    public RCake_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.cakerow, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== onCreateViewHolder =====");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RCake_Adapter.ViewHolder holder, final int position) {

            Cake cake = values.get(position);
            holder.txtTitle.setText(cake.getTitle());
            holder.txtDescription.setText(cake.getDescription());
            holder.imageView.setImageResource(cake.getThumbline());
            holder.constraintLayout.setOnClickListener(v ->
                    Toast.makeText(context, cake.getTitle(), Toast.LENGTH_SHORT).show()
            );
            Log.i(TAG, "****** onBindViewHolder ******");

    }

    @Override
    public int getItemCount() {
        return (values != null) ? values.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtDescription;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtTitle = v.findViewById(R.id.textTitle);
            txtDescription = v.findViewById(R.id.textDesc);
            imageView = v.findViewById(R.id.imgThumbnail);
            constraintLayout = v.findViewById(R.id.row);

        }
    }


}