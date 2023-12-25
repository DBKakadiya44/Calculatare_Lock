package com.db.calculatarelock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.db.calculatarelock.ADS.AppOpenManager;
import com.db.calculatarelock.ADS.SharedPrefs;
import com.db.calculatarelock.databinding.ActivitySplashScreenBinding;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;

public class SplashScreenActivity extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    private static final String LOG_TAG = "SplashActivity";

    Boolean sd;
    Handler handler;
    private CountDownTimer countDownTimer = null;
    private AppOpenManager appOpenManager;
    private TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        sharedPreferences = getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
        sharedPrefs.loaddata();

//        timer = findViewById(R.id.tv_timer);

        FirebaseApp.initializeApp(this);
        MobileAds.initialize(this, initializationStatus -> {
        });

        appOpenManager = new AppOpenManager(this);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {
            appOpenManager.fetchAd();
            countDownTimer = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    long seconds = millisUntilFinished / 1000;

//                    timer.setText(String.valueOf(seconds));
                    int i = Integer.parseInt(String.valueOf(seconds));

                    if (i == 0) {
                        if (AppOpenManager.isAdLoaded()) {
                            Log.d(LOG_TAG, "Showing ad");
                            appOpenManager.showAdIfAvailable();
                            cancel();
                        }
                    }
                }

                @Override
                public void onFinish() {
                    if (!AppOpenManager.isAdLoaded()) {
                        intentToHomeScreen();
                        Log.d(LOG_TAG, "Ad not shown");
                    }
                }
            }.start();
        } else {
            new Handler().postDelayed(() -> intentToHomeScreen(), 6000);
        }

    }

    public void intentToHomeScreen() {
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int log = sharedPreferences.getInt("log", 0);

                if (log == 1) {
                    startActivity(new Intent(SplashScreenActivity.this,CalculatorActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreenActivity.this,IntroActivity.class));
                    finish();
                }
            }
        }, 2000);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirstTime", false);
        editor.apply();

    }

    public void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            Log.d("mmmm", "stop countdown");
        }
    }
}