package com.example.weatherforcast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Intent intent;
    String cityName="";
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Celsius", "Kelvin", "Farenhite"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        btn=findViewById(R.id.button);
        requestQueue= Volley.newRequestQueue(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                EditText city=findViewById(R.id.city);
                cityName=city.getText().toString();
                String unit=String.valueOf(dropdown.getSelectedItem());
                String url=getUrl(city.getText().toString(),unit);
                apiRequest(v.getContext(),url);

            }
        });
    }
    public void apiRequest(Context context,String url)
    {
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject temp=response.getJSONObject("main");
                    JSONObject wind=response.getJSONObject("wind");
                    JSONObject sys=response.getJSONObject("sys");

                    intent=new Intent(context,Tempratue_Detail.class);
                    intent.putExtra("city",cityName);
                    intent.putExtra("temp",temp.getString("temp"));
                    intent.putExtra("pressure",temp.getString("pressure"));
                    intent.putExtra("humidity",temp.getString("humidity"));
                    intent.putExtra("visibility",response.getString("visibility"));
                    intent.putExtra("timezone",response.getString("timezone"));
                    intent.putExtra("speed",wind.getString("speed"));
                    intent.putExtra("sunrise",sys.getString("sunrise"));
                    intent.putExtra("sunset",sys.getString("sunset"));
                    context.startActivity(intent);



                } catch (JSONException e) {
                    Log.e("error",e.toString());

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("JsonError","Some error",error);
            }
        });

        requestQueue.add(objectRequest);

    }

    //utility functions
    public String getUrl(String city,String unit)
    {
        if(unit.equals("Celsius"))
            unit="metric";
        else if(unit.equals("Kelvin"))
            unit="standard";
        else
            unit="imperial";

        String url="http://api.openweathermap.org/data/2.5/weather?q="+city+"&units="+unit+"&APPID=e60925a24114cdb3a4445e4a38058829";
        return url;
    }
}


