package com.db.calculatarelock.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.db.calculatarelock.R;
import com.db.calculatarelock.nav_Activitys.FullImageActivity;
import com.db.calculatarelock.nav_Activitys.PlayVideoActivity;
import com.db.calculatarelock.nav_Activitys.RecyclebinActivity;

import java.util.ArrayList;

public class RecycleBinAdapter extends RecyclerView.Adapter<RecycleBinAdapter.ViewHolder> {
    RecyclebinActivity recyclebinActivity;
    ArrayList<Uri> imageUris;
    int i;

    public RecycleBinAdapter(RecyclebinActivity recyclebinActivity, ArrayList<Uri> imageUris, int i) {
        this.recyclebinActivity = recyclebinActivity;
        this.imageUris = imageUris;
        this.i = i;
    }

    @NonNull
    @Override
    public RecycleBinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleBinAdapter.ViewHolder holder, int position) {

        if (i == 1) {
            holder.video.setVisibility(View.VISIBLE);

            Glide.with(recyclebinActivity)
                    .load(imageUris.get(position))
                    .into(holder.imageView);

            holder.imageView.setOnClickListener(view -> {
                Intent intent = new Intent(recyclebinActivity, PlayVideoActivity.class);
                intent.putExtra("video", imageUris);
                intent.putExtra("pos", position);
                intent.putExtra("cnt", 2);
                recyclebinActivity.startActivity(intent);
                recyclebinActivity.finish();
            });

        } else {
            holder.video.setVisibility(View.GONE);

            Glide.with(recyclebinActivity)
                    .load(imageUris.get(position))
                    .into(holder.imageView);

            holder.imageView.setOnClickListener(view -> {
                Intent intent = new Intent(recyclebinActivity, FullImageActivity.class);
                intent.putExtra("image", imageUris);
                intent.putExtra("pos", position);
                intent.putExtra("cnt", 2);
                recyclebinActivity.startActivity(intent);
                recyclebinActivity.finish();
            });


        }

    }

    @Override
    public int getItemCount() {
        return imageUris.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, video;
        ConstraintLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemgalleryimage);
            video = itemView.findViewById(R.id.vidicon);
            ll = itemView.findViewById(R.id.lllayout);
        }
    }
}
