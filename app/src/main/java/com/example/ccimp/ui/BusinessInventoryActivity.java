package com.example.ccimp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.inventory_business;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BusinessInventoryActivity extends AppCompatActivity {


    ListView listView;
    inventory_business inventory1 = new inventory_business("Bread", "231", "312", "30", "20", "600");
    inventory_business[] values = new inventory_business[]{inventory1};

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

        listView = findViewById(R.id.listitem);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessInventoryActivity.this,BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessInventoryActivity.this,BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessInventoryActivity.this,BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessInventoryActivity.this,BusinessProfileActivity.class);
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
            View view = getLayoutInflater().inflate(R.layout.rowfourlines, null);
            TextView column1 = view.findViewById(R.id.column1);
            TextView column2 = view.findViewById(R.id.column2);
            TextView column3 = view.findViewById(R.id.column3);
            TextView column4 = view.findViewById(R.id.column4);
            column1.setText(values[position].getItemName());
            column2.setText(values[position].getPrice());
            column3.setText(values[position].getQuantity());
            column4.setText(values[position].getAvailQuantity());

            return view;
        }
    }

}
