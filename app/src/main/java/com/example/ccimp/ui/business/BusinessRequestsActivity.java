package com.example.ccimp.ui.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.customer.CustomerOrdersActivity;
import com.example.ccimp.ui.interfaces.business.BusinessRequestsInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessCurrentRequestsAdapter;
import com.example.ccimp.ui.presenter.business.BusinessHistoryRequestsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.ccimp.ui.presenter.business.BusinessRequestsPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusinessRequestsActivity extends AppCompatActivity implements BusinessRequestsInterface.BusinessRequestsView {

    ArrayList<BusinessRequest> currentRequestList;
    ArrayList<BusinessRequest> pastRequestList;
    User business;
    ListView listView1, listView2;
    BottomNavigationView navigation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_requests);

        Intent intent = getIntent();
        business = intent.getParcelableExtra("business");

        navigation = findViewById(R.id.businessNavigation);

        listView1 = findViewById(R.id.current_request_listview);


        listView2 = findViewById(R.id.previous_requests_listview);

        currentRequestList = new ArrayList<>();
        pastRequestList = new ArrayList<>();
        setupRequestsList();



        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return callSupplierNavigation(item);
            }

        });
    }

    @Override
    public boolean callSupplierNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.navigation_home:
                Intent c = new Intent(BusinessRequestsActivity.this, BusinessHomeActivity.class);
                c.putExtra("business", business);
                startActivity(c);
                break;
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessRequestsActivity.this,BusinessRequestsActivity.class);
                a.putExtra("business", business);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessRequestsActivity.this, BusinessInventoryActivity.class);
                b.putExtra("business", business);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessRequestsActivity.this, BusinessProfileActivity.class);
                d.putExtra("business", business);
                startActivity(d);
                break;
        }
        return false;
    }

    @Override
    public void setupRequestsList() {
//        businessCurrentRequestsAdapter = new BusinessCurrentRequestsAdapter(this, R.layout.rowtwolines, requestsArrayList);
//        listView1.setAdapter(businessCurrentRequestsAdapter);
//
//        businessHistoryRequestsAdapter = new BusinessHistoryRequestsAdapter(this, R.layout.row, requestsArrayList);
//        listView2.setAdapter(businessHistoryRequestsAdapter);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getBusinessRequests.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("businessRequest");
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);
                                BusinessRequest businessRequest = new BusinessRequest(orderObj.getString("businessName"),orderObj.getString("requestID"),  orderObj.getString("supplierID"), orderObj.getString("businessID"), orderObj.getString("price"), orderObj.getString("needByDate"), orderObj.getString("requestDate"), orderObj.getString("status"));
                                if(businessRequest.getBusinessID().equals(business.getUserID())){
                                    if(! businessRequest.getStatus().equals("Complete")) {
                                        currentRequestList.add(businessRequest);

                                    }else{
                                        //System.out.println(order.getStatus());

                                        pastRequestList.add(businessRequest);
                                    }

                                }


                            }

                            BusinessCurrentRequestsAdapter adapter = new BusinessCurrentRequestsAdapter(currentRequestList, getApplicationContext());
                            listView1.setAdapter(adapter);

                            BusinessHistoryRequestsAdapter b = new BusinessHistoryRequestsAdapter(pastRequestList, getApplicationContext());
                            listView2.setAdapter(b);
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