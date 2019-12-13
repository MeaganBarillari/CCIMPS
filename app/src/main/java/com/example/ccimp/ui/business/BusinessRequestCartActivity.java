package com.example.ccimp.ui.business;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.ccimp.ui.customer.CustomerHomeActivity;
import com.example.ccimp.ui.customer.CustomerOrderCartActivity;
import com.example.ccimp.ui.customer.CustomerOrdersActivity;
import com.example.ccimp.ui.customer.CustomerProfileActivity;
import com.example.ccimp.ui.interfaces.business.BusinessCartInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.model.request_info;
import com.example.ccimp.ui.presenter.business.BusinessCartAdapter;

import com.example.ccimp.ui.presenter.customer.CustomerCartAdapter;
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
import java.util.Date;
import java.util.Locale;

public class BusinessRequestCartActivity extends AppCompatActivity {
    String requestID;
    ListView listView;
    ArrayList<inventory_supplier> local;
    User business;
    Button btnBusinessRequest;
    TextView supplierName, totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_request_cart);

        local = new ArrayList<>();

        String value = getIntent().getStringExtra("supplierName");
        final String tPrice = getIntent().getStringExtra("price");

        Intent i = getIntent();
        Bundle args = getIntent().getBundleExtra("BUNDLE");
        final ArrayList<inventory_supplier> object = (ArrayList<inventory_supplier>) args.getSerializable("ARRAYLIST");
        local.addAll(object);


        Intent intent = getIntent();
        business = intent.getParcelableExtra("business");

        btnBusinessRequest = findViewById(R.id.fab_customer_order);
        supplierName = findViewById(R.id.BusinessName);
        totalPrice = findViewById(R.id.order_price);
        final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        supplierName.setText(value);
        totalPrice.setText(tPrice);
        final String supplierID = intent.getStringExtra("supplierID");

        btnBusinessRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundProcess bgProcess = new BackgroundProcess(BusinessRequestCartActivity.this);
                bgProcess.execute("order",currentDate, supplierID, business.getUserID(), business.getUsername(), "Accept", tPrice);

            }
        });
        listView = findViewById(R.id.business_checkout_listview);
        final BusinessCartAdapter adapter = new BusinessCartAdapter(local, getApplicationContext());
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
        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessRequestCartActivity.this, BusinessHomeActivity.class);
                        c.putExtra("business", business);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessRequestCartActivity.this,BusinessRequestsActivity.class);
                        a.putExtra("business", business);

                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessRequestCartActivity.this, BusinessInventoryActivity.class);
                        b.putExtra("business", business);
                        startActivity(b);
                        break;

                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessRequestCartActivity.this, BusinessProfileActivity.class);
                        d.putExtra("business", business);
                        startActivity(d);
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
        String supplierID ;
        String businessID ;
        String businessName ;
        String status;
        String price;
        BackgroundProcess(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String addItem_url = "http://shifanzhou.com/sendBusinessRequest.php";
            String addIno_url = "http://shifanzhou.com/sendBusinessRequestInfo.php";
            String type = params[0];
            if(type == "order") {
                try {
                    currentDate = params[1];
                    supplierID = params[2];
                    businessID = params[3];
                    businessName = params[4];
                    status = params[5];
                    price = params[6];

                    URL url = new URL(addItem_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("currentDate", "UTF-8") + "=" + URLEncoder.encode(currentDate, "UTF-8") + "&"
                            + URLEncoder.encode("supplierID", "UTF-8") + "=" + URLEncoder.encode(supplierID, "UTF-8") + "&"
                            + URLEncoder.encode("businessID", "UTF-8") + "=" + URLEncoder.encode(businessID, "UTF-8") + "&"
                            + URLEncoder.encode("businessName", "UTF-8") + "=" + URLEncoder.encode(businessName, "UTF-8")+ "&"
                            + URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8")+ "&"
                            + URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8")
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
                            + URLEncoder.encode("requestID", "UTF-8") + "=" + URLEncoder.encode(requestID, "UTF-8")
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
            alertDialog.setTitle("Request status");
        }
        @Override
        protected void onPostExecute(String result) {
            if(result.equals("Request info waiting")){
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getBusinessRequests.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject obj = new JSONObject(response);
                                    JSONArray array = obj.getJSONArray("businessRequest");
                                    for(int i = 0; i< array.length();i++) {
                                        JSONObject orderObj = array.getJSONObject(i);
                                        BusinessRequest order = new BusinessRequest(orderObj.getString("businessName"), orderObj.getString("requestID"),
                                                orderObj.getString("supplierID"),orderObj.getString("businessID"), orderObj.getString("price"),
                                                orderObj.getString("needByDate"), orderObj.getString("requestDate"),
                                                orderObj.getString("status"));

                                        if(order.getRequestDate().equals(currentDate) &&
                                                order.getBusinessID().equals(businessID) &&
                                                order.getSupplierID().equals(supplierID) &&
                                                order.getStatus().equals(status) &&
                                                order.getPrice().equals(totalPrice)){
                                            requestID = order.getRequestID();
                                            for(int j = 0; j < local.size(); j++) {
                                                System.out.println(local.size());
                                                BackgroundProcess backgroundProcess = new BackgroundProcess(BusinessRequestCartActivity.this);
                                                backgroundProcess.execute("orderInfo", requestID, local.get(j).getItemName(), local.get(j).getPrice(), "1");

                                            }

                                            Toast.makeText(context, "Order received", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(BusinessRequestCartActivity.this, BusinessHomeActivity.class);
                                            intent.putExtra("business", business);
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
}