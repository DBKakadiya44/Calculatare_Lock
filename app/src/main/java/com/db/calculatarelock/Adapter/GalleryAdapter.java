package com.db.calculatarelock.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.db.calculatarelock.R;
import com.db.calculatarelock.nav_Activitys.FullImageActivity;
import com.db.calculatarelock.nav_Activitys.GalleryActivity;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    GalleryActivity galleryActivity;
    ArrayList<Uri> imageUris;
    public GalleryAdapter(GalleryActivity galleryActivity, ArrayList<Uri> imageUris) {
        this.galleryActivity = galleryActivity;
        this.imageUris=imageUris;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, int position) {

        holder.vidicon.setVisibility(View.GONE);

        Glide.with(galleryActivity)
                .load(imageUris.get(position))
                .into(holder.imageView);

        holder.imageView.setOnClickListener(view -> {
            Intent intent = new Intent(galleryActivity , FullImageActivity.class);
            intent.putExtra("image",imageUris);
            intent.putExtra("pos",position);
            intent.putExtra("cnt",1);
            galleryActivity.startActivity(intent);
            galleryActivity.finish();
        });

    }

    @Override
    public int getItemCount() {
        return imageUris.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView vidicon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemgalleryimage);
            vidicon = itemView.findViewById(R.id.vidicon);
        }
    }
}
