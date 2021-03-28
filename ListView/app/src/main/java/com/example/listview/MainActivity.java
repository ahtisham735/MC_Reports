package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> nameList;
    ArrayAdapter<String> adapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameList=new ArrayList<String>();
        nameList.add("Ali");
        nameList.add("Ahmed");
        nameList.add("Raza");
        nameList.add("Babar");
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nameList);
        listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=nameList.get(position);
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

    }
   public void addName(View view)
   {
       EditText text =findViewById(R.id.txt);
       nameList.add(text.getText().toString());
       Collections.sort(nameList);
       adapter.notifyDataSetChanged();
   }

}