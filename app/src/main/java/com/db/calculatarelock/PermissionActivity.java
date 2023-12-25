package com.db.calculatarelock;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.databinding.ActivityPermissionBinding;

public class PermissionActivity extends AppCompatActivity {
    ActivityPermissionBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    public static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        binding.textView2.setOnClickListener(view -> {
            getpermission();
        });

    }

    private void getpermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Request READ_EXTERNAL_STORAGE permission
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            } else {
                // Permission is already granted, so start the main activity
                startMainActivity();
            }
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Request READ_EXTERNAL_STORAGE permission
                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            } else {
                // Permission is already granted, so start the main activity
                startMainActivity();
            }
        } else {
            // For devices below Android M, no need to request permissions, so start the main activity
            startMainActivity();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Request READ_EXTERNAL_STORAGE permission
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                } else {
                    // Permission is already granted, so start the main activity
                    startMainActivity();
                }
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Request READ_EXTERNAL_STORAGE permission
                    requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                } else {
                    // Permission is already granted, so start the main activity
                    startMainActivity();
                }
            } else {
                // Request MANAGE_EXTERNAL_STORAGE permission
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, PERMISSION_REQUEST_CODE);
            }
        } else {
            // For devices below Android 11, no need to request MANAGE_EXTERNAL_STORAGE permission
            startMainActivity();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CheckingPermissionIsEnabledOrNot(PermissionActivity.this)) {
            startActivity(new Intent(PermissionActivity.this, CalculatorActivity.class));
        }
    }

    public boolean CheckingPermissionIsEnabledOrNot(Activity activity) {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(activity.getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(activity.getApplicationContext(), READ_EXTERNAL_STORAGE);
        int FourPermissionResult = ContextCompat.checkSelfPermission(activity.getApplicationContext(), RECORD_AUDIO);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FourPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start the main activity
                startMainActivity();
            } else {
                // Permission denied, handle accordingly (e.g., show a message or exit the app)
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // MANAGE_EXTERNAL_STORAGE permission granted, start the main activity
                    startMainActivity();
                } else {
                    // Permission denied, handle accordingly (e.g., show a message or exit the app)
                }
            }
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(PermissionActivity.this, MainActivity.class));

        SplashScreenActivity.editor.putInt("log",1);
        SplashScreenActivity.editor.commit();
        finish();
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