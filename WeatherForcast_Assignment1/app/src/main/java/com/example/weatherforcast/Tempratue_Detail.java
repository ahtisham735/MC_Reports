package com.example.weatherforcast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class Tempratue_Detail extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempratue__detail);
        TextView cityName=findViewById(R.id.cityName);
        cityName.setText("Weather Details of "+getIntent().getStringExtra("city"));

        //setting current date in textview
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(date);
        TextView dateDisplay=findViewById(R.id.dateDisplay);
        dateDisplay.setText(formattedDate);

        String temp=getIntent().getStringExtra("temp");
        TextView textView=findViewById(R.id.temp);
        TextView pressure=findViewById(R.id.pressure);

        TextView humidity=findViewById(R.id.humidity);
        TextView visibility=findViewById(R.id.visibility);
        TextView timezone=findViewById(R.id.time);
        TextView speed=findViewById(R.id.wind);
        TextView sunrise=findViewById(R.id.sunrise);
        TextView sunset=findViewById(R.id.sunset);
        textView.setText(temp);
        pressure.setText(getIntent().getStringExtra("pressure"));
        humidity.setText(getIntent().getStringExtra("humidity"));
        visibility.setText(getIntent().getStringExtra("visibility"));
        timezone.setText(getIntent().getStringExtra("timezone"));
        speed.setText(getIntent().getStringExtra("speed"));
        sunrise.setText(getIntent().getStringExtra("sunrise"));
        sunset.setText(getIntent().getStringExtra("sunset"));
    }
}