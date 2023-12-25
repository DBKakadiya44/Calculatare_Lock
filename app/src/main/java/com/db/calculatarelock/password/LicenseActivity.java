package com.db.calculatarelock.password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.ADS.Native;
import com.db.calculatarelock.Adapter.IDAdapter;
import com.db.calculatarelock.Adapter.LicenseAdapter;
import com.db.calculatarelock.R;
import com.db.calculatarelock.SplashScreenActivity;
import com.db.calculatarelock.databinding.ActivityLicenseBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LicenseActivity extends AppCompatActivity {
    ActivityLicenseBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    ArrayList nn1 = new ArrayList();
    ArrayList nn2 = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLicenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        int cnt = SplashScreenActivity.sharedPreferences.getInt("LpasLICENSE",0);

        if(cnt==1){
            nn1 = getArrayList1(this);
            nn2 = getArrayList2(this);

            if(!nn1.isEmpty()){

                        callAdapter();

            }
        }

        binding.ivback.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.btnSave.setOnClickListener(view -> {

            String note1 = binding.et1.getText().toString();
            String note2 = binding.et2.getText().toString();

            if(!note1.equals("")){
                nn1.add(note1);
                nn2.add(note2);

                saveArrayList1(this,nn1);
                saveArrayList2(this,nn2);

                nn1 = getArrayList1(this);
                nn2 = getArrayList2(this);

                SplashScreenActivity.editor.putInt("LpasLICENSE",1);
                SplashScreenActivity.editor.commit();

                helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                    @Override
                    public void onAdLoadFailed() {
                        callAdapter();
                    }

                    @Override
                    public void onInterstitialDismissed() {
                        callAdapter();
                    }
                });
            }



        });

        binding.btnadd.setOnClickListener(view -> {

            binding.et1.setText("");
            binding.et2.setText("");


            binding.btncheck.setVisibility(View.VISIBLE);
            binding.btnadd.setVisibility(View.INVISIBLE);
            binding.ccadddata.setVisibility(View.VISIBLE);
            binding.ccrecycler.setVisibility(View.INVISIBLE);
            binding.nativeContainer.setVisibility(View.INVISIBLE);
        });

        binding.btncheck.setOnClickListener(view -> {

            String note1 = binding.et1.getText().toString();
            String note2 = binding.et2.getText().toString();

            if(!note1.equals("")){
                nn1.add(note1);
                nn2.add(note2);

                saveArrayList1(this,nn1);
                saveArrayList2(this,nn2);

                nn1 = getArrayList1(this);
                nn2 = getArrayList2(this);

                SplashScreenActivity.editor.putInt("LpasLICENSE",1);
                SplashScreenActivity.editor.commit();

                helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                    @Override
                    public void onAdLoadFailed() {
                        callAdapter();
                    }

                    @Override
                    public void onInterstitialDismissed() {
                        callAdapter();
                    }
                });
            }

        });

    }

    public static void saveArrayList1(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("pasLICENSE1", json);
        SplashScreenActivity.editor.apply();
    }
    public static void saveArrayList2(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("pasLICENSE2", json);
        SplashScreenActivity.editor.apply();
    }

    public static ArrayList<String> getArrayList1(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("pasLICENSE1", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public static ArrayList<String> getArrayList2(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("pasLICENSE2", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private void callAdapter() {

        binding.btnadd.setVisibility(View.VISIBLE);
        binding.btncheck.setVisibility(View.INVISIBLE);
        binding.ccadddata.setVisibility(View.INVISIBLE);
        binding.ccrecycler.setVisibility(View.VISIBLE);
        binding.nativeContainer.setVisibility(View.VISIBLE);

        Native aNative = new Native(this);
        aNative.ShowNative(this, findViewById(R.id.native_container),2);

        LicenseAdapter adapter = new LicenseAdapter(LicenseActivity.this,nn1,nn2);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(LicenseActivity.this);
        binding.noterecycler.setLayoutManager(manager);
        binding.noterecycler.setAdapter(adapter);

    }

    public static void removeItemFromArrayList1(Context context, int position) {
        ArrayList<String> arrayList = getArrayList1(context);
        if (position >= 0 && position < arrayList.size()) {
            arrayList.remove(position);
            saveArrayList1(context, arrayList);
        }
    }
    public static void removeItemFromArrayList2(Context context, int position) {
        ArrayList<String> arrayList = getArrayList2(context);
        if (position >= 0 && position < arrayList.size()) {
            arrayList.remove(position);
            saveArrayList2(context, arrayList);
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