package com.darkfighter.basicui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string, a;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CarAdapter carAdapter;
    ListView listView;
    String flag_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        setTitle("Available Cars");

        listView = (ListView)findViewById(R.id.listview);
        carAdapter = new CarAdapter(this,R.layout.row_layout);
        listView.setAdapter(carAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        flag_type = getIntent().getExtras().getString("flag_type");
        a = getIntent().getExtras().getString("a");

        try {
            jsonObject = new JSONObject(json_string);
            int count=0;
            String car_no,car_name;
            jsonArray = jsonObject.getJSONArray("server_response");
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                car_no = JO.getString("Car_No");
                car_name = JO.getString("Car_Name");
                Cars car = new Cars(car_no,car_name);
                carAdapter.add(car);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(haveNetworkConnection()==true) {
                    Cars car = (Cars) adapterView.getItemAtPosition(position);
                    String car_no = car.getCar_no();
                    String method = "disp_car_info";
                    BackgroundTask2 backgroundTask2 = new BackgroundTask2(DisplayListView.this);
                    backgroundTask2.execute(method, car_no, flag_type, a);
                }
                else {
                    Toast.makeText(DisplayListView.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
