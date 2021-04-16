package com.example.dialogbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=findViewById(R.id.dialog);
        Button multiple=findViewById(R.id.mutlipleBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Title");
                builder.setMessage("Message");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "OK clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Cancel Cliked", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });

        multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String []color={"red","green","white"};
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Color").setItems(color, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, color[which], Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        Button list=findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String []color={"Red","Blue","White"};

                ArrayList<Integer> list=new ArrayList();
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Multiple Colors").setMultiChoiceItems(color, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked)
                            list.add(which);
                        else if(list.contains(which)){
                            list.remove(Integer.valueOf(which));
                        }
                    }
                }).setCancelable(false)
                  .setPositiveButton("Select", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          String selectedColors="";
                          for(int i=0;i<list.size();i++)
                          {
                              selectedColors=selectedColors+"\n"+(i+1)+":"+color[list.get(i)];
                          }
                          Toast.makeText(MainActivity.this, selectedColors, Toast.LENGTH_LONG).show();
                      }
                  }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }


        });

        Button signin=findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater=getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.signin,null))
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Sign in Logic", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                    }
                }).setCancelable(false);

                AlertDialog alert=builder.create();
                alert.show();

            }
        });




    }
}