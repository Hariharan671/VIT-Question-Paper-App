package com.harryh5n1.vitqp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpenPage extends AppCompatActivity {

    private Button hp1,hp2,hp3,hp4,hp5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_page);

        hp1=(Button)findViewById(R.id.button2);
        hp2=(Button)findViewById(R.id.button3);
        hp3=(Button)findViewById(R.id.button4);
        hp4=(Button)findViewById(R.id.button6);
        hp5=(Button)findViewById(R.id.button7);



        hp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenPage.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        hp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenPage.this,Reference.class);
                startActivity(intent);
                finish();
            }
        });

        hp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenPage.this,Uploads.class);
                startActivity(intent);
                finish();
            }
        });

        hp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenPage.this,AboutUs.class);
                startActivity(intent);
                finish();
            }
        });

        hp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
                finish();
            }
        });


    }
}
