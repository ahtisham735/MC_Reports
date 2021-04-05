package com.example.weatherforcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    Button btn;
    Intent intent;
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
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               EditText city=findViewById(R.id.city);
               EditText country=findViewById(R.id.country);
               requestQueue= Volley.newRequestQueue(getApplicationContext());
                apiRequest(v.getContext());
               v.getContext().startActivity(intent);
           }
       });
    }
    public void apiRequest(Context context)
    {
        String url="http://api.openweathermap.org/data/2.5/weather?q=lahore&APPID=e60925a24114cdb3a4445e4a38058829";
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("response","response called");
                    JSONObject temp=response.getJSONObject("main");
                    Log.d("temp",temp.getString("temp"));
                    intent=new Intent(context,Tempratue_Detail.class);
                    intent.putExtra("temp",temp.getString("temp"));



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



}