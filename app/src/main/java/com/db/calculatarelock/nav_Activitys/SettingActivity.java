package com.db.calculatarelock.nav_Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.CalculatorActivity;
import com.db.calculatarelock.QuestionActivity;
import com.db.calculatarelock.R;
import com.db.calculatarelock.SplashScreenActivity;
import com.db.calculatarelock.databinding.ActivitySettingBinding;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
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

        binding.constraintLayout.setOnClickListener(view -> {
            //change password
            SplashScreenActivity.editor.putInt("FirstTime",0);
            SplashScreenActivity.editor.commit();
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(SettingActivity.this , CalculatorActivity.class));
                    finish();
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(SettingActivity.this , CalculatorActivity.class));
                    finish();
                }
            });

        });
        binding.constraintLayout3.setOnClickListener(view -> {
            //change question
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(SettingActivity.this , QuestionActivity.class));
                    finish();
                }
                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(SettingActivity.this , QuestionActivity.class));
                    finish();
                }
            });

        });
        binding.constraintLayout4.setOnClickListener(view -> {
            //share
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {

                }
                @Override
                public void onInterstitialDismissed() {

                }
            });
        });
        binding.constraintLayout5.setOnClickListener(view -> {
            //rate us
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {

                }
                @Override
                public void onInterstitialDismissed() {

                }
            });
        });
        binding.constraintLayout6.setOnClickListener(view -> {
            //contact us
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {

                }
                @Override
                public void onInterstitialDismissed() {

                }
            });
        });
        binding.constraintLayout7.setOnClickListener(view -> {
            //privacy policy
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {

                }
                @Override
                public void onInterstitialDismissed() {

                }
            });
        });

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