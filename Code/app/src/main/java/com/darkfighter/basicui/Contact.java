package com.darkfighter.basicui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Contact extends AppCompatActivity {

    String name,phone,email,availability,car_no;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    TextView T_NAME,T_PHONE,T_EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setTitle("Contact");
        json_string = getIntent().getExtras().getString("json_data");
        availability = getIntent().getExtras().getString("availability");
        car_no = getIntent().getExtras().getString("car_no");

        T_NAME = (TextView)findViewById(R.id.textView_zname);
        T_PHONE = (TextView)findViewById(R.id.textView_zphone_no);
        T_EMAIL = (TextView)findViewById(R.id.textView_zemail_id);
        try {
            jsonObject = new JSONObject(json_string);
            int count=0;

            jsonArray = jsonObject.getJSONArray("server_response");
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("Name");
                phone = JO.getString("Phone_No");
                email = JO.getString("Email_ID");
                T_NAME.setText(name);
                T_PHONE.setText(phone);
                T_EMAIL.setText(email);

                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onBook(View view) {
        if("1".equalsIgnoreCase(availability)) {
            String method = "book_car";
            availability = "0";
            BackgroundTask5 backgroundTask5 = new BackgroundTask5(this);
            backgroundTask5.execute(method, car_no, availability);
            method = "login";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, MainActivity.username, MainActivity.pass);

            /*method = "cust_display";
            String loc = Customer.loc;
            String flag_type = "customer";
            BackgroundTask1 backgroundTask1 = new BackgroundTask1(this);
            backgroundTask1.execute(method, loc, flag_type);*/
            finish();
        }
        else {
            Toast.makeText(this,"The vehicle is not available right now. Try again later!",Toast.LENGTH_LONG).show();
        }
    }

}
