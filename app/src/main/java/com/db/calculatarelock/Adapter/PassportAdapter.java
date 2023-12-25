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
import com.db.calculatarelock.password.PassportActivity;

import java.util.ArrayList;

public class PassportAdapter extends RecyclerView.Adapter<PassportAdapter.ViewHolder>
{
    PassportActivity passportActivity; ArrayList nn1; ArrayList nn2;

    public PassportAdapter(PassportActivity passportActivity, ArrayList nn1, ArrayList nn2) {
        this.passportActivity = passportActivity;
        this.nn1 = nn1;
        this.nn2 = nn2;
    }

    @NonNull
    @Override
    public PassportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(passportActivity).inflate(R.layout.item_id_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassportAdapter.ViewHolder holder, int position) {
        holder.t1.setText(""+nn1.get(position));
        holder.t2.setText(""+nn2.get(position));
        holder.textView.setText("Passport Number : ");

        holder.delete.setOnClickListener(view -> {
            passportActivity.removeItemFromArrayList1(passportActivity, position);
            passportActivity.removeItemFromArrayList2(passportActivity, position);
            passportActivity.startActivity(new Intent(passportActivity, PassportActivity.class));
            passportActivity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return nn1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,textView;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.tt1);
            t2=itemView.findViewById(R.id.tt2);
            textView=itemView.findViewById(R.id.tvlie);
            delete=itemView.findViewById(R.id.imageView4);
        }
    }
}
