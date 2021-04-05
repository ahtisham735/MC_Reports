package com.example.weatherforcast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Tempratue_Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempratue__detail);
        String temp=getIntent().getStringExtra("temp");
        TextView textView=findViewById(R.id.temp);
        textView.setText(temp);

    }
}