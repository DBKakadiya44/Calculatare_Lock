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
import com.db.calculatarelock.password.AtmActivity;
import com.db.calculatarelock.password.CreditCardActivity;

import java.util.ArrayList;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.ViewHolder>
{
    CreditCardActivity creditCardActivity; ArrayList nn1; ArrayList nn2; ArrayList nn3; ArrayList nn4;

    public CreditAdapter(CreditCardActivity creditCardActivity, ArrayList nn1, ArrayList nn2, ArrayList nn3, ArrayList nn4) {
        this.creditCardActivity = creditCardActivity;
        this.nn1 = nn1;
        this.nn2 = nn2;
        this.nn3 = nn3;
        this.nn4 = nn4;
    }

    @NonNull
    @Override
    public CreditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(creditCardActivity).inflate(R.layout.item_creditcard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditAdapter.ViewHolder holder, int position) {
        holder.t1.setText(""+nn1.get(position));
        holder.t2.setText(""+nn2.get(position));
        holder.t3.setText(""+nn3.get(position));
        holder.t4.setText(""+nn4.get(position));

        holder.delete.setOnClickListener(view -> {
            creditCardActivity.removeItemFromArrayList1(creditCardActivity, position);
            creditCardActivity.removeItemFromArrayList2(creditCardActivity, position);
            creditCardActivity.removeItemFromArrayList3(creditCardActivity, position);
            creditCardActivity.removeItemFromArrayList4(creditCardActivity, position);
            creditCardActivity.startActivity(new Intent(creditCardActivity, CreditCardActivity.class));
            creditCardActivity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return nn1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.tt1);
            t2=itemView.findViewById(R.id.tt2);
            t3=itemView.findViewById(R.id.tt3);
            t4=itemView.findViewById(R.id.tt4);
            delete=itemView.findViewById(R.id.imageView4);
        }
    }
}
