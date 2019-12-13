package com.example.ccimp.ui.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.customer.CustomerOrdersActivity;
import com.example.ccimp.ui.interfaces.business.BusinessHomeInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierHomeInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessCurrentOrderAdapter;
import com.example.ccimp.ui.presenter.business.BusinessHomePresenter;
import com.example.ccimp.ui.presenter.supplier.SupplierCurrentRequestAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusinessHomeActivity extends AppCompatActivity implements BusinessHomeInterface.BusinessHomeView {


    ArrayList<Order> orderList;
    Button btnHistory;
    BottomNavigationView navigation;
    private ListView orderListView;

    private User business;

    private Order[] values = new Order[10000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);

        // Get User information from Intent
        Intent intent = getIntent();
        business = intent.getParcelableExtra("business");

        btnHistory = findViewById(R.id.btnHistory);
        navigation= findViewById(R.id.businessNavigation);
        orderListView = findViewById(R.id.orderlist);
        orderList = new ArrayList<>();
        setupOrderList();

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessHomeActivity.this, BusinessOrderHistoryActivity.class);
                intent.putExtra("business", business);
                startActivity(intent);
            }
        });

        // Get order object and pass it to OrderDetailActivity
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BusinessHomeActivity.this, BusinessOrderDetailActivity.class);
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
                // They can't go to home, they are already on it
                // TODO: MAKE SOME SORT OF TOAST THAT STATES THIS OR MAKE THE COLOR ON THE SELECTED ITEM CHANGE
                break;
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessHomeActivity.this, BusinessRequestsActivity.class);
                a.putExtra("business", business);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessHomeActivity.this, BusinessInventoryActivity.class);
                b.putExtra("business", business);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessHomeActivity.this, BusinessProfileActivity.class);
                //Could probably just get away with passing the whole business user object
                d.putExtra("business", business);
                startActivity(d);
                break;
        }
        return false;
    }

    @Override
    public void setupOrderList() {
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
                                    if(! order.getStatus().equals("Complete")) {
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

    @Override
    public String getIntentData(Intent intent) {
        return null;
    }

    @Override
    public void setBusinessUser(User business){}
}
