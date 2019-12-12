package com.example.ccimp.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.presenter.customer.CustomerMenuAdapter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CustomerMenuActivity extends AppCompatActivity implements CustomerMenuInterface.CustomerMenuView{

    Button btnCart;
    ListView businessMenuListView;
    private String businessID;
    private User customer;
    ArrayList<inventory_business> selectedInventoryBusiness;
    BottomNavigationView navigation;
    private CustomerMenuAdapter customerMenuAdapter;
    private CustomerMenuInterface.CustomerMenuPresenter customerMenuPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);

        Intent intent = getIntent();
        businessID = getIntentBusinessID(intent);
        customer = getIntentCustomerObject(intent);
        if(businessID != null){
            customerMenuPresenter = new CustomerMenuPresenter(this, businessID);

            btnCart = findViewById(R.id.btnCart);
            navigation = findViewById(R.id.customerNavigation);
            businessMenuListView = findViewById(R.id.businessmenulist);

            customerMenuPresenter.onViewCreate();

            btnCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent cart = new Intent(CustomerMenuActivity.this, CustomerOrderCartActivity.class);
                    startActivity(cart);
                }
            });


            businessMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    inventory_business  item = (inventory_business) parent.getItemAtPosition(position);
                    addItemToCart(item);
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
    public boolean callCustomerNavigation(MenuItem customerMenuItem) {
        switch (customerMenuItem.getItemId()) {
            case R.id.navigation_home:
                Intent c = new Intent(CustomerMenuActivity.this, CustomerHomeActivity.class);
                c.putExtra("customer", customer);
                startActivity(c);
                break;
            case R.id.navigation_customer_order:
                Intent a = new Intent(CustomerMenuActivity.this, CustomerOrdersActivity.class);
                a.putExtra("customer", customer);
                startActivity(a);
                break;
            case R.id.navigation_customer_profile:
                Intent b = new Intent(CustomerMenuActivity.this, CustomerProfileActivity.class);
                b.putExtra("customer", customer);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public String getIntentBusinessID(Intent intent) {
        return intent.getStringExtra("businessID");
    }

    @Override
    public User getIntentCustomerObject(Intent intent) {
        User customer = intent.getParcelableExtra("customer");

        if (customer != null){
            return customer;
        }
        return null;
    }

    @Override
    public void setupInventoryList(ArrayList<inventory_business> inventoryArrayList) {
        customerMenuAdapter = new CustomerMenuAdapter(this, R.layout.rowoneline, inventoryArrayList);
        businessMenuListView.setAdapter(customerMenuAdapter);
    }

    public void addItemToCart(inventory_business item){
        selectedInventoryBusiness.add(item);
    }
}
