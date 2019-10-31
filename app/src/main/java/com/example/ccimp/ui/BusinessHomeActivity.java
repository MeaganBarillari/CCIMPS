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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BusinessHomeActivity extends AppCompatActivity {

    Button btnHistory;
    ListView listView;
    TextView tvsecond;
    RelativeLayout layout1;
    Order order1 = new Order("William", "December", "September", "Done", 1, "200", "123456");
    Order order2 = new Order("Shifan", "2019/11/1", "2019/11/1", "Working", 1, "312", "123");
    Order order3 = new Order("Meagan", "2019/11/12", "2019/01/01", "Cooking", 1, "200", "123456");
    Order order4 = new Order("Brandon", "2019/12/1", "September", "Cancel", 1, "200", "123456");
    Order order5 = new Order("Nikolaj", "2019/10/31", "September", "Waiting", 1, "200", "123456");
    Order order6 = new Order("Lucille", "2019/01/13", "September", "Start", 1, "200", "123456");
    Order[] values = new Order[]{order1, order2, order3, order4, order5, order6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);

        btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessHomeActivity.this, BusinessOrderHistoryActivity.class));
            }
        });

        listView = findViewById(R.id.orderlist);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BusinessHomeActivity.this, BusinessOrderDetailActivity.class);
                intent.putExtra("customerName", values[position].getCustomerName());
                intent.putExtra("orderDate", values[position].getOrderDate());
                intent.putExtra("needbyDate", values[position].getNeedByDate());
                intent.putExtra("orderStatus", values[position].getOrderStatus());
                intent.putExtra("orderID", values[position].getOrderID());
                intent.putExtra("totalPrice", values[position].getTotalPrice());
                intent.putExtra("customerID", values[position].getCustomerID());
                startActivity(intent);
            }
        });


        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessHomeActivity.this,BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessHomeActivity.this,BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessHomeActivity.this,BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessHomeActivity.this,BusinessProfileActivity.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });
    }

    class CustomAdapter extends BaseAdapter{

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
            column1.setText(values[position].getCustomerName());
            column2.setText(values[position].getOrderDate());
            column3.setText(values[position].getOrderStatus());

            return view;
        }
    }

}
