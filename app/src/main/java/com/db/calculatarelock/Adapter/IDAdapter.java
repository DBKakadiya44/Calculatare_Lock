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
import com.db.calculatarelock.password.EmailActivity;
import com.db.calculatarelock.password.IdCardActivity;

import java.util.ArrayList;

public class IDAdapter extends RecyclerView.Adapter<IDAdapter.ViewHolder>
{
    IdCardActivity idCardActivity; ArrayList nn1; ArrayList nn2;

    public IDAdapter(IdCardActivity idCardActivity, ArrayList nn1, ArrayList nn2) {
        this.idCardActivity = idCardActivity;
        this.nn1 = nn1;
        this.nn2 = nn2;
    }

    @NonNull
    @Override
    public IDAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(idCardActivity).inflate(R.layout.item_id_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IDAdapter.ViewHolder holder, int position) {
        holder.t1.setText(""+nn1.get(position));
        holder.t2.setText(""+nn2.get(position));

        holder.delete.setOnClickListener(view -> {
            idCardActivity.removeItemFromArrayList1(idCardActivity, position);
            idCardActivity.removeItemFromArrayList2(idCardActivity, position);
            idCardActivity.startActivity(new Intent(idCardActivity, EmailActivity.class));
            idCardActivity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return nn1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.tt1);
            t2=itemView.findViewById(R.id.tt2);
            delete=itemView.findViewById(R.id.imageView4);
        }
    }
}
