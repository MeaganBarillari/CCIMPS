package com.example.ccimp.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.MainActivity;
import com.example.ccimp.ui.business.BusinessHomeActivity;
import com.example.ccimp.ui.business.BusinessInventoryActivity;
import com.example.ccimp.ui.business.BusinessProfileActivity;
import com.example.ccimp.ui.business.BusinessRequestsActivity;
import com.example.ccimp.ui.interfaces.business.BusinessProfileInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerProfileInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.customer.CustomerProfilePresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerProfileActivity extends AppCompatActivity implements CustomerProfileInterface.CustomerProfileView {

    TextView userName, userEmail, userMobile, userAddress;
    Button btnlogout;
    BottomNavigationView navigation;
    CustomerProfileInterface.CustomerProfilePresenter customerProfilePresenter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("customer");

        //customerProfilePresenter = new CustomerProfilePresenter(this);

        navigation = findViewById(R.id.customerNavigation);
        btnlogout = findViewById(R.id.btn_logout);
        userName = findViewById(R.id.user_profile_name);
        userEmail = findViewById(R.id.user_email);
        userMobile = findViewById(R.id.user_mobile);
        userAddress = findViewById(R.id.user_address);

        //customerProfilePresenter.onViewCreate();
        setupProfile();

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return callSupplierNavigation(item);
            }

        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerProfileActivity.this, MainActivity.class));
            }
        });


    }

    @Override
    public boolean callSupplierNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.navigation_home:
                Intent c = new Intent(CustomerProfileActivity.this, CustomerHomeActivity.class);
                c.putExtra("customer", user);
                startActivity(c);
                break;
            case R.id.navigation_customer_order:
                Intent a = new Intent(CustomerProfileActivity.this, CustomerOrdersActivity.class);
                a.putExtra("customer", user);
                startActivity(a);
                break;
            case R.id.navigation_customer_profile:
                break;
        }
        return false;
    }

    @Override
    public User getIntentData(Intent intent) {
        return null;
    }

    @Override
    public void setupProfile() {
        userName.setText(user.getUsername());
        userEmail.setText(user.getEmail());
        userMobile.setText(user.getPhone());
        userAddress.setText(user.getAddress());
    }

}
