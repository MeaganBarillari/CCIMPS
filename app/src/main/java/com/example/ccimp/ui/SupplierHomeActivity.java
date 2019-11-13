package com.example.ccimp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ccimp.R;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.Request;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SupplierHomeActivity extends AppCompatActivity {
    List<Request> requestList;
    ListView listView;

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

        listView = findViewById(R.id.current_request_listview);

        requestList = new ArrayList<>();
        showList();



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

    class businessRequestAdapter extends ArrayAdapter<Request> {
        private List<Request> requestList;
        private Context ctx;
        public businessRequestAdapter(List<Request> P, Context c){
            super(c, R.layout.activity_supplier_home, P);
            this.requestList = P;
            this.ctx = c;
        }

        @Override
        public View getView(int position, View mView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = mView;

            if(view == null) {
                view = inflater.inflate(R.layout.row, parent, false);

            }
            TextView businessName = view.findViewById(R.id.column1);
            TextView requestDate = view.findViewById(R.id.column2);
            TextView status = view.findViewById(R.id.column3);

            Request request = requestList.get(position);
            businessName.setText(request.getBusinessName());
            requestDate.setText(request.getRequestDate());
            status.setText(request.getStatus());


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

        public static synchronized Handler getInstance (Context context) {
            if(mInstance == null) {
                mInstance = new Handler(context);
            }

            return mInstance;
        }

        public  <T> void addToRequestQue(com.android.volley.Request<T> request){
            requestQueue.add(request);
        }
    }

    private void showList() {
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, "http://shifanzhou.com/getBusinessRequests.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("businessRequest");
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);

                                Request r = new Request(orderObj.getString("requestID"), orderObj.getString("businessName"), orderObj.getString("supplierID"), orderObj.getString("businessID"), orderObj.getString("price"), "1", orderObj.getString("requestDate"), orderObj.getString("status"));
                                requestList.add(r);
                            }

                            businessRequestAdapter adapter = new businessRequestAdapter(requestList, getApplicationContext());
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
