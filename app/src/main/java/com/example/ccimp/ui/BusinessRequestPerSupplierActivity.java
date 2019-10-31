package com.example.ccimp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.inventory_supplier;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BusinessRequestPerSupplierActivity extends AppCompatActivity {
    Button btnBack, btnCart, btnFeedback;
    ListView listView;
    inventory_supplier inventory1 = new inventory_supplier("123", "321", "Beans", "100", "3");
    inventory_supplier[] values = new inventory_supplier[]{inventory1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_per_supplier);

        btnBack = findViewById(R.id.btBack);
        btnCart = findViewById(R.id.btCart);
        btnFeedback = findViewById(R.id.btFeedback);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessRequestPerSupplierActivity.this, BusinessSupplierListActivity.class));
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

        listView = findViewById(R.id.suppliermenu);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);


        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessRequestPerSupplierActivity.this,BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessRequestPerSupplierActivity.this,BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessRequestPerSupplierActivity.this,BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessRequestPerSupplierActivity.this,BusinessProfileActivity.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return values.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.rowoneline, null);
            TextView column1 = view.findViewById(R.id.column1);
            column1.setText(values[position].getItemName());

            return view;
        }
    }

}
