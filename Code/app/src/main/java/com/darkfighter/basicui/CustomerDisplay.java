package com.darkfighter.basicui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomerDisplay extends AppCompatActivity {

    String car_no,car_name,car_capacity,rate,availability;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    TextView T_CAR_NO,T_CAR_NAME,T_CAR_CAPACITY,T_RATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_display);
        setTitle("Car Information");

        T_CAR_NO = (TextView)findViewById(R.id.textView_ccar_no);
        T_CAR_NAME = (TextView)findViewById(R.id.textView_ccar_name);
        T_CAR_CAPACITY = (TextView)findViewById(R.id.textView_ccar_capacity);
        T_RATE = (TextView)findViewById(R.id.textView_crate);

        json_string = getIntent().getExtras().getString("json_data");

        try {
            jsonObject = new JSONObject(json_string);
            int count=0;

            jsonArray = jsonObject.getJSONArray("server_response");
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                car_no = JO.getString("Car_No");
                car_name = JO.getString("Car_Name");
                car_capacity = JO.getString("Car_Capacity");
                rate = JO.getString("Rate");
                availability = JO.getString("Availability");
                T_CAR_NO.setText(car_no);
                T_CAR_NAME.setText(car_name);
                T_CAR_CAPACITY.setText(car_capacity);
                T_RATE.setText(rate);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onContact(View view) {
        if(haveNetworkConnection()==true) {
            String method = "contact";
            BackgroundTask4 backgroundTask4 = new BackgroundTask4(this);
            backgroundTask4.execute(method, car_no, availability);
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
