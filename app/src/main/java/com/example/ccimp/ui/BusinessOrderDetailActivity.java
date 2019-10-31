package com.example.ccimp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BusinessOrderDetailActivity extends AppCompatActivity {

    Button btnChangeStatus;
    TextView customerName, totalPrice, customerID, orderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_order_detail);

        btnChangeStatus = findViewById(R.id.btnChangeStatus);
        customerName = findViewById(R.id.customer_name);
        customerID = findViewById(R.id.customer_number);
        orderStatus = findViewById(R.id.request_status);
        totalPrice = findViewById(R.id.request_total_amount);

        btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            customerName.setText(bundle.getString("customerName"));
            totalPrice.setText(bundle.getString("totalPrice"));
            customerID.setText(bundle.getString("customerID"));
            orderStatus.setText(bundle.getString("orderStatus"));
        }

        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessOrderDetailActivity.this, BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessOrderDetailActivity.this,BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessOrderDetailActivity.this, BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessOrderDetailActivity.this, BusinessProfileActivity.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });
    }

}