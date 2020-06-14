package com.harryh5n1.vitqp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Reference extends AppCompatActivity {
    private Button search,getreference;
    String coursecode;
    static TextView course_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        search = (Button) findViewById(R.id.search);
        getreference = (Button) findViewById(R.id.getreference);
        course_code=(TextView)findViewById(R.id.course_code);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name =course_code.getText().toString();
                if(name.isEmpty()) {
                    course_code.setError("Course Code Required");
                    course_code.requestFocus();
                    return;
                }
                coursecode="rm"+course_code.getText().toString();
                Intent intent = new Intent(Reference.this, refdownload.class);
                intent.putExtra("coursecode",coursecode);
                startActivity(intent);
                finish();
            }
        });
        getreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coursecode = course_code.getText().toString();
                Intent intent = new Intent(Reference.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}