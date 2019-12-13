package com.example.ccimp.ui.business;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.business.BusinessOrderHistoryInterface;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessOrderHistoryAdapter;
import com.example.ccimp.ui.presenter.business.BusinessOrderHistoryPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BusinessOrderHistoryActivity extends AppCompatActivity implements BusinessOrderHistoryInterface.BusinessOrderHistoryView {

    private ListView orderListView;
    private User business;
    private BusinessOrderHistoryAdapter businessOrderHistoryAdapter;
    private BusinessOrderHistoryInterface.BusinessOrderHistoryPresenter businessOrderHistoryPresenter;
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_order_history);

        // Get business object from
        Intent intent = getIntent();
        business = getIntentData(intent);
        orderListView = findViewById(R.id.pastorders);
        navigation = findViewById(R.id.businessNavigation);

        // Pass to presenter the userID so we can properly populate the history list from the business
        businessOrderHistoryPresenter = new BusinessOrderHistoryPresenter(this, business);

        businessOrderHistoryPresenter.onViewCreate();

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
        User business = intent.getParcelableExtra("business");

        if (business != null){
            return business;
        }
        return null;
    }

    @Override
    public void setupOrderHistoryList(ArrayList<Order> orderArrayList) {
        businessOrderHistoryAdapter = new BusinessOrderHistoryAdapter(this, R.layout.row, orderArrayList);
        orderListView.setAdapter(businessOrderHistoryAdapter);
    }
}

