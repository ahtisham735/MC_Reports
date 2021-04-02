package com.example.makenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {
    private EditText editText;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText=findViewById(R.id.editText);
        String content=getIntent().getStringExtra("content");
        id=getIntent().getIntExtra("id",0);
        editText.setText(content);
        FloatingActionButton delete_btn=findViewById(R.id.floating_btn_delete);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.noteDAO().delete(id);
                Intent intent=new Intent(v.getContext(),MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.database.noteDAO().save(editText.getText().toString(),id);
    }
}