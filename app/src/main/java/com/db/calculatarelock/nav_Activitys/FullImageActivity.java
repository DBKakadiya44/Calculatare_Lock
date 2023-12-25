package com.db.calculatarelock.nav_Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.Adapter.ImagePagerAdapter;
import com.db.calculatarelock.R;
import com.db.calculatarelock.databinding.ActivityFullImageBinding;
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

public class FullImageActivity extends AppCompatActivity {
    ActivityFullImageBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    ArrayList<Uri> imageUris;
    int pos;
    int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        binding.ivback.setOnClickListener(view -> {
            onBackPressed();
        });

        imageUris = getIntent().getParcelableArrayListExtra("image");
        pos = getIntent().getIntExtra("pos", 0);
        cnt = getIntent().getIntExtra("cnt", 0);

        callAdapter();

        binding.btnmenu.setOnClickListener(view -> {
            if(cnt==1) {
                showPopupMenu(view);
            }if(cnt==2){
                showPopupMenu2(view);
            }
        });


    }

    private void showPopupMenu2(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu_2);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.menu_item_2) {

                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            Files.deleteIfExists(Paths.get(imageUris.get(pos).getPath()));
                            Toast.makeText(FullImageActivity.this, "Image Deleted...", Toast.LENGTH_SHORT).show();
                            callAdapter();
                            startActivity(new Intent(FullImageActivity.this , RecyclebinActivity.class));
                            finish();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                }
                else if (item.getItemId() == R.id.menu_item_1) {

                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                            RestoreImageToStorage(imageUris.get(pos));

                            Files.deleteIfExists(Paths.get(imageUris.get(pos).getPath()));
                            callAdapter();
                            startActivity(new Intent(FullImageActivity.this , RecyclebinActivity.class));
                            finish();
                            Toast.makeText(FullImageActivity.this, "Image Restored Sucessfully...", Toast.LENGTH_SHORT).show();
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
    }

    private void callAdapter() {
        ImagePagerAdapter pagerAdapter = new ImagePagerAdapter(FullImageActivity.this, imageUris);
        binding.photoPager.setAdapter(pagerAdapter);
        binding.photoPager.setCurrentItem(pos);
        pagerAdapter.notifyDataSetChanged();
    }

    private void showPopupMenu(View anchorView) {
        PopupMenu popupMenu = new PopupMenu(this, anchorView);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.menu_item_1) {

                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            Files.deleteIfExists(Paths.get(imageUris.get(pos).getPath()));
                            Toast.makeText(FullImageActivity.this, "Image Unhide Sucessfully...", Toast.LENGTH_SHORT).show();
                            callAdapter();
                            startActivity(new Intent(FullImageActivity.this , GalleryActivity.class));
                            finish();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                } else if (item.getItemId() == R.id.menu_item_2) {

                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                            copyImageToStorage(imageUris.get(pos));

                            Files.deleteIfExists(Paths.get(imageUris.get(pos).getPath()));
                            callAdapter();
                            startActivity(new Intent(FullImageActivity.this , GalleryActivity.class));
                            finish();
                            Toast.makeText(FullImageActivity.this, "Image Deleted...", Toast.LENGTH_SHORT).show();
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

    }

    private void copyImageToStorage(Uri sourceUri) {

        File storageDirectory = new File(Environment.getExternalStorageDirectory(), "RECYCLEBIN");
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void RestoreImageToStorage(Uri sourceUri) {

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

        } catch (IOException e) {
            e.printStackTrace();
        }
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


