package com.example.lenovopn.dictionaryapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class WordDisplayActivity extends AppCompatActivity {

    Intent intent;
    DatabaseHelper db;
    ImageButton left,right;
    TextSwitcher textDisplay;
    String word;
    int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_display);
        Log.d("wordDisplayActivity", "start");

        db = new DatabaseHelper(getApplicationContext());
        //db.insert();
        intent = getIntent();
        left = findViewById(R.id.leftbut);
        right = findViewById(R.id.rightbut);
        textDisplay =findViewById(R.id.textSwitcher);
        textDisplay.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textDis = new TextView(WordDisplayActivity.this);
                textDis.setGravity(Gravity.TOP | Gravity.CENTER_VERTICAL);
                textDis.setTextSize(24);
                textDis.setTextColor(Color.BLUE);
                return textDis;
            }
        });
        Log.d("wordDisplayActivity", "mid");
        word = intent.getStringExtra("word");
        String result = db.search(word);
        currentIndex = db.getIndex(word);
        Log.e("result returned",result);
        if(currentIndex != -1) {
            textDisplay.setText(result);

        }else {
            textDisplay.setText("No Data Found");
            left.setEnabled(false);
            right.setEnabled(false);
        }

        if(result != "")
            textDisplay.setText(result);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentIndex>1) {
                    left.setEnabled(true);
                }
                if(currentIndex>15) {
                    right.setEnabled(false);
                } else {
                    currentIndex = currentIndex+1;
                    Log.e("ryt",""+currentIndex);

                    word = db.getStrings(currentIndex);
                    Log.e("ryt",word);
                    String result = db.search(word);
                    Log.e("string ryt",result);
                    textDisplay.setText(result);
                    if(result != "")
                        textDisplay.setText(result);
                }

            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentIndex<2) {
                    left.setEnabled(false);
                } else {
                    right.setEnabled(true);
                    currentIndex = currentIndex-1;
                    Log.e("ryt",""+currentIndex);
                    word = db.getStrings(currentIndex);
                    Log.e("ryt",""+word);
                    String result = db.search(word);
                    textDisplay.setText(result);
                    Log.e("string ryt",result);
                    if(result != "")
                        textDisplay.setText(result);
                }
            }
        });
    }
}