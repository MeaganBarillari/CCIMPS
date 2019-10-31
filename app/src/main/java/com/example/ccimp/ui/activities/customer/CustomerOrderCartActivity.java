package com.example.ccimp.ui.activities.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustomerOrderCartActivity extends AppCompatActivity {

    FloatingActionButton btnCustomerorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_cart);

        btnCustomerorder = findViewById(R.id.fab_customer_order);

        btnCustomerorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        BottomNavigationView navigation = findViewById(R.id.customerNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(CustomerOrderCartActivity.this, CustomerHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_customer_order:
                        Intent a = new Intent(CustomerOrderCartActivity.this, CustomerOrdersActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_customer_profile:
                        Intent b = new Intent(CustomerOrderCartActivity.this, CustomerProfileActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }

}
