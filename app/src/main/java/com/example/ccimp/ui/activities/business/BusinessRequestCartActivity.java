package com.example.ccimp.ui.activities.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BusinessRequestCartActivity extends AppCompatActivity {

    FloatingActionButton btnsendrequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_request_cart);

        btnsendrequest = findViewById(R.id.fab_customer_order);

        btnsendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessRequestCartActivity.this, BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessRequestCartActivity.this, BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessRequestCartActivity.this, BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessRequestCartActivity.this, BusinessProfileActivity.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });
    }

}