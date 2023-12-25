package com.db.calculatarelock.Adapter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.db.calculatarelock.R;
import com.db.calculatarelock.nav_Activitys.ContactActivity;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    ContactActivity contactActivity;
    ArrayList retrievedList;
    ArrayList retrievedList2;
    int MY_PERMISSIONS_REQUEST_CALL_PHONE = 100;

    public ContactAdapter(ContactActivity contactActivity, ArrayList retrievedList, ArrayList retrievedList2) {
        this.contactActivity = contactActivity;
        this.retrievedList = retrievedList;
        this.retrievedList2 = retrievedList2;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {

        holder.name.setText("" + retrievedList.get(position));
        holder.number.setText("" + retrievedList2.get(position));

        holder.delete.setOnClickListener(view -> {
            ContactActivity.removeItemFromArrayList(contactActivity, position);
            ContactActivity.removeItemFromArrayList2(contactActivity, position);
            contactActivity.startActivity(new Intent(contactActivity, ContactActivity.class));
            contactActivity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return retrievedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, number;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ccname);
            number = itemView.findViewById(R.id.ccnumber);
            delete = itemView.findViewById(R.id.imageView4);
        }
    }
}
