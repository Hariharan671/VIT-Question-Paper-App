package com.harryh5n1.vitqp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button search, getreference;
    String coursecode;
    ImageButton flora;
    static TextView course_code;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    float x1, x2, y2, y1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (Button) findViewById(R.id.search);
        getreference = (Button) findViewById(R.id.getreference);
        course_code = (TextView) findViewById(R.id.course_code);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name =course_code.getText().toString();
                if(name.isEmpty()) {
                    course_code.setError("Course Code Required");
                    course_code.requestFocus();
                    return;
                }

                coursecode = "qp"+course_code.getText().toString();
                Intent intent = new Intent(MainActivity.this, Downloads.class);
                intent.putExtra("coursecode", coursecode);
                startActivity(intent);
                finish();

            }
        });

        getreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Reference.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
        /*gestureDetector = new GestureDetector(new SwipeGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };

    }
    @Override
    public void onClick(View v) {

    }

    private void onLeftSwipe() {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }

    private void onRightSwipe() {
    }

    private class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_MIN_DISTANCE = 50;
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            try {
                float diffAbs = Math.abs(e1.getY() - e2.getY());
                float diff = e1.getX() - e2.getX();

                if (diffAbs > SWIPE_MAX_OFF_PATH)
                    return false;

                // Left swipe
                if (diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    MainActivity.this.onLeftSwipe();
                }
                // Right swipe
                else if (-diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    MainActivity.this.onRightSwipe();
                }
            } catch (Exception e) {
                Log.e("Home", "Error on gestures");
            }
            return false;
        }

    }
}*/

   /* public boolean OnTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = motionEvent.getX();
                y1 = motionEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = motionEvent.getX();
                y2 = motionEvent.getY();
                if (x1 < x2) {
                    Intent intent = new Intent(MainActivity.this, Reference.class);
                    startActivity(intent);
                }
                break;
        }
        return false;
        }
*/

