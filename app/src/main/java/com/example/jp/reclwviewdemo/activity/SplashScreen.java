package com.example.jp.reclwviewdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jp.reclwviewdemo.Login_Register.Login_page;
import com.example.jp.reclwviewdemo.R;

public class SplashScreen extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String KEY_USERNAME = "username";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    if (sharedpreferences.contains(KEY_USERNAME)) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(),Login_page.class);

                        startActivity(intent);
                    }

                }
            }
        };
        timerThread.start();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}

