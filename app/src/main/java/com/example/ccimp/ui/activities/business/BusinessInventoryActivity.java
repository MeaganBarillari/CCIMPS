package com.example.ccimp.ui.activities.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BusinessInventoryActivity extends AppCompatActivity {


    ListView listView;
    Button btnRequestItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_inventory);

        btnRequestItems = findViewById(R.id.requestItem);

        btnRequestItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessInventoryActivity.this, BusinessSupplierListActivity.class));
            }
        });

        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessInventoryActivity.this, BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessInventoryActivity.this, BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessInventoryActivity.this,BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessInventoryActivity.this, BusinessProfileActivity.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });
    }

}
