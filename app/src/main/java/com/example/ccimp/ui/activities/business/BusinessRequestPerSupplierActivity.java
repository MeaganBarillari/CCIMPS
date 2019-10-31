package com.example.ccimp.ui.activities.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BusinessRequestPerSupplierActivity extends AppCompatActivity {
    Button btnBack, btnCart, btnFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_per_supplier);

        btnBack.findViewById(R.id.btBack);
        btnCart.findViewById(R.id.btCart);
        btnFeedback.findViewById(R.id.btFeedback);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessRequestPerSupplierActivity.this, BusinessRequestFromSupplierActivity.class));
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessRequestPerSupplierActivity.this, BusinessRequestCartActivity.class));
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
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
                        Intent c = new Intent(BusinessRequestPerSupplierActivity.this, BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessRequestPerSupplierActivity.this, BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessRequestPerSupplierActivity.this, BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessRequestPerSupplierActivity.this, BusinessProfileActivity.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });
    }

}
