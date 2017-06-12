package com.example.jermy.securityzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageView explan;

    FrameLayout framelayout;
/*
    FirebaseDatabase database;
    int cctvcount;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
//        initDB();
    }
    public void init(){
//        cctvcount=0;
        framelayout=(FrameLayout)findViewById(R.id.id);
        imgBtn();
        explan = (ImageView)findViewById(R.id.explan);

    }

    private void imgBtn() {
        btn1=(ImageButton)findViewById(R.id.btn1);
        btn2=(ImageButton)findViewById(R.id.btn2);
        btn3=(ImageButton)findViewById(R.id.btn3);
        btn4=(ImageButton)findViewById(R.id.btn4);
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
        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        btn3.setBackgroundColor(getResources().getColor(R.color.btn2_after_touch));
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        btn3.setBackgroundColor(getResources().getColor(R.color.btn2_before_touch));
                        break;
                    }
                }
                return false;
            }
        });
        btn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        btn4.setBackgroundColor(getResources().getColor(R.color.btn2_after_touch));
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        btn4.setBackgroundColor(getResources().getColor(R.color.btn2_before_touch));
                        break;
                    }
                }
                return false;
            }
        });
    }

/*    public void initDB() {
        double[] cctvlat=new double[872];
        double[] cctvlng=new double[872];
        double[] storelat=new double[872];
        double[] storelng=new double[298];
        int[] storecctv=new int[298];
        database = FirebaseDatabase.getInstance();
        String result = "";
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.store));
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            result += str;
        }
        String result2 ="";
        Scanner scanner2 = new Scanner(getResources().openRawResource(cctv));
        while(scanner2.hasNextLine()){
            String str=scanner2.nextLine();
            result2+=str;
        }
        try {
            JSONObject json = new JSONObject(result);
            JSONArray array = json.getJSONArray("store");
            for (int i = 0; i < array.length(); i++) {
                storelat[i] = Double.parseDouble(array.getJSONObject(i).getString("latitude"));
                storelng[i] = Double.parseDouble(array.getJSONObject(i).getString("longitude"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject json2 = new JSONObject(result2);
            JSONArray array2 = json2.getJSONArray("cctv");
            for (int i = 0; i < array2.length(); i++) {
                cctvlat[i] = Double.parseDouble(array2.getJSONObject(i).getString("latitude"));
                cctvlng[i] = Double.parseDouble(array2.getJSONObject(i).getString("longitude"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i<872;i++){
            for(int j=0;j<298;j++){
                if(cctvlat[i]-storelat[j]<=0.00174&&cctvlat[i]-storelat[j]>=-0.00174
                        &&cctvlng[i]-storelng[j]<=0.0022&&cctvlng[i]-storelng[j]>=-0.0022){
                    storecctv[j]++;
                }
            }
        }
        DatabaseReference table=database.getReference("SecurityZone/store");
        int num=0;
        for(num=0;num<298;num++) {
            DatabaseReference temp = table.child("num"+num);
            temp.child("nearcctv").setValue(storecctv[num]);
        }
    }*/
    public void btn1Click(View view) {
        Intent intent=new Intent(this,MapActivity.class);
        int Awning=0;
        int Airinjector=0;
        int Fix=0;
        intent.putExtra("Awning",Awning);
        intent.putExtra("Airinjector",Airinjector);
        intent.putExtra("Fix",Fix);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_in);
    }

    public void btn2Click(View view) {
        Intent intent=new Intent(this,FilterActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_in);
    }

    public void btn3Click(View view){
        framelayout.setVisibility(View.VISIBLE);
        explan.setImageResource(R.drawable.explan1);
    }
    public void btn4Click(View view) {

    }

    public void imgclicked(View view) {
        framelayout.setVisibility(View.GONE);
    }
}