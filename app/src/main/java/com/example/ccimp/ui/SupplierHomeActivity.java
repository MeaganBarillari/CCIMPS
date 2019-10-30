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

public class SupplierHomeActivity extends AppCompatActivity {

    Button btnadditem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_home);

        btnadditem = findViewById(R.id.btnadditem);

        btnadditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        BottomNavigationView navigation = findViewById(R.id.supplierNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.supplier_navigation_home:
                        Intent c = new Intent(SupplierHomeActivity.this,SupplierRequestsActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_supplier_history:
                        Intent a = new Intent(SupplierHomeActivity.this,SupplierRequestsHistoryActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_supplier_inventory:
                        Intent d = new Intent(SupplierHomeActivity.this,SupplierHomeActivity.class);
                        startActivity(d);
                        break;
                    case R.id.navigation_supplier_profile:
                        Intent b = new Intent(SupplierHomeActivity.this,SupplierProfileActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }

}
