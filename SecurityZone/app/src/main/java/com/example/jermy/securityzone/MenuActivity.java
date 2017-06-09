package com.example.jermy.securityzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }
    public void init(){
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        btn1.setBackgroundColor(getResources().getColor(R.color.btn1_after_touch));
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        btn1.setBackgroundColor(getResources().getColor(R.color.btn1_before_touch));
                        break;
                    }
                }
                return false;
            }
        });
        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        btn2.setBackgroundColor(getResources().getColor(R.color.btn2_after_touch));
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        btn2.setBackgroundColor(getResources().getColor(R.color.btn2_before_touch));
                        break;
                    }
                }
                return false;
            }
        });
    }

    public void btn1Click(View view) {
        Intent intent=new Intent(this,MapActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_in);
    }

    public void btn2Click(View view) {
    }

}