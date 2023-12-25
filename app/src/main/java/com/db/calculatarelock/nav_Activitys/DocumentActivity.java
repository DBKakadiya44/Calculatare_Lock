package com.db.calculatarelock.nav_Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.ADS.Native;
import com.db.calculatarelock.Adapter.DocumentAdapter;
import com.db.calculatarelock.R;
import com.db.calculatarelock.databinding.ActivityDocumentBinding;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DocumentActivity extends AppCompatActivity {
    ActivityDocumentBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    private static final int PICK_FILE_REQUEST_CODE = 1;

    ArrayList docpath = new ArrayList();
    ArrayList docname = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);



        callAdapter();

        binding.ivback.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.btnadd.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    openPdf();
                }

                @Override
                public void onInterstitialDismissed() {
                    openPdf();
                }
            });

        });

    }


    private void callAdapter() {

        List<String> pdfPaths = getAllPdfsFromFolder( Environment.getExternalStorageDirectory() + "/HIDEDOC");
        List<String> pdfName = getAllPdfsName( Environment.getExternalStorageDirectory() + "/HIDEDOC");

        Log.d("AAAS", "callAdapter: path = "+pdfPaths);

        docpath.clear();
        docname.clear();

        for (String document : pdfPaths) {
            docpath.add(document);
        }
        for (String document : pdfName) {
            docname.add(document);
        }


        if(!docpath.isEmpty()){

            Native aNative = new Native(this);
            aNative.ShowNative(this, findViewById(R.id.native_container),2);

            DocumentAdapter adapter = new DocumentAdapter(DocumentActivity.this,docname,docpath);
            RecyclerView.LayoutManager manager = new GridLayoutManager(DocumentActivity.this,3);
            binding.rvDocument.setLayoutManager(manager);
            binding.rvDocument.setAdapter(adapter);
        }
    }

    private void openPdf() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, 1); // Use onActivityResult to handle the selected PDF
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedPdfUri = data.getData();
            // Save the selected PDF to a new folder (call a method to handle this)
            savePdfToNewFolder(selectedPdfUri);
        }
    }

    private void savePdfToNewFolder(Uri selectedPdfUri) {
        File storageDirectory = new File(Environment.getExternalStorageDirectory(), "HIDEDOC");
        if (!storageDirectory.exists()) {
            storageDirectory.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "DOC_" + timeStamp + ".pdf";
        File destImageFile = new File(storageDirectory, imageFileName);

        try {
            InputStream inputStream = getContentResolver().openInputStream(selectedPdfUri);
            FileUtils.copyInputStreamToFile(inputStream, destImageFile);
            inputStream.close();
            callAdapter();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllPdfsFromFolder(String folderPath) {
        List<String> pdfPaths = new ArrayList<>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".pdf")) {
                    pdfPaths.add(file.getAbsolutePath());
                }
            }
        }

        return pdfPaths;
    }

    public static List<String> getAllPdfsName(String folderPath) {
        List<String> pdfname = new ArrayList<>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".pdf")) {
                    pdfname.add(file.getName());
                }
            }
        }

        return pdfname;
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