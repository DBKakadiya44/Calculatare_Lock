package com.db.calculatarelock.password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.ADS.Native;
import com.db.calculatarelock.Adapter.AtmAdapter;
import com.db.calculatarelock.Adapter.NoteAdapter;
import com.db.calculatarelock.R;
import com.db.calculatarelock.SplashScreenActivity;
import com.db.calculatarelock.databinding.ActivityAtmBinding;
import com.db.calculatarelock.nav_Activitys.NotesActivity;
import com.db.calculatarelock.nav_Activitys.PasswordActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AtmActivity extends AppCompatActivity {
    ActivityAtmBinding binding;
    ArrayList nn1 = new ArrayList();
    ArrayList nn2 = new ArrayList();
    ArrayList nn3 = new ArrayList();
    ArrayList nn4 = new ArrayList();
    ArrayList nn5 = new ArrayList();
    ArrayList nn6 = new ArrayList();
    ArrayList nn7 = new ArrayList();
    InterstitialAD helper;
    AdsManager adsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAtmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        int cnt = SplashScreenActivity.sharedPreferences.getInt("LpasATM",0);

        if(cnt==1){
            nn1 = getArrayList1(this);
            nn2 = getArrayList2(this);
            nn3 = getArrayList3(this);
            nn4 = getArrayList4(this);
            nn5 = getArrayList5(this);
            nn6 = getArrayList6(this);
            nn7 = getArrayList7(this);

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
            String note3 = binding.et3.getText().toString();
            String note4 = binding.et4.getText().toString();
            String note5 = binding.et5.getText().toString();
            String note6 = binding.et6.getText().toString();
            String note7 = binding.et7.getText().toString();

            if(!note1.equals("")){
                nn1.add(note1);
                nn2.add(note2);
                nn3.add(note3);
                nn4.add(note4);
                nn5.add(note5);
                nn6.add(note6);
                nn7.add(note7);

                saveArrayList1(this,nn1);
                saveArrayList2(this,nn2);
                saveArrayList3(this,nn3);
                saveArrayList4(this,nn4);
                saveArrayList5(this,nn5);
                saveArrayList6(this,nn6);
                saveArrayList7(this,nn7);

                nn1 = getArrayList1(this);
                nn2 = getArrayList2(this);
                nn3 = getArrayList3(this);
                nn4 = getArrayList4(this);
                nn5 = getArrayList5(this);
                nn6 = getArrayList6(this);
                nn7 = getArrayList7(this);

                SplashScreenActivity.editor.putInt("LpasATM",1);
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
            binding.et3.setText("");
            binding.et4.setText("");
            binding.et5.setText("");
            binding.et6.setText("");
            binding.et7.setText("");


            binding.btncheck.setVisibility(View.VISIBLE);
            binding.btnadd.setVisibility(View.INVISIBLE);
            binding.ccadddata.setVisibility(View.VISIBLE);
            binding.ccrecycler.setVisibility(View.INVISIBLE);
            binding.nativeContainer.setVisibility(View.INVISIBLE);
        });

        binding.btncheck.setOnClickListener(view -> {

            String note1 = binding.et1.getText().toString();
            String note2 = binding.et2.getText().toString();
            String note3 = binding.et3.getText().toString();
            String note4 = binding.et4.getText().toString();
            String note5 = binding.et5.getText().toString();
            String note6 = binding.et6.getText().toString();
            String note7 = binding.et7.getText().toString();

            if(!note1.equals("")){
                nn1.add(note1);
                nn2.add(note2);
                nn3.add(note3);
                nn4.add(note4);
                nn5.add(note5);
                nn6.add(note6);
                nn7.add(note7);

                saveArrayList1(this,nn1);
                saveArrayList2(this,nn2);
                saveArrayList3(this,nn3);
                saveArrayList4(this,nn4);
                saveArrayList5(this,nn5);
                saveArrayList6(this,nn6);
                saveArrayList7(this,nn7);

                nn1 = getArrayList1(this);
                nn2 = getArrayList2(this);
                nn3 = getArrayList3(this);
                nn4 = getArrayList4(this);
                nn5 = getArrayList5(this);
                nn6 = getArrayList6(this);
                nn7 = getArrayList7(this);

                SplashScreenActivity.editor.putInt("LpasATM",1);
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
        SplashScreenActivity.editor.putString("pasATM1", json);
        SplashScreenActivity.editor.apply();
    }
    public static void saveArrayList2(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("pasATM2", json);
        SplashScreenActivity.editor.apply();
    }
    public static void saveArrayList3(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("pasATM3", json);
        SplashScreenActivity.editor.apply();
    }
    public static void saveArrayList4(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("pasATM4", json);
        SplashScreenActivity.editor.apply();
    }
    public static void saveArrayList5(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("pasATM5", json);
        SplashScreenActivity.editor.apply();
    }
    public static void saveArrayList6(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("pasATM6", json);
        SplashScreenActivity.editor.apply();
    }
    public static void saveArrayList7(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("pasATM7", json);
        SplashScreenActivity.editor.apply();
    }



    public static ArrayList<String> getArrayList1(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("pasATM1", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public static ArrayList<String> getArrayList2(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("pasATM2", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public static ArrayList<String> getArrayList3(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("pasATM3", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public static ArrayList<String> getArrayList4(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("pasATM4", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public static ArrayList<String> getArrayList5(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("pasATM5", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public static ArrayList<String> getArrayList6(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("pasATM6", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public static ArrayList<String> getArrayList7(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("pasATM7", "");
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

        AtmAdapter adapter = new AtmAdapter(AtmActivity.this,nn1,nn2,nn3,nn4,nn5,nn6,nn7);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(AtmActivity.this);
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
    public static void removeItemFromArrayList3(Context context, int position) {
        ArrayList<String> arrayList = getArrayList3(context);
        if (position >= 0 && position < arrayList.size()) {
            arrayList.remove(position);
            saveArrayList3(context, arrayList);
        }
    }
    public static void removeItemFromArrayList4(Context context, int position) {
        ArrayList<String> arrayList = getArrayList4(context);
        if (position >= 0 && position < arrayList.size()) {
            arrayList.remove(position);
            saveArrayList4(context, arrayList);
        }
    }
    public static void removeItemFromArrayList5(Context context, int position) {
        ArrayList<String> arrayList = getArrayList5(context);
        if (position >= 0 && position < arrayList.size()) {
            arrayList.remove(position);
            saveArrayList5(context, arrayList);
        }
    }
    public static void removeItemFromArrayList6(Context context, int position) {
        ArrayList<String> arrayList = getArrayList6(context);
        if (position >= 0 && position < arrayList.size()) {
            arrayList.remove(position);
            saveArrayList6(context, arrayList);
        }
    }
    public static void removeItemFromArrayList7(Context context, int position) {
        ArrayList<String> arrayList = getArrayList7(context);
        if (position >= 0 && position < arrayList.size()) {
            arrayList.remove(position);
            saveArrayList7(context, arrayList);
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