package com.db.calculatarelock.nav_Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.Adapter.ContactAdapter;
import com.db.calculatarelock.R;
import com.db.calculatarelock.SplashScreenActivity;
import com.db.calculatarelock.databinding.ActivityContactBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {
    ActivityContactBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    ArrayList retrievedList = new ArrayList();
    ArrayList retrievedList2 = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this, this, adsManager);

        int cnt = SplashScreenActivity.sharedPreferences.getInt("con", 0);

        if (cnt == 1) {
            retrievedList = getArrayList(this);
            retrievedList2 = getArrayList2(this);

            if (!retrievedList.isEmpty()) {
                callAdapter();
            }
        }


        binding.ivback.setOnClickListener(view -> {
            onBackPressed();
        });
        binding.btnSave.setOnClickListener(view -> {

            String name = binding.etname.getText().toString();
            String number = binding.etnumber.getText().toString();

            if (!name.equals("")) {

                retrievedList.add(name);
                retrievedList2.add(number);

                saveArrayList(this, retrievedList);
                saveArrayList2(this, retrievedList2);

                retrievedList = getArrayList(this);
                retrievedList2 = getArrayList2(this);

                SplashScreenActivity.editor.putInt("con", 1);
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

            binding.ccadddata.setVisibility(View.GONE);
            binding.ccrecycler.setVisibility(View.VISIBLE);

        });
        binding.btnadd.setOnClickListener(view -> {
            binding.ccrecycler.setVisibility(View.GONE);
            binding.ccadddata.setVisibility(View.VISIBLE);

            binding.etnumber.setText("");
            binding.etname.setText("");
        });

    }

    public static void saveArrayList(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("contactname", json);
        SplashScreenActivity.editor.apply();
    }

    public static ArrayList<String> getArrayList(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("contactname", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void saveArrayList2(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("contactnumber", json);
        SplashScreenActivity.editor.apply();
    }

    public static ArrayList<String> getArrayList2(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("contactnumber", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    private void callAdapter() {

        ContactAdapter adapter = new ContactAdapter(ContactActivity.this, retrievedList, retrievedList2);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(ContactActivity.this);
        binding.contactrecycler.setLayoutManager(manager);
        binding.contactrecycler.setAdapter(adapter);

    }

    public static void removeItemFromArrayList(Context context, int position) {
        ArrayList<String> arrayList = getArrayList(context);

        if (position >= 0 && position < arrayList.size()) {
            arrayList.remove(position);
            saveArrayList(context, arrayList);
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