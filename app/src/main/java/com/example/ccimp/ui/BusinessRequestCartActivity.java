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
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.request_info;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BusinessRequestCartActivity extends AppCompatActivity {

    ListView listView;
    TextView orderPrice;
    request_info request1 = new request_info("123", "321", "3", "1000");
    Item item1 = new Item("123", "Beans", "300", "231", "3", "Fresh");
    Item[] values = new Item[]{item1};

    Button btnsendrequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_request_cart);


        orderPrice = findViewById(R.id.order_price);
        orderPrice.setText(request1.getTotalPrice());
        btnsendrequest = findViewById(R.id.fab_customer_order);

        btnsendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessRequestCartActivity.this, BusinessRequestsActivity.class));
            }
        });

        listView = findViewById(R.id.business_checkout_listview);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);


        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessRequestCartActivity.this,BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessRequestCartActivity.this,BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessRequestCartActivity.this,BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessRequestCartActivity.this,BusinessProfileActivity.class);
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
            column1.setText(values[position].getName());
            column2.setText(values[position].getSupplierID());
            column3.setText(values[position].getQuantity());
            column4.setText(values[position].getPrice());

            return view;
        }
    }

}