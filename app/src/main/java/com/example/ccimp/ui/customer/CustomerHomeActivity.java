package com.example.ccimp.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.customer.CustomerHomeInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.customer.CustomerHomeAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.ccimp.ui.presenter.customer.CustomerHomePresenter;

import java.util.ArrayList;

public class CustomerHomeActivity extends AppCompatActivity implements CustomerHomeInterface.CustomerHomeView {
    ListView businessListView;
    private User customer;
    private String customerEmail;
    BottomNavigationView navigation;
    private CustomerHomeAdapter customerHomeAdapter;
    private CustomerHomeInterface.CustomerHomePresenter customerHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        Intent intent = getIntent();
        customerEmail = getIntentData(intent);
        if(customerEmail!= null){
            customerHomePresenter = new CustomerHomePresenter(this, customerEmail);
            navigation = findViewById(R.id.customerNavigation);
            businessListView = findViewById(R.id.businesslist);
            customerHomePresenter.onViewCreate();

            businessListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    User business = (User) parent.getItemAtPosition(position);
                    Intent intent = new Intent(CustomerHomeActivity.this, CustomerMenuActivity.class);
                    intent.putExtra("businessID", business.getUserID());
                    intent.putExtra("customer", customer);
                    startActivity(intent);
                }
            });

            navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    return callCustomerNavigation(item);
                }

            });
        }
    }

    @Override
    public boolean callCustomerNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.navigation_home:
                break;
            case R.id.navigation_customer_order:
                Intent a = new Intent(CustomerHomeActivity.this, CustomerOrdersActivity.class);
                a.putExtra("customer", customer);
                startActivity(a);
                break;
            case R.id.navigation_customer_profile:
                Intent b = new Intent(CustomerHomeActivity.this, CustomerProfileActivity.class);
                b.putExtra("customer", customer);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public String getIntentData(Intent intent) {
        return intent.getStringExtra("userEmail");
    }

    @Override
    public void setCustomerUser(User customer) {
        this.customer = customer;
    }

    @Override
    public void setupBusinessList(ArrayList<User> userArrayList) {
        customerHomeAdapter = new CustomerHomeAdapter(this, R.layout.rowoneline, userArrayList);
        businessListView.setAdapter(customerHomeAdapter);
    }

}
