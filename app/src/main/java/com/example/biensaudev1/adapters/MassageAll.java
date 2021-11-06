package com.example.biensaudev1.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.biensaudev1.DetailedActivity;
import com.example.biensaudev1.R;
import com.example.biensaudev1.models.MassageModel;
import com.example.biensaudev1.ui.description.DescriptionDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class MassageAll extends RecyclerView.Adapter<MassageAll.ViewHolder>  {

    private Context context;
    private List<MassageModel> massageModelLists;
    private static FragmentManager fragmentManager;

    public MassageAll(Context context, List<MassageModel> massageModelLists) {
        this.context = context;
        this.massageModelLists = massageModelLists;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.massage_all,parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(massageModelLists.get(position).getImage()).into(holder.image);
        holder.title.setText(massageModelLists.get(position).getTitle());
        holder.shortDescription.setText(massageModelLists.get(position).getShortDescription());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail", massageModelLists.get(position));
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return massageModelLists.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title,shortDescription,description,recipient ,duration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image =itemView.findViewById(R.id.massage_img);
            title =itemView.findViewById(R.id.massage_title);
            shortDescription =itemView.findViewById(R.id.massage_shortdesc);
            duration =itemView.findViewById(R.id.massage_duration);

        }
    }
}
