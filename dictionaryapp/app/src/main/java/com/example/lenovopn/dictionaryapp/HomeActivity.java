package com.example.lenovopn.dictionaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final EditText search=findViewById(R.id.et1);
        btn = findViewById(R.id.but1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("homeActivity", "buttonclick");
                Intent intent = new Intent(getApplicationContext(), WordDisplayActivity.class);
                intent.putExtra("word",search.getText().toString());
                Log.d("homeActivity", "intent");
                startActivity(intent);
            }
        });
    }
}
