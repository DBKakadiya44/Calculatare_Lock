package com.db.calculatarelock;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.db.calculatarelock.ADS.AdsManager;
import com.db.calculatarelock.ADS.InterstitialAD;
import com.db.calculatarelock.databinding.ActivityCalculatorBinding;

public class CalculatorActivity extends AppCompatActivity {
    ActivityCalculatorBinding binding;
    int setpasslength = 0;
    int firsttime = 0;
    int securituque = 0;
    String n = "";
    int i2 = 0;
    int password1 = 12, password2 = 13;
    int action = 0;
    long firstvalue, secondvalue;
    int i = 1;
    long answer = 0;
    boolean NNN = true;
    boolean CCC = false;
    InterstitialAD helper;
    AdsManager adsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        firsttime = SplashScreenActivity.sharedPreferences.getInt("FirstTime", 0);
        securituque = SplashScreenActivity.sharedPreferences.getInt("SECURITY", 0);

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        checkfirsttime();
    }

    private void checkfirsttime() {
        if (firsttime == 0) {
            i = 1;
            i2 = 0;
            binding.txtxtxt.setVisibility(View.VISIBLE);
            binding.textView16.setVisibility(View.VISIBLE);
            allbtnclick();
        }else
        if (firsttime == 1) {
            i = 2;
            i2 = 2;
            binding.txtxtxt.setVisibility(View.INVISIBLE);
            binding.textView16.setText("");
            allbtnclick();
        }
    }

    @SuppressLint("SetTextI18n")
    private void allbtnclick() {
        binding.btac.setOnClickListener(view -> {
            n = "";
            binding.textView15.setText("" + n);
            checkfirsttime();
            action = 0;
            NNN = true;
        });
        binding.btpersantage.setOnClickListener(view -> {
            if (i == 2 && CCC == true) {
                NNN = true;
                if (action == 0) {
                    firstvalue = Integer.parseInt(binding.textView15.getText().toString());
                    binding.textView15.setText("%");
                    binding.textView16.setText("" + firstvalue + "%");
                    n = "";
                    action = 1;
                }
            }
        });
        binding.btdivide.setOnClickListener(view -> {
            if (i == 2 && CCC == true) {
                NNN = true;
                if (action == 0) {
                    firstvalue = Integer.parseInt(binding.textView15.getText().toString());
                    binding.textView15.setText("÷");
                    binding.textView16.setText("" + firstvalue + "÷");
                    n = "";
                    action = 2;
                }
            }
        });
        binding.btmulti.setOnClickListener(view -> {
            if (i == 2 && CCC == true) {
                NNN = true;
                if (action == 0) {
                    firstvalue = Integer.parseInt(binding.textView15.getText().toString());
                    binding.textView15.setText("×");
                    binding.textView16.setText("" + firstvalue + "×");
                    n = "";
                    action = 3;
                }
            }
        });
        binding.btminus.setOnClickListener(view -> {
            if (i == 2 && CCC == true) {
                NNN = true;
                if (action == 0) {
                    firstvalue = Integer.parseInt(binding.textView15.getText().toString());
                    binding.textView15.setText("-");
                    binding.textView16.setText("" + firstvalue + "-");
                    n = "";
                    action = 4;
                }
            }
        });
        binding.btplus.setOnClickListener(view -> {
            if (i == 2 && CCC == true) {
                NNN = true;
                if (action == 0) {
                    firstvalue = Integer.parseInt(binding.textView15.getText().toString());
                    binding.textView15.setText("+");
                    binding.textView16.setText("" + firstvalue + "+");
                    n = "";
                    action = 5;
                }
            }
        });
        binding.btback.setOnClickListener(view -> {
            String n2 = binding.textView15.getText().toString();
            if(n2.length()>0){
                n = n2.substring(0,n2.length()-1);
                binding.textView15.setText(""+n);
            }
        });
        binding.textView14.setOnClickListener(view -> {
            if (i == 1 && CCC == true) {

                n="";

                setpasslength = binding.textView15.length();
                if (setpasslength == 4) {
                    if (i2 == 0) {
                        int take1 = Integer.parseInt(binding.textView15.getText().toString());
                        password1 = take1;
                        n = "";
                        binding.textView15.setText("");
                        i2 = 1;
                        binding.txtxtxt.setText("Confirm your Password");
                    } else if (i2 == 1) {
                        int take2 = Integer.parseInt(binding.textView15.getText().toString());
                        password2 = take2;
                        n = "";
                        binding.textView15.setText("");
                        i2 = 2;
                        i = 2;
                        if (password2 == password1) {
                            Toast.makeText(this, "Password set sucessfully...", Toast.LENGTH_SHORT).show();

                            passwordset(1);

                        } else {
                            i = 1;
                            i2 = 0;
                            Toast.makeText(this, "Both Password must be same...", Toast.LENGTH_SHORT).show();
                            binding.txtxtxt.setText("Enter 4 Digit Password");
                        }
                    }

                } else {
                    Toast.makeText(this, "Enter4 number Password", Toast.LENGTH_SHORT).show();
                }
            }else if (i == 2 && CCC == true) {
                if (NNN) {

                    if (action == 0) {
                        passwordset(2);
                    }
                    if (action == 1) {
                        secondvalue = Integer.parseInt(binding.textView15.getText().toString());
                        n = "";
                        answer = (firstvalue * secondvalue) / 100;
                        binding.textView15.setText("" + answer);
                        binding.textView16.setText("" + firstvalue + " % " + secondvalue);
                        action = 0;
                        NNN = false;
                    }
                    if (action == 2) {
                        secondvalue = Integer.parseInt(binding.textView15.getText().toString());
                        n = "";
                        answer = firstvalue / secondvalue;
                        binding.textView15.setText("" + answer);
                        binding.textView16.setText("" + firstvalue + " ÷ " + secondvalue);
                        action = 0;
                        NNN = false;
                    }
                    if (action == 3) {
                        secondvalue = Integer.parseInt(binding.textView15.getText().toString());
                        n = "";
                        answer = firstvalue * secondvalue;
                        binding.textView15.setText("" + answer);
                        binding.textView16.setText("" + firstvalue + " × " + secondvalue);
                        action = 0;
                        NNN = false;
                    }
                    if (action == 4) {
                        secondvalue = Integer.parseInt(binding.textView15.getText().toString());
                        n = "";
                        answer = firstvalue - secondvalue;
                        binding.textView15.setText("" + answer);
                        binding.textView16.setText("" + firstvalue + " - " + secondvalue);
                        action = 0;
                        NNN = false;
                    }
                    if (action == 5) {
                        secondvalue = Integer.parseInt(binding.textView15.getText().toString());
                        n = "";
                        answer = firstvalue + secondvalue;
                        binding.textView15.setText("" + answer);
                        binding.textView16.setText("" + firstvalue + " + " + secondvalue);
                        action = 0;
                        NNN = false;
                    }
                }
            }
        });

        binding.bt1.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 3) {
                    n += "1";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                CCC = true;
                setpasslength = binding.textView15.length();
                if (setpasslength <= 8) {
                    n += "1";
                    binding.textView15.setText("" + n);
                }
            }
        });
        binding.bt2.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 3) {
                    n += "2";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            CCC = true;
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                setpasslength = binding.textView15.length();
                if (setpasslength <= 8) {
                    n += "2";
                    binding.textView15.setText("" + n);
                }
            }
        });
        binding.bt3.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 3) {
                    n += "3";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            CCC = true;
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                setpasslength = binding.textView15.length();
                if (setpasslength <= 8) {
                    n += "3";
                    binding.textView15.setText("" + n);
                }
            }
        });
        binding.bt4.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 3) {
                    n += "4";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            CCC = true;
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                setpasslength = binding.textView15.length();
                if (setpasslength <= 8) {
                    n += "4";
                    binding.textView15.setText("" + n);
                }
            }
        });
        binding.bt5.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 3) {
                    n += "5";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            CCC = true;
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                setpasslength = binding.textView15.length();
                if (setpasslength <= 8) {
                    n += "5";
                    binding.textView15.setText("" + n);
                }
            }
        });
        binding.bt6.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 3) {
                    n += "6";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            CCC = true;
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                setpasslength = binding.textView15.length();
                if (setpasslength <= 8) {
                    n += "6";
                    binding.textView15.setText("" + n);
                }
            }
        });
        binding.bt7.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 3) {
                    n += "7";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            CCC = true;
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                setpasslength = binding.textView15.length();
                if (setpasslength <= 8) {
                    n += "7";
                    binding.textView15.setText("" + n);
                }
            }
        });
        binding.bt8.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 3) {
                    n += "8";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            CCC = true;
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                setpasslength = binding.textView15.length();
                if (setpasslength <= 8) {
                    n += "8";
                    binding.textView15.setText("" + n);
                }
            }
        });
        binding.bt9.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 3) {
                    n += "9";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            CCC = true;
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                setpasslength = binding.textView15.length();
                if (setpasslength <= 8) {
                    n += "9";
                    binding.textView15.setText("" + n);
                }
            }
        });
        binding.bt0.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 3) {
                    n += "0";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            CCC = true;
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                setpasslength = binding.textView15.length();
                if (setpasslength <= 8) {
                    n += "0";
                    binding.textView15.setText("" + n);
                }
            }
        });
        binding.btpoint.setOnClickListener(view -> {
            if (i == 1) {
                setpasslength = binding.textView15.length();
                if (setpasslength <= 2) {
                    n += "00";
                    binding.textView15.setText("" + n);
                }
                CCC = true;
            }
            CCC = true;
            if (i == 2) {
                if (NNN == false) {
                    n = "";
                    binding.textView15.setText("");
                    binding.textView16.setText("");
                    NNN = true;
                }
                setpasslength = binding.textView15.length();
                if (setpasslength <= 7) {
                    n += "00";
                    binding.textView15.setText("" + n);
                }
            }
        });

    }

    private void passwordset(int i) {

        if(i==1){
            SplashScreenActivity.editor.putInt("FirstTime", 1);
            SplashScreenActivity.editor.commit();
            SplashScreenActivity.editor.putInt("PASSWORD", password1);
            SplashScreenActivity.editor.commit();

            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(CalculatorActivity.this, QuestionActivity.class));
                    finish();
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(CalculatorActivity.this, QuestionActivity.class));
                    finish();
                }
            });


        }

        if (i == 2) {

            int passcheck = Integer.parseInt(binding.textView15.getText().toString());
            int passoriginal = SplashScreenActivity.sharedPreferences.getInt("PASSWORD", 0);

            if (passcheck == passoriginal) {
                helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                    @Override
                    public void onAdLoadFailed() {
                        if(securituque==1) {
                            startActivity(new Intent(CalculatorActivity.this, HomeActivity.class));
                        }else {
                            startActivity(new Intent(CalculatorActivity.this, QuestionActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onInterstitialDismissed() {
                        if(securituque==1) {
                            startActivity(new Intent(CalculatorActivity.this, HomeActivity.class));
                        }else {
                            startActivity(new Intent(CalculatorActivity.this, QuestionActivity.class));
                            finish();
                        }
                    }
                });

            }

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