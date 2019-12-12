package com.example.ccimp.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerOrdersActivity extends AppCompatActivity {

    ListView currentOrdersListView, previousOrdersListView;
    User customer;
    Order[] values = new Order[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);

        Intent intent = getIntent();
        customer = intent.getParcelableExtra("customer");

        currentOrdersListView = findViewById(R.id.current_orders_listview);

        CustomAdapter customAdapter = new CustomAdapter();

        currentOrdersListView.setAdapter(customAdapter);

        previousOrdersListView = findViewById(R.id.previous_orders_listview);


        BottomNavigationView navigation = findViewById(R.id.customerNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(CustomerOrdersActivity.this, CustomerHomeActivity.class);
                        c.putExtra("customer", customer);
                        startActivity(c);
                        break;
                    case R.id.navigation_customer_order:
                        Intent a = new Intent(CustomerOrdersActivity.this,CustomerOrdersActivity.class);
                        a.putExtra("customer", customer);
                        startActivity(a);
                        break;
                    case R.id.navigation_customer_profile:
                        Intent b = new Intent(CustomerOrdersActivity.this, CustomerProfileActivity.class);
                        b.putExtra("customer", customer);
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
            column1.setText(values[position].getOrderID());
            column2.setText(values[position].getCreateDateTime());
            column3.setText(values[position].getStatus());

            return view;
        }
    }
}

