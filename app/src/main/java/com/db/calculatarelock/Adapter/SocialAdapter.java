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
import com.db.calculatarelock.password.CreditCardActivity;
import com.db.calculatarelock.password.SocialActivity;

import java.util.ArrayList;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.ViewHolder>
{
    SocialActivity socialActivity; ArrayList nn1; ArrayList nn2; ArrayList nn3; ArrayList nn4;

    public SocialAdapter(SocialActivity socialActivity, ArrayList nn1, ArrayList nn2, ArrayList nn3, ArrayList nn4) {
        this.socialActivity = socialActivity;
        this.nn1 = nn1;
        this.nn2 = nn2;
        this.nn3 = nn3;
        this.nn4 = nn4;
    }

    @NonNull
    @Override
    public SocialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(socialActivity).inflate(R.layout.item_creditcard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialAdapter.ViewHolder holder, int position) {

        holder.tt1.setText("Social Type : ");
        holder.tt2.setText("Name : ");
        holder.tt3.setText("Email/UserID : ");
        holder.tt4.setText("Password : ");

        holder.t1.setText(""+nn1.get(position));
        holder.t2.setText(""+nn2.get(position));
        holder.t3.setText(""+nn3.get(position));
        holder.t4.setText(""+nn4.get(position));

        holder.delete.setOnClickListener(view -> {
            socialActivity.removeItemFromArrayList1(socialActivity, position);
            socialActivity.removeItemFromArrayList2(socialActivity, position);
            socialActivity.removeItemFromArrayList3(socialActivity, position);
            socialActivity.removeItemFromArrayList4(socialActivity, position);
            socialActivity.startActivity(new Intent(socialActivity, SocialActivity.class));
            socialActivity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return nn1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,tt1,tt2,tt3,tt4;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.tt1);
            t2=itemView.findViewById(R.id.tt2);
            t3=itemView.findViewById(R.id.tt3);
            t4=itemView.findViewById(R.id.tt4);
            tt1=itemView.findViewById(R.id.tvv1);
            tt2=itemView.findViewById(R.id.tvv2);
            tt3=itemView.findViewById(R.id.tvv3);
            tt4=itemView.findViewById(R.id.tvv4);
            delete=itemView.findViewById(R.id.imageView4);
        }
    }
}
