package com.db.calculatarelock.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.db.calculatarelock.R;
import com.db.calculatarelock.nav_Activitys.ContactActivity;
import com.db.calculatarelock.password.AtmActivity;

import java.util.ArrayList;

public class AtmAdapter extends RecyclerView.Adapter<AtmAdapter.ViewHolder>
{
    AtmActivity atmActivity;
    ArrayList nn1;
    ArrayList nn2;
    ArrayList nn3;
    ArrayList nn4;
    ArrayList nn5;
    ArrayList nn6;
    ArrayList nn7;

    public AtmAdapter(AtmActivity atmActivity, ArrayList nn1, ArrayList nn2, ArrayList nn3, ArrayList nn4, ArrayList nn5, ArrayList nn6, ArrayList nn7) {
        this.atmActivity = atmActivity;
        this.nn1 = nn1;
        this.nn2 = nn2;
        this.nn3 = nn3;
        this.nn4 = nn4;
        this.nn5 = nn5;
        this.nn6 = nn6;
        this.nn7 = nn7;
    }

    @NonNull
    @Override
    public AtmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(atmActivity).inflate(R.layout.item_atm,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtmAdapter.ViewHolder holder, int position) {
        holder.t1.setText(""+nn1.get(position));
        holder.t2.setText(""+nn2.get(position));
        holder.t3.setText(""+nn3.get(position));
        holder.t4.setText(""+nn4.get(position));
        holder.t5.setText(""+nn5.get(position));
        holder.t6.setText(""+nn6.get(position));
        holder.t7.setText(""+nn7.get(position));

        holder.delete.setOnClickListener(view -> {
            atmActivity.removeItemFromArrayList1(atmActivity, position);
            atmActivity.removeItemFromArrayList2(atmActivity, position);
            atmActivity.removeItemFromArrayList3(atmActivity, position);
            atmActivity.removeItemFromArrayList4(atmActivity, position);
            atmActivity.removeItemFromArrayList5(atmActivity, position);
            atmActivity.removeItemFromArrayList6(atmActivity, position);
            atmActivity.removeItemFromArrayList7(atmActivity, position);
            atmActivity.startActivity(new Intent(atmActivity, AtmActivity.class));
            atmActivity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return nn1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5,t6,t7;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.tt1);
            t2=itemView.findViewById(R.id.tt2);
            t3=itemView.findViewById(R.id.tt3);
            t4=itemView.findViewById(R.id.tt4);
            t5=itemView.findViewById(R.id.tt5);
            t6=itemView.findViewById(R.id.tt6);
            t7=itemView.findViewById(R.id.tt7);
            delete=itemView.findViewById(R.id.imageView4);
        }
    }
}
