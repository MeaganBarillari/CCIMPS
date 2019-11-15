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

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.business.BusinessOrderDetailInterface;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.order_info;
import com.example.ccimp.ui.presenter.business.BusinessOrderDetailAdapter;
import com.example.ccimp.ui.presenter.business.BusinessOrderDetailPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BusinessOrderDetailActivity extends AppCompatActivity implements BusinessOrderDetailInterface.BusinessOrderDetailView {

    Button btnChangeStatus;
    TextView customerName, totalPrice, orderID, orderStatus;
    BottomNavigationView navigation;
    ListView orderItemListView;
    Order tempOrder;
    User business;
    BusinessOrderDetailAdapter businessOrderDetailAdapter;
    BusinessOrderDetailInterface.BusinessOrderDetailPresenter businessOrderDetailPresenter;
    Item item1 = new Item("123", "coffee", "300", "231", "3", "no ice");

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

        // Gets order object and sets text view's based on request fields
        // returns the order object that we use to get the supplierID and get the orderItem Listview
        Intent intent = getIntent();
        tempOrder = getIntentData(intent);
        if(tempOrder != null){
            String businessID = tempOrder.getBusinessID();

            businessOrderDetailPresenter = new BusinessOrderDetailPresenter(this, businessID);
            businessOrderDetailPresenter.onViewCreate();

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
    }

    @Override
    public boolean callBusinessNavigation(MenuItem businessMenuItem) {
        switch (businessMenuItem.getItemId()) {
            case R.id.navigation_home:
                Intent c = new Intent(BusinessOrderDetailActivity.this, BusinessHomeActivity.class);
                c.putExtra("businessID", business.getEmail());
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
        Order order = intent.getParcelableExtra("Order");

        if (order != null){
            customerName.setText(order.getCustomerName());
            orderID.setText(order.getOrderID());
            orderStatus.setText(order.getStatus());
            totalPrice.setText(order.getTotalPrice());
            return order;
        }
        return null;
    }

    @Override
    public void setupOrderItemList(ArrayList<order_info> orderItemArrayList) {
        businessOrderDetailAdapter = new BusinessOrderDetailAdapter(this, R.layout.row, orderItemArrayList);
        orderItemListView.setAdapter(businessOrderDetailAdapter);
    }

    @Override
    public void setBusinessUser(User business) {
        this.business = business;
    }
}