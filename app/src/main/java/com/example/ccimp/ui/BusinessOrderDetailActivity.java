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

public class BusinessOrderDetailActivity extends AppCompatActivity {

    Button btnChangeStatus;
    TextView customerName, totalPrice, customerID, orderStatus;
    ListView listView;
    Item item1 = new Item("123", "coffee", "300", "231", "3", "no ice");
    Item[] values = new Item[]{item1};

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

        listView = findViewById(R.id.itemList);
        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            customerName.setText(bundle.getString("customerName"));
            totalPrice.setText(bundle.getString("totalPrice"));
            customerID.setText(bundle.getString("userID"));
            orderStatus.setText(bundle.getString("status"));
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