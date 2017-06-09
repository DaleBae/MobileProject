package com.example.jermy.securityzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        bicycle.setImageResource(R.drawable.bicycle);



    }

    public void imgClick(View view) {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_in);
    }
}
