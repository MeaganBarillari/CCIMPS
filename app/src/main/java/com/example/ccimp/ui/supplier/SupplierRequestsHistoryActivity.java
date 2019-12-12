package com.example.ccimp.ui.supplier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.supplier.SupplierRequestHistoryInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.order_info;
import com.example.ccimp.ui.model.request_info;
import com.example.ccimp.ui.presenter.supplier.SupplierCurrentRequestAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestHistoryAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestHistoryPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SupplierRequestsHistoryActivity extends AppCompatActivity implements SupplierRequestHistoryInterface.SupplierRequestHistoryView {

    private ListView requestListView;
    BottomNavigationView navigation;
    private SupplierRequestHistoryAdapter supplierRequestHistoryAdapter;
    private SupplierRequestHistoryInterface.SupplierRequestHistoryPresenter supplierRequestHistoryPresenter;
    private User supplier;
    ArrayList<BusinessRequest> requestList;
    private  BusinessRequest[] values = new BusinessRequest[10000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_request_history);

        // Get supplier object from
        Intent intent = getIntent();
        supplier = intent.getParcelableExtra("supplier");

        requestListView = findViewById(R.id.previous_requests_listview);
        requestList = new ArrayList<>();
        setupRequestHistoryList();


        requestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SupplierRequestsHistoryActivity.this, SupplierRequestDetailActivity.class);
                intent.putExtra("supplier", supplier);
                intent.putExtra("businessName", values[position].getBusinessName());
                intent.putExtra("requestID", values[position].getRequestID());
                intent.putExtra("businessID", values[position].getBusinessID());
                intent.putExtra("price", values[position].getPrice());
                intent.putExtra("needByDate", values[position].getNeedByDate());
                intent.putExtra("requestDate", values[position].getRequestDate());
                intent.putExtra("status", values[position].getStatus());
                startActivity(intent);
            }
        });

        navigation = findViewById(R.id.supplierNavigation);

        // Pass to presenter the userID so we can properly populate the history list from the supplierID
//        supplierRequestHistoryPresenter = new SupplierRequestHistoryPresenter(this, supplier);
//
//        supplierRequestHistoryPresenter.onViewCreate();

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
            case R.id.supplier_navigation_home:
                Intent c = new Intent(SupplierRequestsHistoryActivity.this, SupplierHomeActivity.class);
                c.putExtra("supplier", supplier);
                startActivity(c);
                break;
            case R.id.navigation_supplier_inventory:
                Intent d = new Intent(SupplierRequestsHistoryActivity.this, SupplierInventoryActivity.class);
                d.putExtra("supplier", supplier);
                startActivity(d);
                break;
            case R.id.navigation_supplier_profile:
                Intent b = new Intent(SupplierRequestsHistoryActivity.this, SupplierProfileActivity.class);
                b.putExtra("supplier", supplier);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public User getIntentData(Intent intent) {
        User supplier = intent.getParcelableExtra("supplier");

        if (supplier != null){
            return supplier;
        }
        return null;
    }

    @Override
    public void setupRequestHistoryList() {
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
                                if(businessRequest.getSupplierID().equals(supplier.getUserID()) &&  (businessRequest.getStatus().equals("Complete"))){
                                    requestList.add(businessRequest);
                                    for(int j = 0; j < values.length; j++) {
                                        if(values[j] == null) {
                                            values[j] = businessRequest;
                                            break;
                                        }
                                    }
                                }
                            }
                            SupplierRequestHistoryAdapter adapter = new SupplierRequestHistoryAdapter(requestList, getApplicationContext());
                            requestListView.setAdapter(adapter);
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
