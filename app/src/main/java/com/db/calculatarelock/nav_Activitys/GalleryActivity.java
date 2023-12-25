package com.db.calculatarelock.nav_Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.ADS.Native;
import com.db.calculatarelock.Adapter.GalleryAdapter;
import com.db.calculatarelock.R;
import com.db.calculatarelock.databinding.ActivityGalleryBinding;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GalleryActivity extends AppCompatActivity {
    ActivityGalleryBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    private static final int PICK_IMAGE_REQUEST = 1;
    ArrayList<Uri> imageUris = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);



        callAdapter();

        binding.btnadd.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    openGallery();
                }

                @Override
                public void onInterstitialDismissed() {
                    openGallery();
                }
            });

        });
        binding.ivback.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            copyImageToStorage(selectedImageUri);
        }
    }

    private void copyImageToStorage(Uri sourceUri) {

        File storageDirectory = new File(Environment.getExternalStorageDirectory(), "HIDEPHOTO");
        if (!storageDirectory.exists()) {
            storageDirectory.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + ".jpg";
        File destImageFile = new File(storageDirectory, imageFileName);

        try {
            InputStream inputStream = getContentResolver().openInputStream(sourceUri);
            FileUtils.copyInputStreamToFile(inputStream, destImageFile);
            inputStream.close();
            callAdapter();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        openGallery();
    }

    private void callAdapter() {

        File storageDirectory = new File(Environment.getExternalStorageDirectory(), "HIDEPHOTO");

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

                    Native aNative = new Native(this);
                    aNative.ShowNative(this, findViewById(R.id.native_container),2);

                    GalleryAdapter adapter = new GalleryAdapter(GalleryActivity.this, imageUris);
                    RecyclerView.LayoutManager manager = new GridLayoutManager(GalleryActivity.this, 3);
                    binding.photoRecycler.setLayoutManager(manager);
                    binding.photoRecycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".png");
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