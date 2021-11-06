package com.example.biensaudev1.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biensaudev1.R;
import com.example.biensaudev1.models.ReserveModel;

import java.util.List;

public class ReserveAdapter extends RecyclerView.Adapter<ReserveAdapter.ViewHolder> {
    private Context context;
    private List<ReserveModel> reserveModelList;

    public ReserveAdapter(Context context, List<ReserveModel> reserveModelList) {
        this.context = context;
        this.reserveModelList = reserveModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reserves_item,parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.reserveday.setText(reserveModelList.get(position).getReserveday());

        holder.service.setText(reserveModelList.get(position).getService());

        holder.center.setText(reserveModelList.get(position).getCenter());
        holder.status.setText(reserveModelList.get(position).getStatus());

           if (reserveModelList.get(position).getStatus().equals("Em confirmação") ){
            holder.status.setTextColor(Color.rgb(186, 162, 7));
        }else if(reserveModelList.get(position).getStatus().equals("Cancelado") ){
               holder.status.setTextColor(Color.rgb(181, 54, 33));
           }else if (reserveModelList.get(position).getStatus().equals("Confirmado")){
            holder.status.setTextColor(Color.rgb(56, 107, 22));
        }else if(reserveModelList.get(position).getStatus().equals("Concluido")){
               holder.status.setTextColor(Color.rgb(145, 193, 82));
           }



    }

    @Override
    public int getItemCount() {
        return reserveModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView service, reserveday, status, center;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            service = itemView.findViewById(R.id.service);
            reserveday = itemView.findViewById(R.id.reserveday);
            status = itemView.findViewById(R.id.status);
            center = itemView.findViewById(R.id.center);
        }
    }




}
