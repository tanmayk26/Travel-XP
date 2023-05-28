package com.darkfighter.basicui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask5 extends AsyncTask<String,Void,String> {

    Context ctx;
    BackgroundTask5(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
    }
    @Override
    protected String doInBackground(String... params) {
        String booking_url = "http://projtest.orgfree.com/webappdb/book_car.php";
        String method = params[0];
        if("book_car".equalsIgnoreCase(method)) {
            String car_no = params[1];
            String availability = params[2];
            try {
                URL url = new URL(booking_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8") + "&" +
                        URLEncoder.encode("car_no", "UTF-8") + "=" + URLEncoder.encode(car_no, "UTF-8") + "&" +
                        URLEncoder.encode("availability", "UTF-8") + "=" + URLEncoder.encode(availability, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();
                return "Car booked Successfully...";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if("Car booked successfully...".equalsIgnoreCase(result)) {
            Toast.makeText(ctx, result+" Contact the owner!", Toast.LENGTH_SHORT).show();
        }
    }
}