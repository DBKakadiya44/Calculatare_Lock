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
import com.db.calculatarelock.nav_Activitys.PlayVideoActivity;
import com.db.calculatarelock.nav_Activitys.VideoActivity;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    VideoActivity videoActivity;
    ArrayList<Uri> imageUris;
    public VideoAdapter(VideoActivity videoActivity, ArrayList<Uri> imageUris) {
        this.videoActivity = videoActivity;
        this.imageUris=imageUris;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(videoActivity).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {

        holder.vidicon.setVisibility(View.VISIBLE);

        Glide.with(videoActivity)
                .load(imageUris.get(position))
                .into(holder.imageView);

        holder.imageView.setOnClickListener(view -> {
            Intent intent = new Intent(videoActivity , PlayVideoActivity.class);
            intent.putExtra("video",imageUris);
            intent.putExtra("pos",position);
            intent.putExtra("cnt",1);
            videoActivity.startActivity(intent);
            videoActivity.finish();
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
