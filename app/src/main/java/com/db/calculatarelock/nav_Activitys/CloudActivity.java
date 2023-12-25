package com.db.calculatarelock.nav_Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.ADS.Native;
import com.db.calculatarelock.R;
import com.db.calculatarelock.databinding.ActivityCloudBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class CloudActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityCloudBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    private Button link_drive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCloudBinding.inflate(getLayoutInflater());
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

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setTitle("Cloud Backup");
        initView();
        initListener();

    }

    private void initListener() {
        link_drive.setOnClickListener(this);
    }

    private void initView() {
        link_drive = (Button) findViewById(R.id.link_drive);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        onBackPressed();
        return false;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.link_drive) {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    requestSignin();
                }

                @Override
                public void onInterstitialDismissed() {
                    requestSignin();
                }
            });

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
    private void requestSignin() {
        startActivityForResult(GoogleSignIn.getClient((Activity) this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestScopes(new Scope("https://www.googleapis.com/auth/drive.file"), new Scope[0]).build()).getSignInIntent(), 100);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            handleSignInIntent(intent);
            if (intent != null) {
                intent.getAction();
            }
        }
    }

    private void handleSignInIntent(Intent intent) {
        GoogleSignIn.getSignedInAccountFromIntent(intent).addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
            public void onSuccess(GoogleSignInAccount googleSignInAccount) {
                Log.e("asd", "onSucces: " + googleSignInAccount.getDisplayName());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception exc) {
                Log.e("asd", "onFailure: " + exc.getMessage());
                exc.printStackTrace();
            }
        });
    }

}