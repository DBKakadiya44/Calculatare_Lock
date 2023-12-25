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
import com.db.calculatarelock.password.BankActivity;

import java.util.ArrayList;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.ViewHolder>
{
    BankActivity bankActivity;
    ArrayList nn1;
    ArrayList nn2;
    ArrayList nn3;
    ArrayList nn4;
    ArrayList nn5;
    ArrayList nn6;
    ArrayList nn7;
    ArrayList nn8;
    ArrayList nn9;
    ArrayList nn10;
    ArrayList nn11;

    public BankAdapter(BankActivity bankActivity, ArrayList nn1, ArrayList nn2, ArrayList nn3, ArrayList nn4, ArrayList nn5, ArrayList nn6, ArrayList nn7, ArrayList nn8, ArrayList nn9, ArrayList nn10, ArrayList nn11) {
        this.bankActivity = bankActivity;
        this.nn1 = nn1;
        this.nn2 = nn2;
        this.nn3 = nn3;
        this.nn4 = nn4;
        this.nn5 = nn5;
        this.nn6 = nn6;
        this.nn7 = nn7;
        this.nn8 = nn8;
        this.nn9 = nn9;
        this.nn10 = nn10;
        this.nn11 = nn11;
    }

    @NonNull
    @Override
    public BankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(bankActivity).inflate(R.layout.item_bank,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankAdapter.ViewHolder holder, int position) {
        holder.t1.setText(""+nn1.get(position));
        holder.t2.setText(""+nn2.get(position));
        holder.t3.setText(""+nn3.get(position));
        holder.t4.setText(""+nn4.get(position));
        holder.t5.setText(""+nn5.get(position));
        holder.t6.setText(""+nn6.get(position));
        holder.t7.setText(""+nn7.get(position));
        holder.t8.setText(""+nn8.get(position));
        holder.t9.setText(""+nn9.get(position));
        holder.t10.setText(""+nn10.get(position));
        holder.t11.setText(""+nn11.get(position));

        holder.delete.setOnClickListener(view -> {
            bankActivity.removeItemFromArrayList1(bankActivity, position);
            bankActivity.removeItemFromArrayList2(bankActivity, position);
            bankActivity.removeItemFromArrayList3(bankActivity, position);
            bankActivity.removeItemFromArrayList4(bankActivity, position);
            bankActivity.removeItemFromArrayList5(bankActivity, position);
            bankActivity.removeItemFromArrayList6(bankActivity, position);
            bankActivity.removeItemFromArrayList7(bankActivity, position);
            bankActivity.removeItemFromArrayList8(bankActivity, position);
            bankActivity.removeItemFromArrayList9(bankActivity, position);
            bankActivity.removeItemFromArrayList10(bankActivity, position);
            bankActivity.removeItemFromArrayList11(bankActivity, position);
            bankActivity.startActivity(new Intent(bankActivity, BankActivity.class));
            bankActivity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return nn1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
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
            t8=itemView.findViewById(R.id.tt8);
            t9=itemView.findViewById(R.id.tt9);
            t10=itemView.findViewById(R.id.tt10);
            t11=itemView.findViewById(R.id.tt11);
            delete=itemView.findViewById(R.id.imageView4);
        }
    }
}
