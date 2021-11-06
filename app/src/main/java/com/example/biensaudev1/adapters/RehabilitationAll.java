package com.example.biensaudev1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.biensaudev1.DetailedActivity;
import com.example.biensaudev1.R;
import com.example.biensaudev1.models.RehabilitationModel;

import java.util.List;

public class RehabilitationAll extends RecyclerView.Adapter<RehabilitationAll.ViewHolder> {

    private Context context;
    private List<RehabilitationModel> rehabilitationModelList;

    public RehabilitationAll(Context context, List<RehabilitationModel> rehabilitationModelList) {
        this.context = context;
        this.rehabilitationModelList = rehabilitationModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RehabilitationAll.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rehabilitation_all,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(rehabilitationModelList.get(position).getImage()).into(holder.image);
        holder.title.setText(rehabilitationModelList.get(position).getTitle());
        holder.shortDescription.setText(rehabilitationModelList.get(position).getShortDescription());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("rehabilitationdetails", rehabilitationModelList.get(position));
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return rehabilitationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title,shortDescription,description,duration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image =itemView.findViewById(R.id.rehabilitation_img);
            title =itemView.findViewById(R.id.rehabilitation_title);
            shortDescription =itemView.findViewById(R.id.rehabilitation_shortdesc);
        }
    }
}
