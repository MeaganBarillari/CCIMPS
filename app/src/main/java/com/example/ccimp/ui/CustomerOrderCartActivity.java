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
import com.example.ccimp.ui.model.order_info;
import com.example.ccimp.ui.model.request_info;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustomerOrderCartActivity extends AppCompatActivity {

    ListView listView;
    Button btnCustomerorder;
    TextView businessName, totalPrice;
    order_info request1 = new order_info("Starbucks","123", "321", "3", "263");
    Item item1 = new Item("123", "Apple", "200", "231", "3", "Fresh");
    Item[] values = new Item[]{item1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_cart);

        btnCustomerorder = findViewById(R.id.fab_customer_order);
        businessName = findViewById(R.id.BusinessName);
        totalPrice = findViewById(R.id.order_pric);

        businessName.setText(request1.getBusinessName());
        totalPrice.setText(request1.getTotalPrice());

        btnCustomerorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerOrderCartActivity.this, CustomerOrdersActivity.class));
            }
        });

        listView = findViewById(R.id.customer_checkout_listview);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        BottomNavigationView navigation = findViewById(R.id.customerNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(CustomerOrderCartActivity.this,CustomerHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_customer_order:
                        Intent a = new Intent(CustomerOrderCartActivity.this,CustomerOrdersActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_customer_profile:
                        Intent b = new Intent(CustomerOrderCartActivity.this,CustomerProfileActivity.class);
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
            View view = getLayoutInflater().inflate(R.layout.rowoneline, null);
            TextView column1 = view.findViewById(R.id.column1);
            column1.setText(values[position].getName());

            return view;
        }
    }

}
