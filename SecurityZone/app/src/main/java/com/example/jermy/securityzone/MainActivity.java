package com.example.jermy.securityzone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView bicycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        bicycle=(ImageView)findViewById(R.id.bicycle);
        bicycle.setImageResource(R.drawable.img1);

    }
}
