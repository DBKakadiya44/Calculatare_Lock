package com.db.calculatarelock.nav_Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.view.WindowManager;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.Adapter.RecycleBinAdapter;
import com.db.calculatarelock.R;
import com.db.calculatarelock.databinding.ActivityRecyclebinBinding;
import java.io.File;
import java.util.ArrayList;

public class RecyclebinActivity extends AppCompatActivity {
    ActivityRecyclebinBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    ArrayList<Uri> imageUris = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclebinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        getphoto();

        binding.ivback.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.opphoto.setOnClickListener(view -> {
            getphoto();
            binding.opphoto.setBackgroundResource(R.drawable.num_btn);
            binding.opvideo.setBackgroundResource(R.color.maincolor);
        });

        binding.opvideo.setOnClickListener(view -> {
            getvideo();
            binding.opvideo.setBackgroundResource(R.drawable.num_btn);
            binding.opphoto.setBackgroundResource(R.color.maincolor);
        });


    }

    private void getvideo() {
        File storageDirectory = new File(Environment.getExternalStorageDirectory(), "RECYCLEBIN");

        if (storageDirectory.exists() && storageDirectory.isDirectory()) {
            File[] files = storageDirectory.listFiles();

            if (files != null && files.length > 0) {

                imageUris.clear();

                for (File file : files) {
                    if (file.isFile() && isVideoFile(file)) {
                        Uri imageUri = Uri.fromFile(file);
                        imageUris.add(imageUri);

                    }
                }

                if (!imageUris.isEmpty()) {

                    RecycleBinAdapter adapter = new RecycleBinAdapter(RecyclebinActivity.this,imageUris,1);
                    RecyclerView.LayoutManager manager = new GridLayoutManager(RecyclebinActivity.this, 3);
                    binding.rvrecyclebin.setLayoutManager(manager);
                    binding.rvrecyclebin.setAdapter(adapter);
                }
            }
        }
    }

    private void getphoto() {
        File storageDirectory = new File(Environment.getExternalStorageDirectory(), "RECYCLEBIN");

        if (storageDirectory.exists() && storageDirectory.isDirectory()) {
            File[] files = storageDirectory.listFiles();

            if (files != null && files.length > 0) {
                imageUris.clear();

                for (File file : files) {
                    if (file.isFile() && isImageFile(file)) {
                        Uri imageUri = Uri.fromFile(file);
                        imageUris.add(imageUri);
                    }
                }
                if (!imageUris.isEmpty()) {

                    RecycleBinAdapter adapter = new RecycleBinAdapter(RecyclebinActivity.this,imageUris, 2);
                    RecyclerView.LayoutManager manager = new GridLayoutManager(RecyclebinActivity.this, 3);
                    binding.rvrecyclebin.setLayoutManager(manager);
                    binding.rvrecyclebin.setAdapter(adapter);
                }
            }
        }
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".png");
    }

    private boolean isVideoFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".mp4");
    }

    @Override
    public void onBackPressed() {
        helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
            @Override
            public void onAdLoadFailed() {
                finish();
            }
            @Override
            public void onInterstitialDismissed() {
                finish();
            }
        });
        super.onBackPressed();
    }

}