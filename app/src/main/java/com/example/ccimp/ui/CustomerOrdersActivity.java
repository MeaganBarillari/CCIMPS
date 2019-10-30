package com.example.ccimp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);


        BottomNavigationView navigation = findViewById(R.id.customerNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(CustomerOrdersActivity.this,CustomerHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_customer_order:
                        Intent a = new Intent(CustomerOrdersActivity.this,CustomerOrdersActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_customer_profile:
                        Intent b = new Intent(CustomerOrdersActivity.this,CustomerProfileActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }

}

