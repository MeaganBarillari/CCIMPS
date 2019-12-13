package com.example.ccimp.ui.business;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.business.BusinessOrderHistoryInterface;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessCurrentOrderAdapter;
import com.example.ccimp.ui.presenter.business.BusinessOrderHistoryAdapter;
import com.example.ccimp.ui.presenter.business.BusinessOrderHistoryPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusinessOrderHistoryActivity extends AppCompatActivity implements BusinessOrderHistoryInterface.BusinessOrderHistoryView {

    ArrayList<Order> orderList;
    private ListView orderListView;
    private User business;
    private Order[] values = new Order[10000];
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_order_history);

        // Get business object from
        Intent intent = getIntent();
        business = intent.getParcelableExtra("business");
        orderListView = findViewById(R.id.pastorders);
        navigation = findViewById(R.id.businessNavigation);
        orderList = new ArrayList<>();
        setupOrderHistoryList();

        // Pass to presenter the userID so we can properly populate the history list from the business
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BusinessOrderHistoryActivity.this, BusinessOrderDetailActivity.class);
                intent.putExtra("orderID", values[position].getOrderID());
                intent.putExtra("customerName", values[position].getCustomerName());
                intent.putExtra("businessID", values[position].getBusinessID());
                intent.putExtra("userID", values[position].getUserID());
                intent.putExtra("createDateTime", values[position].getCreateDateTime());
                intent.putExtra("status", values[position].getStatus());
                intent.putExtra("totalPrice", values[position].getTotalPrice());
                intent.putExtra("business", business);
                startActivity(intent);
            }
        });

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return callBusinessNavigation(item);
            }
        });

    }

    @Override
    public boolean callBusinessNavigation(MenuItem businessMenuItem) {
        switch (businessMenuItem.getItemId()) {
            case R.id.navigation_home:
                Intent c = new Intent(BusinessOrderHistoryActivity.this, BusinessRequestsActivity.class);
                c.putExtra("business", business);
                startActivity(c);
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessOrderHistoryActivity.this, BusinessRequestsActivity.class);
                a.putExtra("business", business);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessOrderHistoryActivity.this, BusinessInventoryActivity.class);
                b.putExtra("business", business);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessOrderHistoryActivity.this, BusinessProfileActivity.class);
                d.putExtra("business", business);
                startActivity(d);
                break;
        }
        return false;
    }

    @Override
    public User getIntentData(Intent intent) {
        return null;
    }

    @Override
    public void setupOrderHistoryList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getCustomerOrder.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("customerOrder");
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);
                                Order order = new Order(orderObj.getString("username"),orderObj.getString("orderID"),  orderObj.getString("createDateTime"), orderObj.getString("businessID"), orderObj.getString("userID"), orderObj.getString("status"), orderObj.getString("totalPrice"));
                                if(order.getBusinessID().equals(business.getUserID())){
                                    if(order.getStatus().equals("Complete")) {
                                        orderList.add(order);

                                        for(int j = 0; j < values.length;j++){
                                            if(values[j] == null) {
                                                values[j] = order;
                                                break;
                                            }
                                        }

                                    }
                                }
                            }

                            BusinessCurrentOrderAdapter adapter = new BusinessCurrentOrderAdapter(orderList, getApplicationContext());
                            orderListView.setAdapter(adapter);

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
}

