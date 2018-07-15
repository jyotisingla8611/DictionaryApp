package com.example.lenovopn.dictionaryapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
        private static int time_splash=4000;
        String str="Dictionary App";

        DatabaseHelper db;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            db = new DatabaseHelper(getApplicationContext());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    Intent i= new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            },time_splash);

        }
}
