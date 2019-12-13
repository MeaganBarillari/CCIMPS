package com.example.ccimp.ui.business;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.business.BusinessOrderDetailInterface;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.order_info;
import com.example.ccimp.ui.presenter.business.BusinessOrderDetailAdapter;
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
import java.util.ArrayList;

public class BusinessOrderDetailActivity extends AppCompatActivity implements BusinessOrderDetailInterface.BusinessOrderDetailView {

    ArrayList<order_info> itemList;
    Button btnChangeStatus;
    TextView customerName, totalPrice, orderID, orderStatus;
    BottomNavigationView navigation;
    ListView orderItemListView;
    User business;
    Spinner spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_order_detail);

        orderItemListView = findViewById(R.id.itemList);
        navigation= findViewById(R.id.businessNavigation);
        customerName = findViewById(R.id.customer_name);
        orderID = findViewById(R.id.customer_number);
        orderStatus = findViewById(R.id.request_status);
        totalPrice = findViewById(R.id.request_total_amount);
        itemList = new ArrayList<>();
        setupOrderItemList();

        String[] items = new String[] {"Decline", "Accept", "Complete", "Ready", "In Progress"};
        spinner = (Spinner) findViewById(R.id.changestatus);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setSelection(adapter.getPosition(orderStatus.getText().toString()), true);
        spinner.setAdapter(adapter);

        // Gets order object and sets text view's based on request fields
        // returns the order object that we use to get the supplierID and get the orderItem Listview
        Intent intent = getIntent();

        business = intent.getParcelableExtra("business");
        final Bundle bundle = getIntent().getExtras();
        customerName.setText(bundle.getString("customerName"));
        orderID.setText(bundle.getString("orderID"));
        orderStatus.setText(bundle.getString("status"));
        totalPrice.setText(bundle.getString("totalPrice"));


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return callBusinessNavigation(item);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int count = 0;
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(count >= 1){
                    orderStatus.setText(parentView.getItemAtPosition(position).toString());
                    UpdateStatus updateStatus = new UpdateStatus(BusinessOrderDetailActivity.this);

                    updateStatus.execute(orderID.getText().toString(), orderStatus.getText().toString());
                }
                count++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Override
    public boolean callBusinessNavigation(MenuItem businessMenuItem) {
        switch (businessMenuItem.getItemId()) {
            case R.id.navigation_home:
                Intent c = new Intent(BusinessOrderDetailActivity.this, BusinessHomeActivity.class);
                c.putExtra("business", business);
                startActivity(c);
                break;
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessOrderDetailActivity.this, BusinessRequestsActivity.class);
                a.putExtra("business", business);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessOrderDetailActivity.this, BusinessInventoryActivity.class);
                b.putExtra("business", business);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessOrderDetailActivity.this, BusinessProfileActivity.class);
                //Could probably just get away with passing the whole business user object
                d.putExtra("business", business);
                startActivity(d);
                break;
        }
        return false;
    }

    @Override
    public Order getIntentData(Intent intent) {
        return null;
    }

    @Override
    public void setupOrderItemList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getOrderInfo.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("order_info");
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);
                                order_info item = new order_info(business.getUsername(), orderObj.getString("OrderID"), orderObj.getString("itemName"), orderObj.getString("price"), orderObj.getString("quantity"));
                                if(item.getOrderID().equals(orderID.getText())){
                                    itemList.add(item);
                                }
                            }

                            BusinessOrderDetailAdapter adapter = new BusinessOrderDetailAdapter(itemList, getApplicationContext());
                            orderItemListView.setAdapter(adapter);
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
        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }

    private class UpdateStatus extends AsyncTask<String, Void, String> {
        Context context;

        UpdateStatus(Context ctx){
            this.context =ctx;
        }

        @Override
        protected String doInBackground(String... params){
            String update_url = "http://shifanzhou.com/updateStatus.php";
            try {
                String id = params[0];
                String status = params[1];
                URL url = new URL(update_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("orderID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&"
                        + URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");
                System.out.println(id);
                System.out.println(status);


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
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("Status updated")) {
                Toast.makeText(context, "Status updated", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void setBusinessUser(User business){
    }
}