package com.example.ccimp.ui.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.supplier.SupplierCurrentRequestAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerOrdersActivity extends AppCompatActivity {

    ArrayList<Order> currentOrderList;
    ArrayList<Order> pastOrderList;
    ListView currentOrdersListView, previousOrdersListView;
    User customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);

        Intent intent = getIntent();
        customer = intent.getParcelableExtra("customer");

        currentOrdersListView = findViewById(R.id.current_orders_listview);

        previousOrdersListView = findViewById(R.id.previous_orders_listview);

        currentOrderList = new ArrayList<>();
        pastOrderList = new ArrayList<>();
        showOrderList();

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
//                        Intent a = new Intent(CustomerOrdersActivity.this,CustomerOrdersActivity.class);
//                        a.putExtra("customer", customer);
//                        startActivity(a);
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

    class CustomerCurrentOrdersAdapter extends ArrayAdapter<Order> {
        private ArrayList<Order> orderArrayList;
        private Context ctx;
        public CustomerCurrentOrdersAdapter(ArrayList<Order> orderArrayList, Context context){
            super(context, R.layout.activity_customer_orders, orderArrayList);
            this.orderArrayList = orderArrayList;
            this.ctx = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View v = view;
            if(v == null) {
                v = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
            }
            Order order = orderArrayList.get(position);
            TextView column1 = v.findViewById(R.id.column1);
            TextView column2 = v.findViewById(R.id.column2);
            TextView column3 = v.findViewById(R.id.column3);
            column1.setText(order.getOrderID());
            column2.setText(order.getCreateDateTime());
            column3.setText(order.getStatus());

            return v;
        }
    }

    class CustomerPastOrdersAdapter extends ArrayAdapter<Order> {
        private ArrayList<Order> orderArrayList;
        private Context ctx;
        public CustomerPastOrdersAdapter(ArrayList<Order> orderArrayList, Context context){
            super(context, R.layout.activity_customer_orders, orderArrayList);
            this.orderArrayList = orderArrayList;
            this.ctx = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View v = view;
            if(v == null) {
                v = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
            }

            Order order = orderArrayList.get(position);
            TextView column1 = v.findViewById(R.id.column1);
            TextView column2 = v.findViewById(R.id.column2);
            TextView column3 = v.findViewById(R.id.column3);
            column1.setText(order.getOrderID());
            column2.setText(order.getCreateDateTime());
            column3.setText(order.getStatus());

            return v;
        }
    }

    private void showOrderList(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getCustomerOrder.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("customerOrder");
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);
                                Order order = new Order(customer.getUsername(),orderObj.getString("orderID"),  orderObj.getString("createDateTime"), orderObj.getString("businessID"), orderObj.getString("userID"), orderObj.getString("status"), orderObj.getString("totalPrice"));
                                if(order.getUserID().equals(customer.getUserID())){
                                    if(! order.getStatus().equals("Complete")) {
                                        System.out.println(order.getStatus());
                                        currentOrderList.add(order);

                                    }else{
                                        //System.out.println(order.getStatus());

                                        pastOrderList.add(order);
                                    }

                                }


                            }

                            CustomerCurrentOrdersAdapter adapter = new CustomerCurrentOrdersAdapter(currentOrderList, getApplicationContext());
                            currentOrdersListView.setAdapter(adapter);

                            CustomerPastOrdersAdapter b = new CustomerPastOrdersAdapter(pastOrderList, getApplicationContext());
                            previousOrdersListView.setAdapter(b);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }
}

