package com.example.ccimp.ui.activities.supplier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ccimp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SupplierRequestsHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_request_history);


        BottomNavigationView navigation = findViewById(R.id.supplierNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.supplier_navigation_home:
                        Intent c = new Intent(SupplierRequestsHistoryActivity.this, SupplierHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_supplier_inventory:
                        Intent d = new Intent(SupplierRequestsHistoryActivity.this, SupplierInventoryActivity.class);
                        startActivity(d);
                        break;
                    case R.id.navigation_supplier_profile:
                        Intent b = new Intent(SupplierRequestsHistoryActivity.this, SupplierProfileActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }
}
