package com.darkfighter.basicui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ET_USER_NAME,ET_USER_PASS;
    String login_name,login_pass;
    static String username,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET_USER_NAME = (EditText)findViewById(R.id.user_name);
        ET_USER_PASS = (EditText)findViewById(R.id.user_pass);
    }

    public void userReg(View view) {
        ET_USER_PASS.setText("");
        ET_USER_NAME.setText("");
        startActivity(new Intent(this,Register.class));
    }

    public void userLogin(View view) {
        login_name = ET_USER_NAME.getText().toString();
        login_pass = ET_USER_PASS.getText().toString();
        if(TextUtils.isEmpty(login_name)) {
            ET_USER_NAME.setError("Enter username");
        }
        else if(TextUtils.isEmpty(login_pass)) {
            ET_USER_PASS.setError("Enter password");
        }
        else {
            if(haveNetworkConnection()==true) {
                String method = "login";
                ET_USER_PASS.setText("");
                ET_USER_NAME.setText("");
                username = login_name;
                pass = login_pass;
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, login_name, login_pass);
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
