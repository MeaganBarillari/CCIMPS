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
import com.example.ccimp.ui.model.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SupplierRequestDetailActivity extends AppCompatActivity {

    TextView businessName, requestID, status, totalPrice;

    ListView listView;

    Button btnChangeStatus;
    Item item1 = new Item("123", "Beans", "300", "231", "3", "Black");
    Item[] values = new Item[]{item1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_request_detail);

        btnChangeStatus = findViewById(R.id.btnChangeStatus);
        businessName = findViewById(R.id.business_name);
        requestID = findViewById(R.id.request_number);
        status = findViewById(R.id.request_status);
        totalPrice = findViewById(R.id.totalPrice);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            businessName.setText(bundle.getString("businessName"));
            requestID.setText(bundle.getString("requestID"));
            status.setText(bundle.getString("status"));
            totalPrice.setText(bundle.getString("totalPrice"));
        }


        btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        listView = findViewById(R.id.requestiems);
        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        BottomNavigationView navigation = findViewById(R.id.supplierNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.supplier_navigation_home:
                        Intent c = new Intent(SupplierRequestDetailActivity.this,SupplierHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_supplier_inventory:
                        Intent d = new Intent(SupplierRequestDetailActivity.this,SupplierInventoryActivity.class);
                        startActivity(d);
                        break;
                    case R.id.navigation_supplier_profile:
                        Intent b = new Intent(SupplierRequestDetailActivity.this,SupplierProfileActivity.class);
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
            column1.setText(values[position].getName());
            column2.setText(values[position].getQuantity());
            column3.setText(values[position].getPrice());

            return view;
        }
    }
}
