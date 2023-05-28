package com.darkfighter.basicui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCar extends AppCompatActivity {

    EditText ET_LOCATION,ET_CAR_NO,ET_CAR_NAME,ET_CAR_CAPACITY,ET_RATE;
    String location,car_no,car_name,car_capacity,rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        setTitle("Add Car");

        ET_LOCATION = (EditText)findViewById(R.id.editText_alocation);
        ET_CAR_NO = (EditText)findViewById(R.id.editText_acar_no);
        ET_CAR_NAME = (EditText)findViewById(R.id.editText_acar_name);
        ET_CAR_CAPACITY = (EditText)findViewById(R.id.editText_acar_capacity);
        ET_RATE = (EditText)findViewById(R.id.editText_arate);

    }

    public void onAdding(View view) {
        location = ET_LOCATION.getText().toString();
        car_no = ET_CAR_NO.getText().toString();
        car_name = ET_CAR_NAME.getText().toString();
        car_capacity = ET_CAR_CAPACITY.getText().toString();
        rate = ET_RATE.getText().toString();
        if(TextUtils.isEmpty(location)) {
            ET_LOCATION.setError("Enter Location");
        }
        else if(TextUtils.isEmpty(car_no)) {
            ET_CAR_NO.setError("Enter Car_No");
        }
        else if (TextUtils.isEmpty(car_name)) {
            ET_CAR_NAME.setError("Enter Car_Name");
        }
        else if (TextUtils.isEmpty(car_capacity)) {
            ET_CAR_CAPACITY.setError("Enter Car_Capacity");
        }
        else if (TextUtils.isEmpty(rate)) {
            ET_RATE.setError("Enter Rate");
        }
        else {
            if(haveNetworkConnection()==true) {
                String method = "adding";
                ET_LOCATION.setText("");
                ET_CAR_NO.setText("");
                ET_CAR_NAME.setText("");
                ET_CAR_CAPACITY.setText("");
                ET_RATE.setText("");

                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, location, car_no, car_name, car_capacity, rate);
                finish();
            }
            else {
                Toast.makeText(this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
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
