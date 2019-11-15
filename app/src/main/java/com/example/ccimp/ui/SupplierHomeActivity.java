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
import com.example.ccimp.ui.model.BusinessRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SupplierHomeActivity extends AppCompatActivity {
    List<BusinessRequest> requestList;
    ListView listView;
    String userEmail;
    Button btnSeehistory;
    BusinessRequest[] values = new BusinessRequest[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_home);

        btnSeehistory = findViewById(R.id.btnHistory);
        btnSeehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SupplierHomeActivity.this, SupplierRequestsHistoryActivity.class));
            }
        });

        listView = findViewById(R.id.current_orders_listview);

        requestList = new ArrayList<>();
        showList();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SupplierHomeActivity.this, SupplierRequestDetailActivity.class);
                intent.putExtra("businessName", values[position].getBusinessName());
                intent.putExtra("requestID", values[position].getRequestID());
                intent.putExtra("supplierID", values[position].getSupplierID());
                intent.putExtra("businessID", values[position].getBusinessID());
                intent.putExtra("price", values[position].getPrice());
                intent.putExtra("needByDate", values[position].getNeedByDate());
                intent.putExtra("requestDate", values[position].getRequestDate());
                intent.putExtra("status", values[position].getStatus());
                startActivity(intent);



            }
        });


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            userEmail = bundle.getString("email");
        }




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
                        d.putExtra("email", userEmail);
                        startActivity(d);
                        break;
                    case R.id.navigation_supplier_profile:
                        Intent b = new Intent(SupplierHomeActivity.this,SupplierProfileActivity.class);
                        b.putExtra("email", userEmail);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }

    class businessRequestAdapter extends ArrayAdapter<BusinessRequest> {
        private List<BusinessRequest> requestList;
        private Context ctx;
        public businessRequestAdapter(List<BusinessRequest> P, Context c){
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
            TextView price = view.findViewById(R.id.column2);
            TextView status = view.findViewById(R.id.column3);

            BusinessRequest businessRequest = requestList.get(position);
            businessName.setText(businessRequest.getBusinessName());
            price.setText(businessRequest.getPrice());
            status.setText(businessRequest.getStatus());


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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getBusinessRequests.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("businessRequest");
                            System.out.println(array);
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);
                                BusinessRequest businessRequest = new BusinessRequest(orderObj.getString("businessName"),orderObj.getString("requestID"),  orderObj.getString("supplierID"), orderObj.getString("businessID"), orderObj.getString("price"), orderObj.getString("needByDate"), orderObj.getString("requestDate"), orderObj.getString("status"));
                                requestList.add(businessRequest);
                                values[i] = businessRequest;
                            }
                            System.out.println(requestList.toString());

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
