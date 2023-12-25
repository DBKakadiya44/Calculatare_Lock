package com.db.calculatarelock.nav_Activitys;

import static com.db.calculatarelock.nav_Activitys.ContactActivity.saveArrayList;

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
import com.db.calculatarelock.Adapter.NoteAdapter;
import com.db.calculatarelock.R;
import com.db.calculatarelock.SplashScreenActivity;
import com.db.calculatarelock.databinding.ActivityNotesBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {
    ActivityNotesBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    ArrayList retrievedList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        int cnt = SplashScreenActivity.sharedPreferences.getInt("not",0);

        if(cnt==1){
            retrievedList = getArrayList(this);

            if(!retrievedList.isEmpty()){
                callAdapter();
            }
        }
        binding.ivback.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.btnadd.setOnClickListener(view -> {

            binding.note.setText("");

            binding.btncheck.setVisibility(View.VISIBLE);
            binding.btnadd.setVisibility(View.INVISIBLE);
            binding.ccadddata.setVisibility(View.VISIBLE);
            binding.ccrecycler.setVisibility(View.INVISIBLE);
        });

        binding.btncheck.setOnClickListener(view -> {

            String note = binding.note.getText().toString();

            if(!note.equals("")){
                retrievedList.add(note);
                saveArrayList(this,retrievedList);

                retrievedList = getArrayList(this);

                SplashScreenActivity.editor.putInt("not",1);
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

            binding.btnadd.setVisibility(View.VISIBLE);
            binding.btncheck.setVisibility(View.INVISIBLE);
            binding.ccadddata.setVisibility(View.INVISIBLE);
            binding.ccrecycler.setVisibility(View.VISIBLE);

        });

    }

    public static void saveArrayList(Context context, ArrayList<String> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        SplashScreenActivity.editor.putString("notes", json);
        SplashScreenActivity.editor.apply();
    }
    public static ArrayList<String> getArrayList(Context context) {
        String json = SplashScreenActivity.sharedPreferences.getString("notes", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private void callAdapter() {

        NoteAdapter adapter = new NoteAdapter(NotesActivity.this,retrievedList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(NotesActivity.this);
        binding.noterecycler.setLayoutManager(manager);
        binding.noterecycler.setAdapter(adapter);

    }

    public static void removeItemFromArrayList(Context context, int position) {
        ArrayList<String> arrayList = getArrayList(context);
        if (position >= 0 && position < arrayList.size()) {
            arrayList.remove(position);
            saveArrayList(context, arrayList);
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