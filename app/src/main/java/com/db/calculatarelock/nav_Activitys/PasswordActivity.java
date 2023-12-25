package com.db.calculatarelock.nav_Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.ADS.Native;
import com.db.calculatarelock.R;
import com.db.calculatarelock.databinding.ActivityPasswordBinding;
import com.db.calculatarelock.password.AtmActivity;
import com.db.calculatarelock.password.BankActivity;
import com.db.calculatarelock.password.CreditCardActivity;
import com.db.calculatarelock.password.EmailActivity;
import com.db.calculatarelock.password.IdCardActivity;
import com.db.calculatarelock.password.LicenseActivity;
import com.db.calculatarelock.password.PassportActivity;
import com.db.calculatarelock.password.SocialActivity;

public class PasswordActivity extends AppCompatActivity {
    ActivityPasswordBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        Native aNative = new Native(this);
        aNative.ShowNative(this, findViewById(R.id.native_container),2);

        binding.ivback.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.pp1.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(PasswordActivity.this , AtmActivity.class));
                }
                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(PasswordActivity.this , AtmActivity.class));
                }
            });

        });
        binding.pp2.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(PasswordActivity.this , BankActivity.class));
                }
                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(PasswordActivity.this , BankActivity.class));
                }
            });

        });

        binding.pp4.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(PasswordActivity.this , CreditCardActivity.class));
                }
                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(PasswordActivity.this , CreditCardActivity.class));
                }
            });

        });

        binding.pp6.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(PasswordActivity.this , EmailActivity.class));
                }
                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(PasswordActivity.this , EmailActivity.class));
                }
            });

        });

        binding.pp9.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(PasswordActivity.this , IdCardActivity.class));
                }
                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(PasswordActivity.this , IdCardActivity.class));
                }
            });

        });

        binding.pp11.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(PasswordActivity.this , LicenseActivity.class));
                }
                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(PasswordActivity.this , LicenseActivity.class));
                }
            });

        });
        binding.pp12.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(PasswordActivity.this , PassportActivity.class));
                }
                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(PasswordActivity.this , PassportActivity.class));
                }
            });

        });
        binding.pp13.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(PasswordActivity.this , SocialActivity.class));
                }
                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(PasswordActivity.this , SocialActivity.class));
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