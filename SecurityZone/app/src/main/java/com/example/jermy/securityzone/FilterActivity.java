package com.example.jermy.securityzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static com.example.jermy.securityzone.R.id.chk1;
import static com.example.jermy.securityzone.R.id.chk2;
import static com.example.jermy.securityzone.R.id.chk3;
import static com.example.jermy.securityzone.R.id.chk4;
import static com.example.jermy.securityzone.R.id.chk5;
import static com.example.jermy.securityzone.R.id.chk6;
import static com.example.jermy.securityzone.R.id.chk7;
import static com.example.jermy.securityzone.R.id.chk8;
import static com.example.jermy.securityzone.R.id.chk9;

public class FilterActivity extends AppCompatActivity {
    RadioGroup rg1;
    RadioGroup rg2;
    RadioGroup rg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        init();
    }

    public void init() {
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        rg3 = (RadioGroup) findViewById(R.id.rg3);
    }

    public void filterbtn(View view) {
        RadioButton r1 = (RadioButton) findViewById(rg1.getCheckedRadioButtonId());
        RadioButton r2 = (RadioButton) findViewById(rg2.getCheckedRadioButtonId());
        RadioButton r3 = (RadioButton) findViewById(rg3.getCheckedRadioButtonId());
        int Awnings = 0;
        int Airinjector = 0;
        int fix = 0;
        switch (r1.getId()) {
            case chk1:
                Awnings = 1;
                break;
            case chk2:
                Awnings = 2;
                break;
            case chk3:
                Awnings = 3;
                break;
        }
        switch (r2.getId()) {
            case chk4:
                Airinjector = 1;
                break;
            case chk5:
                Airinjector = 2;
                break;
            case chk6:
                Airinjector = 3;
                break;
        }
        switch (r3.getId()) {
            case chk7:
                fix = 1;
                break;
            case chk8:
                fix = 2;
                break;
            case chk9:
                fix = 3;
                break;
        }
        Intent intent=new Intent(this,MapActivity.class);
        intent.putExtra("Awning",Awnings);
        intent.putExtra("Airinjector",Airinjector);
        intent.putExtra("Fix",fix);
        startActivity(intent);
    }
}

