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

public class BackgroundTask extends AsyncTask<String,Void,String> {
    static String a;
    AlertDialog alertDialog;
    //ProgressDialog progressDialog;
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
        /*progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Loading");
        progressDialog.show();*/
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }
    @Override
    protected String doInBackground(String... params) {

        String reg_url = "http://projtest.orgfree.com/webappdb/register.php";
        String login_url = "http://projtest.orgfree.com/webappdb/login.php";
        String adding_url = "http://projtest.orgfree.com/webappdb/add_car.php";
        String booking_url = "http://projtest.orgfree.com/webappdb/book_car.php";
        String method = params[0];
        if ("register".equalsIgnoreCase(method)) {
            String name = params[1];
            String user_name = params[2];
            String user_pass = params[3];
            String user_type = params[4];
            String phone = params[5];
            String email = params[6];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                        URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8") + "&" +
                        URLEncoder.encode("user_type", "UTF-8") + "=" + URLEncoder.encode(user_type, "UTF-8") + "&" +
                        URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if("login".equalsIgnoreCase(method))
        {
            String login_name = params[1];
            a = login_name;
            String login_pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if("adding".equalsIgnoreCase(method)) {
            String location = params[1];
            String car_no = params[2];
            String car_name = params[3];
            String car_capacity = params[4];
            String rate = params[5];
            try {
                URL url = new URL(adding_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8") + "&" +
                        URLEncoder.encode("car_no", "UTF-8") + "=" + URLEncoder.encode(car_no, "UTF-8") + "&" +
                        URLEncoder.encode("car_name", "UTF-8") + "=" + URLEncoder.encode(car_name, "UTF-8") + "&" +
                        URLEncoder.encode("car_capacity", "UTF-8") + "=" + URLEncoder.encode(car_capacity, "UTF-8") + "&" +
                        URLEncoder.encode("rate", "UTF-8") + "=" + URLEncoder.encode(rate, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();
                return "Car Added Successfully...";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if("removing".equalsIgnoreCase(method)) {
            String car_no = params[1];
            try {
                URL url = new URL(adding_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("car_no", "UTF-8") + "=" + URLEncoder.encode(car_no, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();
                return "Car Removed Successfully...";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if("updating_car".equalsIgnoreCase(method)) {
            String location = params[1];
            String car_no = params[2];
            String car_no1 = params[3];
            String car_name = params[4];
            String car_capacity = params[5];
            String rate = params[6];
            String availability = params[7];
            try {
                URL url = new URL(adding_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8") + "&" +
                        URLEncoder.encode("car_no", "UTF-8") + "=" + URLEncoder.encode(car_no, "UTF-8") + "&" +
                        URLEncoder.encode("car_no1", "UTF-8") + "=" + URLEncoder.encode(car_no1, "UTF-8") + "&" +
                        URLEncoder.encode("car_name", "UTF-8") + "=" + URLEncoder.encode(car_name, "UTF-8") + "&" +
                        URLEncoder.encode("car_capacity", "UTF-8") + "=" + URLEncoder.encode(car_capacity, "UTF-8") + "&" +
                        URLEncoder.encode("rate", "UTF-8") + "=" + URLEncoder.encode(rate, "UTF-8") + "&" +
                        URLEncoder.encode("availability", "UTF-8") + "=" + URLEncoder.encode(availability, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();
                return "Information updated Successfully...";
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
        //progressDialog.dismiss();
        if("Registration Success...".equalsIgnoreCase(result)) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if("Username already exists".equalsIgnoreCase(result)) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if("customer".equalsIgnoreCase(result)) {
            String method = "cust_one";
            BackgroundTask3 backgroundTask3 = new BackgroundTask3(ctx);
            backgroundTask3.execute(method, a);
        }
        else if("owner".equalsIgnoreCase(result)) {
            Intent intent = new Intent(ctx, Owner.class);
            intent.putExtra("a",a);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ctx.startActivity(intent);
        }
        else if("Login Failed".equalsIgnoreCase((result))){
            alertDialog.setMessage(result);
            alertDialog.show();
        }
        else if("Car Added Successfully...".equalsIgnoreCase(result)) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if("Car Removed Successfully...".equalsIgnoreCase(result)) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if("Information updated Successfully...".equalsIgnoreCase(result)) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }
}