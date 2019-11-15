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

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.business.BusinessHomeInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierHomeInterface;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessCurrentOrderAdapter;
import com.example.ccimp.ui.presenter.business.BusinessHomePresenter;
import com.example.ccimp.ui.presenter.supplier.SupplierCurrentRequestAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BusinessHomeActivity extends AppCompatActivity implements BusinessHomeInterface.BusinessHomeView {

    Button btnHistory;
    String businessEmail;
    BottomNavigationView navigation;
    private ListView orderListView;
    private BusinessCurrentOrderAdapter businessCurrentOrderAdapter;
    private BusinessHomeInterface.BusinessHomePresenter businessHomePresenter;
    Order order1 = new Order("William", "123456", "2019/11/1", "124578", "987654321", "Done", "600");
    private User business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);

        // Get User information from Intent
        Intent intent = getIntent();
        businessEmail = getIntentData(intent);

        businessHomePresenter = new BusinessHomePresenter(this, businessEmail);

        btnHistory = findViewById(R.id.btnHistory);
        navigation= findViewById(R.id.businessNavigation);
        orderListView = findViewById(R.id.orderlist);

        businessHomePresenter.onViewCreate();

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessHomeActivity.this, BusinessOrderHistoryActivity.class);
                intent.putExtra("BusinessID", business.getUserID());
                startActivity(intent);
            }
        });

        // Get order object and pass it to OrderDetailActivity
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order  order = (Order) parent.getItemAtPosition(position);
                Intent intent = new Intent(BusinessHomeActivity.this, BusinessOrderDetailActivity.class);
                intent.putExtra("Order", order);
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
                a.putExtra("businessID", business.getUserID());
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessHomeActivity.this, BusinessInventoryActivity.class);
                b.putExtra("businessID", business.getUserID());
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessHomeActivity.this, BusinessProfileActivity.class);
                //Could probably just get away with passing the whole business user object
                d.putExtra("businessID", business.getUserID());
                startActivity(d);
                break;
        }
        return false;
    }

    @Override
    public void setupOrderList(ArrayList<Order> orderArrayList) {
        businessCurrentOrderAdapter = new BusinessCurrentOrderAdapter(this, R.layout.row, orderArrayList);
        orderListView.setAdapter(businessCurrentOrderAdapter);
    }

    @Override
    public String getIntentData(Intent intent) {
        return intent.getStringExtra("userEmail");
    }

    @Override
    public void setBusinessUser(User business) {
        this.business = business;
    }
}
