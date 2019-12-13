package com.example.ccimp.ui.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.ui.RegisterActivity;
import com.example.ccimp.ui.business.BusinessHomeActivity;
import com.example.ccimp.ui.customer.CustomerHomeActivity;
import com.example.ccimp.ui.LoginActivity;
import com.example.ccimp.ui.supplier.SupplierHomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class backgroundWorker extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context context;
    String userLoginType;
    String userEmail;
    public backgroundWorker(Context ctx) {
     context = ctx;
    }
    @Override
    public String doInBackground(String... params) {
        String type = params[0];

        String login_url = "http://shifanzhou.com/login.php";
        String register_url = "http://shifanzhou.com/register.php";
        if(type.equals("login")) {
            try {
                String user_email = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_email","UTF-8") + "=" + URLEncoder.encode(user_email,"UTF-8") + "&"
                        + URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result ="";
                String line="";
                while((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                if(result.equals("login success")){
                    userEmail = user_email;
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("register")) {
            try {
                String username = params[1];
                String user_email = params[2];
                String address = params[3];
                String phone = params[4];
                String password = params[5];
                String userType = params[6];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_email","UTF-8") + "=" + URLEncoder.encode(user_email,"UTF-8") + "&"
                        + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("user_password","UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                        + URLEncoder.encode("user_type","UTF-8") + "=" + URLEncoder.encode(userType, "UTF-8") + "&"
                        + URLEncoder.encode("user_phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&"
                        + URLEncoder.encode("user_address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result ="";
                String line="";
                while((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
       alertDialog = new AlertDialog.Builder(context).create();
       alertDialog.setTitle("Login status");
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("login success")){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getUser.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject obj = new JSONObject(response);
                                JSONArray array = obj.getJSONArray("user");
                                for(int i = 0; i< array.length();i++) {
                                    JSONObject orderObj = array.getJSONObject(i);
                                    User user = new User(orderObj.getString("userId"), orderObj.getString("username"),
                                            orderObj.getString("email"), orderObj.getString("password"),
                                            orderObj.getString("type"), orderObj.getString("phone"),
                                            orderObj.getString("address"));

                                    if (user.getEmail().equals(userEmail)) {
                                        if(user.getType().equals("Customer")){
                                           Intent intent = new Intent(context, CustomerHomeActivity.class);
                                           intent.putExtra("customer", user);
                                           context.startActivity(intent);
                                           ((LoginActivity)context).finish();
                                        }else if(user.getType().equals("Business")){
                                            Intent intent = new Intent(context, BusinessHomeActivity.class);
                                            intent.putExtra("business", user);
                                            context.startActivity(intent);
                                            ((LoginActivity)context).finish();
                                        }else if(user.getType().equals("Supplier")){
                                            Intent intent = new Intent(context, SupplierHomeActivity.class);
                                            intent.putExtra("supplier", user);
                                            context.startActivity(intent);
                                            ((LoginActivity)context).finish();
                                        }

                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){

            };
            Handler.getInstance(context).addToRequestQue(stringRequest);
        }
        else if(result.equals("Register success")){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            ((RegisterActivity)context).finish();
        }

        else if(result.equals("login fail")){
            Toast.makeText(context, "Invalid username or password", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
