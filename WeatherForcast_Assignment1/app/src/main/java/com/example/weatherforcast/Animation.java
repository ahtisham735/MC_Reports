package com.example.weatherforcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

public class Animation extends AppCompatActivity {

    TextView text;
    CharSequence charSequence;
    int index;
    long delay = 200;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        text=findViewById(R.id.message);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        textAnimation("Weather forecast app");
        

    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            text.setText(charSequence.subSequence(0,index++));
            if(index<=charSequence.length()){
                handler.postDelayed(runnable,delay);
                startActivity(new Intent(Animation.this, MainActivity.class));
            }

        }
    };

    public void textAnimation(CharSequence cs){
        charSequence=cs;
        index=0;
        text.setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,delay);
    }
}