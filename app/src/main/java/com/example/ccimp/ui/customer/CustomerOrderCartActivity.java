package com.example.ccimp.ui.customer;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.ccimp.R;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.order_info;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.presenter.customer.CustomerCartAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
public class CustomerOrderCartActivity extends AppCompatActivity {

    ListView listView;
    String businessNametext;
    Button btnCustomerorder;
    TextView businessName, totalPrice;
    order_info request1 = new order_info("Starbucks","123", "321", "3", "263");
    Item item1 = new Item("123", "Apple", "200", "231", "3", "Fresh");
    Item[] values = new Item[]{item1};
    Order order = new Order("Will", "123123", "2019/12/12", "123", "abc", "ready", "267");
    ArrayList<inventory_business> local = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_cart);

        String value = getIntent().getStringExtra("businessname");
        final String tPrice = getIntent().getStringExtra("price");

        Intent i = getIntent();
        Bundle args = getIntent().getBundleExtra("BUNDLE");
        final ArrayList<inventory_business> object = (ArrayList<inventory_business>) args.getSerializable("ARRAYLIST");
        local.addAll(object);

        btnCustomerorder = findViewById(R.id.fab_customer_order);
        businessName = findViewById(R.id.BusinessName);
        totalPrice = findViewById(R.id.order_pric);

        businessName.setText(value);
        totalPrice.setText(tPrice);

        btnCustomerorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerOrderCartActivity.this, CustomerOrdersActivity.class));
            }
        });



        listView = findViewById(R.id.customer_checkout_listview);






        final CustomerCartAdapter adapter = new CustomerCartAdapter(local, getApplicationContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int tota = Integer.valueOf(tPrice);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                local.remove(position);
                if (local.size() != 0){
                    tota -= Integer.valueOf(local.get(position).getPrice());
                }
                else{
                    tota = 0;
                }

                totalPrice.setText(Integer.toString(tota));
                listView.setAdapter(adapter);

            }
        });



        BottomNavigationView navigation = findViewById(R.id.customerNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(CustomerOrderCartActivity.this, CustomerHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_customer_order:
                        Intent a = new Intent(CustomerOrderCartActivity.this,CustomerOrdersActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_customer_profile:
                        Intent b = new Intent(CustomerOrderCartActivity.this, CustomerProfileActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }



}
