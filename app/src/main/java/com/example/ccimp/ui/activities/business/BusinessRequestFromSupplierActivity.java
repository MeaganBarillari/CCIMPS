package com.example.ccimp.ui.activities.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BusinessRequestFromSupplierActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_from_supplier);


        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessRequestFromSupplierActivity.this, BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessRequestFromSupplierActivity.this, BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessRequestFromSupplierActivity.this, BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessRequestFromSupplierActivity.this, BusinessProfileActivity.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });
    }

}
