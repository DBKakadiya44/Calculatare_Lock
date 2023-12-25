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
import java.util.ArrayList;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolder>
{
    EmailActivity emailActivity; ArrayList nn1; ArrayList nn2; ArrayList nn3;

    public EmailAdapter(EmailActivity emailActivity, ArrayList nn1, ArrayList nn2, ArrayList nn3) {
        this.emailActivity = emailActivity;
        this.nn1 = nn1;
        this.nn2 = nn2;
        this.nn3 = nn3;
    }

    @NonNull
    @Override
    public EmailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(emailActivity).inflate(R.layout.item_email,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailAdapter.ViewHolder holder, int position) {
        holder.t1.setText(""+nn1.get(position));
        holder.t2.setText(""+nn2.get(position));
        holder.t3.setText(""+nn3.get(position));

        holder.delete.setOnClickListener(view -> {
            emailActivity.removeItemFromArrayList1(emailActivity, position);
            emailActivity.removeItemFromArrayList2(emailActivity, position);
            emailActivity.removeItemFromArrayList3(emailActivity, position);
            emailActivity.startActivity(new Intent(emailActivity, EmailActivity.class));
            emailActivity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return nn1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.tt1);
            t2=itemView.findViewById(R.id.tt2);
            t3=itemView.findViewById(R.id.tt3);
            delete=itemView.findViewById(R.id.imageView4);
        }
    }
}
