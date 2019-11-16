package com.example.ccimp.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.customer.CustomerHomeInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.presenter.customer.CustomerHomeAdapter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.ccimp.ui.presenter.customer.CustomerHomePresenter;

import java.util.ArrayList;

public class CustomerHomeActivity extends AppCompatActivity implements CustomerHomeInterface.CustomerHomeView {
    ListView listView;
    private User user = new User("123", "business", "business@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    BottomNavigationView navigation;
    private CustomerHomeAdapter customerHomeAdapter;
    private CustomerHomeInterface.CustomerHomePresenter customerHomePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        customerHomePresenter = new CustomerHomePresenter(this, user.getUserID());

        navigation = findViewById(R.id.customerNavigation);






        listView = findViewById(R.id.businesslist);

        customerHomePresenter.onViewCreate();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomerHomeActivity.this, CustomerMenuActivity.class);
                startActivity(intent);
            }
        });

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return callSupplierNavigation(item);
            }

        });
    }

    @Override
    public boolean callSupplierNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.navigation_home:
                Intent c = new Intent(CustomerHomeActivity.this,CustomerHomeActivity.class);
                startActivity(c);
                break;
            case R.id.navigation_customer_order:
                Intent a = new Intent(CustomerHomeActivity.this, CustomerOrdersActivity.class);
                startActivity(a);
                break;
            case R.id.navigation_customer_profile:
                Intent b = new Intent(CustomerHomeActivity.this, CustomerProfileActivity.class);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public void setupBusinessList(ArrayList<User> userArrayList) {
        customerHomeAdapter = new CustomerHomeAdapter(this, R.layout.rowoneline, userArrayList);
        listView.setAdapter(customerHomeAdapter);
    }

}
