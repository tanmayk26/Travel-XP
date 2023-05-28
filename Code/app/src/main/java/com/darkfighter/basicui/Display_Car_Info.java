package com.darkfighter.basicui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Display_Car_Info extends AppCompatActivity {
    String location,car_no,car_no1,car_name,car_capacity,rate,availability;
    String json_string, user_name, flag_type;
    JSONObject jsonObject;
    JSONArray jsonArray;
    EditText ET_LOCATION,ET_CAR_NO,ET_CAR_NAME,ET_CAR_CAPACITY,ET_RATE;
    Switch S_AVAILABILITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__car__info);

        setTitle("Car Information");

        ET_LOCATION = (EditText)findViewById(R.id.editText_dlocation);
        ET_CAR_NO = (EditText)findViewById(R.id.editText_dcar_no);
        ET_CAR_NAME = (EditText)findViewById(R.id.editText_dcar_name);
        ET_CAR_CAPACITY = (EditText)findViewById(R.id.editText_dcar_capacity);
        ET_RATE = (EditText)findViewById(R.id.editText_drate);
        S_AVAILABILITY = (Switch)findViewById(R.id.switch_availability);

        json_string = getIntent().getExtras().getString("json_data");
        user_name = getIntent().getExtras().getString("a");
        flag_type = getIntent().getExtras().getString("flag_type");

        try {
            jsonObject = new JSONObject(json_string);
            int count=0;

            jsonArray = jsonObject.getJSONArray("server_response");
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                location = JO.getString("Location");
                car_no = JO.getString("Car_No");
                car_name = JO.getString("Car_Name");
                car_capacity = JO.getString("Car_Capacity");
                rate = JO.getString("Rate");
                availability = JO.getString("Availability");
                ET_LOCATION.setText(location);
                ET_CAR_NO.setText(car_no);
                ET_CAR_NAME.setText(car_name);
                ET_CAR_CAPACITY.setText(car_capacity);
                ET_RATE.setText(rate);
                if("1".equalsIgnoreCase(availability)) {
                    S_AVAILABILITY.setChecked(true);
                }
                else {
                    S_AVAILABILITY.setChecked(false);
                }
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onUpdate(View view) {
        if(haveNetworkConnection()==true) {
            location = ET_LOCATION.getText().toString();
            car_no1 = ET_CAR_NO.getText().toString();
            car_name = ET_CAR_NAME.getText().toString();
            car_capacity = ET_CAR_CAPACITY.getText().toString();
            rate = ET_RATE.getText().toString();
            if (S_AVAILABILITY.isChecked()) {
                availability = "1";
            } else {
                availability = "0";
            }
            String method = "updating_car";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, location, car_no, car_no1, car_name, car_capacity, rate, availability);
        }
        else {
            Toast.makeText(this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(haveNetworkConnection()==true) {
            String method = "owner_display";
            BackgroundTask1 backgroundTask1 = new BackgroundTask1(this);
            backgroundTask1.execute(method,user_name,flag_type);
        }
        else {
            Toast.makeText(this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
