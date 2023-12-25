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
import com.db.calculatarelock.password.LicenseActivity;
import java.util.ArrayList;

public class LicenseAdapter extends RecyclerView.Adapter<LicenseAdapter.ViewHolder>
{
    LicenseActivity licenseActivity; ArrayList nn1; ArrayList nn2;

    public LicenseAdapter(LicenseActivity licenseActivity, ArrayList nn1, ArrayList nn2) {
        this.licenseActivity = licenseActivity;
        this.nn1 = nn1;
        this.nn2 = nn2;
    }

    @NonNull
    @Override
    public LicenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(licenseActivity).inflate(R.layout.item_id_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LicenseAdapter.ViewHolder holder, int position) {
        holder.t1.setText(""+nn1.get(position));
        holder.t2.setText(""+nn2.get(position));
        holder.textView.setText("Licence Number : ");

        holder.delete.setOnClickListener(view -> {
            licenseActivity.removeItemFromArrayList1(licenseActivity, position);
            licenseActivity.removeItemFromArrayList2(licenseActivity, position);
            licenseActivity.startActivity(new Intent(licenseActivity, LicenseActivity.class));
            licenseActivity.finish();
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
