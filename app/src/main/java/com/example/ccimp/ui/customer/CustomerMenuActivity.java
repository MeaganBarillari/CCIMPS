package com.example.ccimp.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.business.BusinessHomeActivity;
import com.example.ccimp.ui.business.BusinessInventoryActivity;
import com.example.ccimp.ui.business.BusinessProfileActivity;
import com.example.ccimp.ui.business.BusinessRequestsActivity;
import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.presenter.business.BusinessInventoryAdapter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuAdapter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CustomerMenuActivity extends AppCompatActivity implements CustomerMenuInterface.CustomerMenuView{

    Button btnCart;
    ListView listView;
    private User user = new User("123", "business", "business@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    BottomNavigationView navigation;
    private CustomerMenuAdapter customerMenuAdapter;
    private CustomerMenuInterface.CustomerMenuPresenter customerMenuPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);

        customerMenuPresenter = new CustomerMenuPresenter(this, user.getUserID());

        btnCart = findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerMenuActivity.this, CustomerOrderCartActivity.class));
            }
        });

        navigation = findViewById(R.id.customerNavigation);

        listView= findViewById(R.id.businessmenulist);
        customerMenuPresenter.onViewCreate();

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
                Intent c = new Intent(CustomerMenuActivity.this, CustomerHomeActivity.class);
                startActivity(c);
                break;
            case R.id.navigation_customer_order:
                Intent a = new Intent(CustomerMenuActivity.this, CustomerOrdersActivity.class);
                startActivity(a);
                break;
            case R.id.navigation_customer_profile:
                Intent b = new Intent(CustomerMenuActivity.this, CustomerProfileActivity.class);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public void setupInventoryList(ArrayList<inventory_business> inventoryArrayList) {
        customerMenuAdapter = new CustomerMenuAdapter(this, R.layout.rowoneline, inventoryArrayList);
        listView.setAdapter(customerMenuAdapter);
    }

}
