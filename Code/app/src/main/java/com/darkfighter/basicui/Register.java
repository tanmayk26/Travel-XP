package com.darkfighter.basicui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText ET_NAME,ET_USER_NAME,ET_USER_PASS,ET_PHONE,ET_EMAIL;
    private String name,user_name,user_pass,user_type,phone,email;
    private RadioButton RB_USER_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        setTitle("Register");
        ET_NAME = (EditText)findViewById(R.id.name);
        ET_USER_NAME = (EditText)findViewById(R.id.new_user_name);
        ET_USER_PASS = (EditText)findViewById(R.id.new_user_pass);
        ET_PHONE = (EditText)findViewById(R.id.editText_phone);
        ET_EMAIL = (EditText)findViewById(R.id.editText_email);
        RadioButton r = (RadioButton) findViewById(R.id.radio_cust);
        r.setChecked(true);
    }

    public void userReg(View view) {
        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_gr);
        int selectedId = rg.getCheckedRadioButtonId();
        RB_USER_TYPE = (RadioButton) findViewById(selectedId);
        name = ET_NAME.getText().toString();
        user_name = ET_USER_NAME.getText().toString();
        user_pass = ET_USER_PASS.getText().toString();
        user_type = RB_USER_TYPE.getText().toString();
        phone = ET_PHONE.getText().toString();
        email = ET_EMAIL.getText().toString();

        if(TextUtils.isEmpty(name)) {
            ET_NAME.setError("Enter Car_No");
        }
        else if (TextUtils.isEmpty(user_name)) {
            ET_USER_NAME.setError("Enter username");
        }
        else if (TextUtils.isEmpty(user_pass)) {
            ET_USER_PASS.setError("Enter password");
        }
        else if (TextUtils.isEmpty(phone)) {
            ET_PHONE.setError("Enter phone number");
        }
        else if (TextUtils.isEmpty(email)) {
            ET_EMAIL.setError("Enter email_id");
        }
        else {
            if(haveNetworkConnection()==true) {
                String method = "Register";
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, name, user_name, user_pass, user_type, phone, email);
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
