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
/*    FirebaseDatabase database;
    int cctvcount;
    private FirebaseAuth mAuth;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
//        initDB();
    }
    public void init(){
//        cctvcount=0;
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
/*        mAuth= FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword("jermy0131@naver.com","wpfmal1234");*/
    }
/*    public void initDB(){
        database = FirebaseDatabase.getInstance();
        String result = "";
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.store));
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            result += str;
        }
        parsingJson(result);
    }*/
/*    public void parsingJson(String result) {
        try {
            DatabaseReference table = database.getReference("SecurityZone/store");

            JSONObject json = new JSONObject(result);
            JSONArray array = json.getJSONArray("store");
            for (int i = 0; i < array.length(); i++) {
                DatabaseReference cctv=table.child("num"+cctvcount);
                String col1 = array.getJSONObject(i).getString("storeName");
                String col2 = array.getJSONObject(i).getString("Address1");
                String col3 = array.getJSONObject(i).getString("Address2");
                String col4 = array.getJSONObject(i).getString("latitude");
                String col5 = array.getJSONObject(i).getString("longitude");
                String col6 = array.getJSONObject(i).getString("capacity");
                String col7 = array.getJSONObject(i).getString("setYear");
                String col8 = array.getJSONObject(i).getString("setType");
                String col9 = array.getJSONObject(i).getString("Awnings");
                String col10 = array.getJSONObject(i).getString("Airinjector");
                String col11 = array.getJSONObject(i).getString("AirinjectorType");
                String col12 = array.getJSONObject(i).getString("fix");
                String col13 = array.getJSONObject(i).getString("managecontact");
                String col14 = array.getJSONObject(i).getString("managename");
                cctv.child("storeName").setValue(col1);
                cctv.child("Address1").setValue(col2);
                cctv.child("Address2").setValue(col3);
                cctv.child("latitude").setValue(col4);
                cctv.child("longitude").setValue(col5);
                cctv.child("capacity").setValue(col6);
                cctv.child("setYear").setValue(col7);
                cctv.child("setType").setValue(col8);
                cctv.child("Awnings").setValue(col9);
                cctv.child("Airinjector").setValue(col10);
                cctv.child("AirinjectorType").setValue(col11);
                cctv.child("fix").setValue(col12);
                cctv.child("managecontact").setValue(col13);
                cctv.child("managename").setValue(col14);
                cctvcount++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
    public void btn1Click(View view) {
        Intent intent=new Intent(this,MapActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_in);
    }

    public void btn2Click(View view) {
    }

}