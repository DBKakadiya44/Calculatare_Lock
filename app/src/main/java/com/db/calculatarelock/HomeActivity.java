package com.db.calculatarelock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.db.calculatarelock.ADS.AdName;
import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.databinding.ActivityHomeBinding;
import com.db.calculatarelock.nav_Activitys.AudioActivity;
import com.db.calculatarelock.nav_Activitys.CloudActivity;
import com.db.calculatarelock.nav_Activitys.ContactActivity;
import com.db.calculatarelock.nav_Activitys.DocumentActivity;
import com.db.calculatarelock.nav_Activitys.GalleryActivity;
import com.db.calculatarelock.nav_Activitys.NotesActivity;
import com.db.calculatarelock.nav_Activitys.PasswordActivity;
import com.db.calculatarelock.nav_Activitys.RecyclebinActivity;
import com.db.calculatarelock.nav_Activitys.SettingActivity;
import com.db.calculatarelock.nav_Activitys.VideoActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentPhotoPath;
    InterstitialAD helper;
    AdsManager adsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this, this, adsManager);

        binding.inc.ivToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        binding.menus.nav1.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    binding.drawerLayout.close();
                    startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    binding.drawerLayout.close();
                    startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                }
            });

        });
        binding.menus.nav3.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    binding.drawerLayout.close();
                }

                @Override
                public void onInterstitialDismissed() {
                    binding.drawerLayout.close();
                }
            });
        });
        binding.menus.nav4.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    binding.drawerLayout.close();
                }

                @Override
                public void onInterstitialDismissed() {
                    binding.drawerLayout.close();
                }
            });
        });
        binding.menus.nav5.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    binding.drawerLayout.close();
                    SharedPreferences sharedPreferences = getSharedPreferences("ADDEMO", Context.MODE_PRIVATE);
                    String url = sharedPreferences.getString(AdName.PrivacyPolicy, "");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }

                @Override
                public void onInterstitialDismissed() {
                    binding.drawerLayout.close();
                    SharedPreferences sharedPreferences = getSharedPreferences("ADDEMO", Context.MODE_PRIVATE);
                    String url = sharedPreferences.getString(AdName.PrivacyPolicy, "");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            });
        });
        binding.menus.nav6.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    binding.drawerLayout.close();
                }

                @Override
                public void onInterstitialDismissed() {
                    binding.drawerLayout.close();
                }
            });
        });
        binding.menus.nav7.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    binding.drawerLayout.close();
                }

                @Override
                public void onInterstitialDismissed() {
                    binding.drawerLayout.close();
                }
            });
        });

        binding.inc.gallery.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(HomeActivity.this, GalleryActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(HomeActivity.this, GalleryActivity.class));
                }
            });

        });
        binding.inc.videos.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(HomeActivity.this, VideoActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(HomeActivity.this, VideoActivity.class));
                }
            });

        });
        binding.inc.camera.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    createImageFolder();
                    dispatchTakePictureIntent();
                }

                @Override
                public void onInterstitialDismissed() {
                    createImageFolder();
                    dispatchTakePictureIntent();
                }
            });

        });
        binding.inc.audio.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(HomeActivity.this, AudioActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(HomeActivity.this, AudioActivity.class));
                }
            });

        });
        binding.inc.password.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(HomeActivity.this, PasswordActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(HomeActivity.this, PasswordActivity.class));
                }
            });

        });

        binding.inc.document.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(HomeActivity.this, DocumentActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(HomeActivity.this, DocumentActivity.class));
                }
            });

        });
        binding.inc.notes.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(HomeActivity.this, NotesActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(HomeActivity.this, NotesActivity.class));
                }
            });

        });
        binding.inc.contact.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(HomeActivity.this, ContactActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(HomeActivity.this, ContactActivity.class));
                }
            });

        });
        binding.inc.recyclebin.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(HomeActivity.this, RecyclebinActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(HomeActivity.this, RecyclebinActivity.class));
                }
            });

        });
        binding.inc.cloud.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(HomeActivity.this, CloudActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(HomeActivity.this, CloudActivity.class));
                }
            });

        });

        binding.inc.setting.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                }
            });

        });
    }

    private void createImageFolder() {
        File storageDirectory = new File(Environment.getExternalStorageDirectory(), "HIDEPHOTO");

        if (!storageDirectory.exists()) {
            storageDirectory.mkdirs();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDirectory = new File(Environment.getExternalStorageDirectory(), "HIDEPHOTO");
        if (!storageDirectory.exists()) {
            storageDirectory.mkdirs();
        }
        File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(
                        HomeActivity.this,
                        "com.db.calculatarelock.fileprovider",
                        photoFile
                );
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Log.d("ImageFilePath", currentPhotoPath);
        }
    }

    @Override
    public void onBackPressed() {
        helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
            @Override
            public void onAdLoadFailed() {
                startActivity(new Intent(HomeActivity.this, CalculatorActivity.class));
            }

            @Override
            public void onInterstitialDismissed() {
                startActivity(new Intent(HomeActivity.this, CalculatorActivity.class));
            }
        });

        super.onBackPressed();
    }
}