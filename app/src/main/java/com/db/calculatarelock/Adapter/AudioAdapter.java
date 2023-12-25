package com.db.calculatarelock.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.db.calculatarelock.R;
import com.db.calculatarelock.nav_Activitys.AudioActivity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {
    AudioActivity audioActivity;
    ArrayList<Uri> imageUris;
    public static MediaPlayer mediaPlayer;
    ArrayList<Uri> imageName;
    public AudioAdapter(AudioActivity audioActivity, ArrayList<Uri> imageUris, ArrayList<Uri> imageName) {
        this.audioActivity=audioActivity;
        this.imageUris=imageUris;
        this.imageName=imageName;
    }

    @NonNull
    @Override
    public AudioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_audio,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.textView.setText(imageName.get(position).toString());

        mediaPlayer = new MediaPlayer();
        holder.click.setOnClickListener(view -> {

            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(audioActivity, imageUris.get(position));

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build());
                } else {
                    mediaPlayer.setAudioStreamType(AudioAttributes.CONTENT_TYPE_MUSIC);
                }

                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.start();
        });

        holder.menu.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(audioActivity, view);
            popupMenu.inflate(R.menu.popup_menu);

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    if (item.getItemId() == R.id.menu_item_1) {

                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                Files.deleteIfExists(Paths.get(imageUris.get(position).getPath()));
                                Toast.makeText(audioActivity, "Audio Unhide Sucessfully...", Toast.LENGTH_SHORT).show();
                                audioActivity.startActivity(new Intent(audioActivity,AudioActivity.class));
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return true;
                    } else if (item.getItemId() == R.id.menu_item_2) {

                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                                copyImageToStorage(imageUris.get(position));

                                Files.deleteIfExists(Paths.get(imageUris.get(position).getPath()));
                                Toast.makeText(audioActivity, "Audio Deleted...", Toast.LENGTH_SHORT).show();
                                audioActivity.startActivity(new Intent(audioActivity,AudioActivity.class));
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        return true;
                    } else {
                        return false;
                    }
                }
            });
            popupMenu.show();
        });

    }

    @Override
    public int getItemCount() {
        return imageUris.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView menu;
        ConstraintLayout click;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.audiotext);
            click = itemView.findViewById(R.id.mainaudio);
            menu = itemView.findViewById(R.id.menubtn);
        }
    }

    private void copyImageToStorage(Uri sourceUri) {

        File storageDirectory = new File(Environment.getExternalStorageDirectory(), "RECYCLEBIN");
        if (!storageDirectory.exists()) {
            storageDirectory.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "AUD_" + timeStamp + ".mp3";
        File destImageFile = new File(storageDirectory, imageFileName);

        try {
            InputStream inputStream = audioActivity.getContentResolver().openInputStream(sourceUri);
            FileUtils.copyInputStreamToFile(inputStream, destImageFile);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
