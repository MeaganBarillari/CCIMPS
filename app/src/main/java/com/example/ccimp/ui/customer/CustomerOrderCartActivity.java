package com.example.ccimp.ui.customer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.LoginActivity;
import com.example.ccimp.ui.business.BusinessHomeActivity;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.order_info;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.presenter.customer.CustomerCartAdapter;
import com.example.ccimp.ui.supplier.SupplierAddToInventory;
import com.example.ccimp.ui.supplier.SupplierHomeActivity;
import com.example.ccimp.ui.supplier.SupplierInventoryActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class CustomerOrderCartActivity extends AppCompatActivity {
    String orderID;
    User customer;
    ListView listView;
    Button btnCustomerorder;
    TextView businessName, totalPrice;
    ArrayList<inventory_business> local;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_cart);
        local = new ArrayList<>();

        String value = getIntent().getStringExtra("businessname");
        final String tPrice = getIntent().getStringExtra("price");

        Intent i = getIntent();
        Bundle args = getIntent().getBundleExtra("BUNDLE");
        final ArrayList<inventory_business> object = (ArrayList<inventory_business>) args.getSerializable("ARRAYLIST");
        local.addAll(object);


        Intent intent = getIntent();
        customer = intent.getParcelableExtra("customer");

        btnCustomerorder = findViewById(R.id.fab_customer_order);
        businessName = findViewById(R.id.BusinessName);
        totalPrice = findViewById(R.id.order_pric);
        final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        businessName.setText(value);
        totalPrice.setText(tPrice);
        final String businessID = intent.getStringExtra("businessID");

        btnCustomerorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundProcess bgProcess = new BackgroundProcess(CustomerOrderCartActivity.this);
                bgProcess.execute("order",currentDate, businessID, customer.getUserID(), customer.getUsername(), "Accept", tPrice);

            }
        });
        listView = findViewById(R.id.customer_checkout_listview);
        final CustomerCartAdapter adapter = new CustomerCartAdapter(local, getApplicationContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int tota = Integer.valueOf(tPrice);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (local.size() != 0){
                    tota -= Integer.valueOf(local.get(position).getPrice());
                }
                else{
                    tota = 0;
                }
                local.remove(position);
                totalPrice.setText(Integer.toString(tota));
                listView.setAdapter(adapter);

            }
        });
        BottomNavigationView navigation = findViewById(R.id.customerNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(CustomerOrderCartActivity.this, CustomerHomeActivity.class);
                        c.putExtra("customer", customer);
                        startActivity(c);
                        break;
                    case R.id.navigation_customer_order:
                        Intent a = new Intent(CustomerOrderCartActivity.this,CustomerOrdersActivity.class);
                        a.putExtra("customer", customer);
                        startActivity(a);
                        break;
                    case R.id.navigation_customer_profile:
                        Intent b = new Intent(CustomerOrderCartActivity.this, CustomerProfileActivity.class);
                        b.putExtra("customer", customer);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }

    private class BackgroundProcess extends AsyncTask<String, Void, String> {
        Context context;
        AlertDialog alertDialog;
        String currentDate ;
        String businessID ;
        String userID ;
        String username ;
        String status;
        String totalPrice;
        BackgroundProcess(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String addItem_url = "http://shifanzhou.com/sendCustomerOrder.php";
            String addIno_url = "http://shifanzhou.com/sendCustomerOrderInfo.php";
            String type = params[0];
            if(type == "order") {
                try {
                   currentDate = params[1];
                    businessID = params[2];
                   userID = params[3];
                   username = params[4];
                   status = params[5];
                   totalPrice = params[6];

                    URL url = new URL(addItem_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("currentDate", "UTF-8") + "=" + URLEncoder.encode(currentDate, "UTF-8") + "&"
                            + URLEncoder.encode("businessID", "UTF-8") + "=" + URLEncoder.encode(businessID, "UTF-8") + "&"
                            + URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(userID, "UTF-8") + "&"
                            + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")+ "&"
                            + URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8")+ "&"
                            + URLEncoder.encode("totalPrice", "UTF-8") + "=" + URLEncoder.encode(totalPrice, "UTF-8")
                            ;
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
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
            }else if(type == "orderInfo"){
                try {
                    String itemName = params[2];
                    String price = params[3];
                    String quantity = params[4];


                    URL url = new URL(addIno_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8") + "&"
                            + URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8") + "&"
                            + URLEncoder.encode("quantity", "UTF-8") + "=" + URLEncoder.encode(quantity, "UTF-8") + "&"
                            + URLEncoder.encode("orderID", "UTF-8") + "=" + URLEncoder.encode(orderID, "UTF-8")
                            ;
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
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
        protected void onPreExecute(){
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Order status");
        }
        @Override
        protected void onPostExecute(String result) {
            if(result.equals("Order info waiting")){
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getCustomerOrder.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject obj = new JSONObject(response);
                                    JSONArray array = obj.getJSONArray("customerOrder");
                                    for(int i = 0; i< array.length();i++) {
                                        JSONObject orderObj = array.getJSONObject(i);
                                        Order order = new Order(orderObj.getString("username"), orderObj.getString("orderID"),
                                                orderObj.getString("createDateTime"), orderObj.getString("businessID"),
                                                orderObj.getString("userID"), orderObj.getString("status"),
                                                orderObj.getString("totalPrice"));

                                        if(order.getCreateDateTime().equals(currentDate) &&
                                                order.getBusinessID().equals(businessID) &&
                                                order.getUserID().equals(userID) &&
                                                order.getStatus().equals(status) &&
                                                order.getTotalPrice().equals(totalPrice)){
                                            orderID = order.getOrderID();
                                            for(int j = 0; j < local.size(); j++) {
                                                System.out.println(local.size());
                                                BackgroundProcess backgroundProcess = new BackgroundProcess(CustomerOrderCartActivity.this);
                                                backgroundProcess.execute("orderInfo", orderID, local.get(j).getItemName(), local.get(j).getPrice(), "1");

                                            }

                                            Toast.makeText(context, "Order received", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(CustomerOrderCartActivity.this, CustomerHomeActivity.class);
                                            intent.putExtra("customer", customer);
                                            startActivity(intent);

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
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

    private void getOrderID(){

    }



}
