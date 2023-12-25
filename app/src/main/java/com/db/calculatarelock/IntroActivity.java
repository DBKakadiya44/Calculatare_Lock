package com.db.calculatarelock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.Adapter.Intro_Adapter;
import com.db.calculatarelock.databinding.ActivityIntroBinding;

import java.lang.annotation.Native;

public class IntroActivity extends AppCompatActivity {
    ActivityIntroBinding binding;
    int position =0;
    InterstitialAD helper;
    AdsManager adsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);


        Intro_Adapter pagerAdapter = new Intro_Adapter(IntroActivity.this);
        binding.viewPager.setAdapter(pagerAdapter);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    binding.ivdot1.setBackgroundResource(R.drawable.dark_dot);
                    binding.ivdot2.setBackgroundResource(R.drawable.light_dot);
                    binding.ivdot3.setBackgroundResource(R.drawable.light_dot);
                }
                if (position == 1) {
                    binding.ivdot1.setBackgroundResource(R.drawable.light_dot);
                    binding.ivdot2.setBackgroundResource(R.drawable.dark_dot);
                    binding.ivdot3.setBackgroundResource(R.drawable.light_dot);
                }
                if (position == 2) {
                    binding.ivdot1.setBackgroundResource(R.drawable.light_dot);;
                    binding.ivdot2.setBackgroundResource(R.drawable.light_dot);;
                    binding.ivdot3.setBackgroundResource(R.drawable.dark_dot);
                    binding.tvNext.setText("Get Started");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        binding.tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                    @Override
                    public void onAdLoadFailed() {
                        position = binding.viewPager.getCurrentItem();
                        position++;
                        binding.viewPager.setCurrentItem(position);
                        if (position == 3) {
                            Intent intent = new Intent(IntroActivity.this, PermissionActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onInterstitialDismissed() {
                        position = binding.viewPager.getCurrentItem();
                        position++;
                        binding.viewPager.setCurrentItem(position);
                        if (position == 3) {
                            Intent intent = new Intent(IntroActivity.this, PermissionActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
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