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

public class RemoveCar extends AppCompatActivity {

    EditText ET_CAR_NO;
    String car_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_car);
        setTitle("Delete Car");

        ET_CAR_NO = (EditText)findViewById(R.id.editText_car_noR);
    }

    public void onRemoving(View view) {
        car_no = ET_CAR_NO.getText().toString();
        if(TextUtils.isEmpty(car_no)) {
            ET_CAR_NO.setError("Enter Car_No");
        }
        else {
            if(haveNetworkConnection()==true) {
                String method = "removing";
                ET_CAR_NO.setText("");

                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, car_no);
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
