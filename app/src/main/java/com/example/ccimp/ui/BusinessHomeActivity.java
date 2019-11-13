package com.example.ccimp.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ccimp.R;
import com.example.ccimp.ui.model.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessHomeActivity extends AppCompatActivity {

    Button btnHistory;
    ListView listView;
    List<Order> orderList;
//    Order order1 = new Order("William", "123456", "2019/11/1", "124578", "987654321", "Done", "600");
//    Order order2 = new Order("Shifan", "2019/11/1", "2019/11/1", "Working", "1", "312", "123");
//    Order order3 = new Order("Meagan", "2019/11/12", "2019/01/01", "Cooking", "1", "200", "123456");
//    Order order4 = new Order("Brandon", "2019/12/1", "September", "Cancel", "1", "200", "123456");
//    Order order5 = new Order("Nikolaj", "2019/10/31", "September", "Waiting", "1", "200", "123456");
//    Order order6 = new Order("Lucille", "2019/01/13", "September", "Start", "1", "200", "123456");
//    Order[] values = new Order[]{order1, order2, order3, order4, order5, order6};

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

        orderList = new ArrayList<>();
        showList();
        System.out.println(orderList);



//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(BusinessHomeActivity.this, BusinessOrderDetailActivity.class);
//                intent.putExtra("customerName", values[position].getCustomerName());
//                intent.putExtra("orderID", values[position].getOrderID());
//                intent.putExtra("createDateTime", values[position].getCreateDateTime());
//                intent.putExtra("businessID", values[position].getBusinessID());
//                intent.putExtra("userID", values[position].getUserID());
//                intent.putExtra("status", values[position].getStatus());
//                intent.putExtra("totalPrice", values[position].getTotalPrice());
//                startActivity(intent);
//            }
//        });


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

    class customerOrderAdapter extends ArrayAdapter<Order> {
        private List<Order> orderList;
        private Context ctx;
        public customerOrderAdapter(List<Order> P, Context c){
            super(c, R.layout.activity_business_home, P);
            this.orderList = P;
            this.ctx = c;
        }

        @Override
        public View getView(int position, View mView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = mView;

            if(view == null) {
                view = inflater.inflate(R.layout.row, parent, false);

            }
                    TextView customerName = view.findViewById(R.id.column1);
                    TextView dateTime = view.findViewById(R.id.column2);
                    TextView status = view.findViewById(R.id.column3);

                    Order order = orderList.get(position);
                    customerName.setText(order.getCustomerName());
                    dateTime.setText(order.getCreateDateTime());
                    status.setText(order.getStatus());


            return view;
        }
    }

    private static class Handler{
        public  static Handler mInstance;
        private RequestQueue requestQueue;
        private static Context ctx;

        private Handler(Context context) {
            ctx = context;
            requestQueue = getRequestQueue();
        }

        public RequestQueue getRequestQueue(){
            if(requestQueue == null) {
                requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
            }

            return requestQueue;
        }

        public static synchronized  Handler getInstance (Context context) {
            if(mInstance == null) {
                mInstance = new Handler(context);
            }

            return mInstance;
        }

        public  <T> void addToRequestQue(Request<T> request){
            requestQueue.add(request);
        }
    }

    private void showList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getBusinessRequests.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("businessRequest");
                            System.out.println(array + "!");
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);

                                String customerName = orderObj.getString("businessID");
                                System.out.println(customerName);
                                Order o = new Order(customerName, Integer.toString(i), orderObj.getString("requestDate"), Integer.toString(i), Integer.toString(i), orderObj.getString("status"), Integer.toString(i));
                                orderList.add(o);
                            }

                            customerOrderAdapter adapter = new customerOrderAdapter(orderList, getApplicationContext());
                            listView.setAdapter(adapter);
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
