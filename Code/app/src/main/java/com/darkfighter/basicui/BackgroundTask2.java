package com.darkfighter.basicui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by hp-pc on 16-09-2016.
 */
class BackgroundTask2 extends AsyncTask<String, Void, String>
{
    String flag_type, a;
    String json_url;
    String JSON_STRING;
    ProgressDialog progressDialog;
    Context ctx;
    BackgroundTask2(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        json_url = "http://projtest.orgfree.com/webappdb/owner_display.php";
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        flag_type = params[2];
        a = params[3];
        if("disp_car_info".equalsIgnoreCase(method)) {
            try {
                String car_no = params[1];

                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8") + "&" +
                        URLEncoder.encode("car_no", "UTF-8") + "=" + URLEncoder.encode(car_no, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!=null) {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
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
        String json_string = result;
        if("owner".equalsIgnoreCase(flag_type)) {
            Intent intent = new Intent(ctx, Display_Car_Info.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("json_data", json_string);
            intent.putExtra("a",a);
            progressDialog.dismiss();
            ctx.startActivity(intent);
        }
        else if("customer".equalsIgnoreCase(flag_type)) {
            Intent intent = new Intent(ctx, CustomerDisplay.class);
            intent.putExtra("json_data", json_string);
            progressDialog.dismiss();
            ctx.startActivity(intent);
        }
    }
}