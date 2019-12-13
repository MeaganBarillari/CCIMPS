package com.example.ccimp.ui.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
import com.example.ccimp.ui.interfaces.business.BusinessOrderDetailInterface;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.order_info;
import com.example.ccimp.ui.model.request_info;
import com.example.ccimp.ui.presenter.business.BusinessOrderDetailAdapter;
import com.example.ccimp.ui.presenter.business.BusinessOrderDetailPresenter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestDetailAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusinessOrderDetailActivity extends AppCompatActivity implements BusinessOrderDetailInterface.BusinessOrderDetailView {

    ArrayList<order_info> itemList;
    Button btnChangeStatus;
    TextView customerName, totalPrice, orderID, orderStatus;
    BottomNavigationView navigation;
    ListView orderItemListView;
    User business;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_order_detail);

        orderItemListView = findViewById(R.id.itemList);
        navigation= findViewById(R.id.businessNavigation);
        btnChangeStatus = findViewById(R.id.btnChangeStatus);
        customerName = findViewById(R.id.customer_name);
        orderID = findViewById(R.id.customer_number);
        orderStatus = findViewById(R.id.request_status);
        totalPrice = findViewById(R.id.request_total_amount);
        itemList = new ArrayList<>();
        setupOrderItemList();

        // Gets order object and sets text view's based on request fields
        // returns the order object that we use to get the supplierID and get the orderItem Listview
        Intent intent = getIntent();
        business = intent.getParcelableExtra("business");
        Bundle bundle = getIntent().getExtras();
        customerName.setText(bundle.getString("customerName"));
        orderID.setText(bundle.getString("orderID"));
        orderStatus.setText(bundle.getString("status"));
        totalPrice.setText(bundle.getString("totalPrice"));

        btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    @Override
    public void setBusinessUser(User business){
    }
}