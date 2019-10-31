package com.example.ccimp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.Request;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SupplierHomeActivity extends AppCompatActivity {

    ListView listView;
    Request request1 = new Request("Starbucks", "123", "231", "345", "200", "2019/11/1", "2019/10/31", "Working");
    Request[] values = new Request[]{request1};

    Button btnseehistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_home);

        btnseehistory = findViewById(R.id.btnHistory);
        btnseehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SupplierHomeActivity.this, SupplierRequestsHistoryActivity.class));
            }
        });

        listView = findViewById(R.id.current_orders_listview);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SupplierHomeActivity.this, SupplierRequestDetailActivity.class);
                intent.putExtra("businessName", values[position].getBusinessName());
                intent.putExtra("requestID", values[position].getRequestID());
                intent.putExtra("businessID", values[position].getBusinessID());
                intent.putExtra("status", values[position].getStatus());
                intent.putExtra("totalPrice", values[position].getPrice());
                startActivity(intent);
            }
        });



        BottomNavigationView navigation = findViewById(R.id.supplierNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.supplier_navigation_home:
                        Intent c = new Intent(SupplierHomeActivity.this,SupplierHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_supplier_inventory:
                        Intent d = new Intent(SupplierHomeActivity.this,SupplierInventoryActivity.class);
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
            View view = getLayoutInflater().inflate(R.layout.row, null);
            TextView column1 = view.findViewById(R.id.column1);
            TextView column2 = view.findViewById(R.id.column2);
            TextView column3 = view.findViewById(R.id.column3);
            column1.setText(values[position].getBusinessName());
            column2.setText(values[position].getPrice());
            column3.setText(values[position].getStatus());

            return view;
        }
    }
}
