package com.example.ccimp.ui.supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.supplier.SupplierHomeInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.supplier.SupplierCurrentRequestAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierHomePresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SupplierHomeActivity extends AppCompatActivity implements SupplierHomeInterface.SupplierHomeView {

    ArrayList<BusinessRequest> requestList;
    private Button btnseehistory;
    private ListView requestListView;
    BottomNavigationView navigation;
    private User supplier;
    private String supplierEmail;
    private SupplierCurrentRequestAdapter supplierCurrentRequestAdapter;
    private SupplierHomeInterface.SupplierHomePresenter supplierHomePresenter;
    private BusinessRequest[] values = new BusinessRequest[1000];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_home);

        // Get User information from Intent
        Intent intent = getIntent();
        supplierEmail = getIntentData(intent);
        if(supplierEmail != null){
            supplierHomePresenter = new SupplierHomePresenter(this, supplierEmail);

            navigation = findViewById(R.id.supplierNavigation);
            btnseehistory = findViewById(R.id.btnHistory);
            requestListView = findViewById(R.id.current_requests_listview);
            requestList = new ArrayList<>();
            setupRequestList();

            // Will populate the request array list by calling to database and creating request objects
            // as well as get the supplier supplier object we need to use for this account
            supplierHomePresenter.onViewCreate();

            // Listens for a click on the see history button, simply passes along the supplierID to that acitivity,
            // populating the listview will be handled by the SupplierRequestHistoryPresenter
            btnseehistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SupplierHomeActivity.this, SupplierRequestsHistoryActivity.class);
                    intent.putExtra("supplier", supplier);
                    startActivity(intent);
                }
            });

            // Get request object and pass it to RequestDetailActivity
            requestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BusinessRequest businessRequest = (BusinessRequest) parent.getItemAtPosition(position);
                    Intent intent = new Intent(SupplierHomeActivity.this, SupplierRequestDetailActivity.class);
                    intent.putExtra("BusinessRequest", businessRequest);
                    startActivity(intent);
                }
            });

            // Handled in activity
            navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    return callSupplierNavigation(item);
                }

            });
        }
    }

    // Directs supplier to correct activity based on navigation selected
    @Override
    public boolean callSupplierNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.supplier_navigation_home:
                // They can't go to home, they are already on it
                // TODO: MAKE SOME SORT OF TOAST THAT STATES THIS OR MAKE THE COLOR ON THE SELECTED ITEM CHANGE
                break;
            case R.id.navigation_supplier_inventory:
                Intent inventory = new Intent(SupplierHomeActivity.this, SupplierInventoryActivity.class);
                inventory.putExtra("supplierID", supplier.getUserID());
                startActivity(inventory);
                break;
            case R.id.navigation_supplier_profile:
                Intent profile = new Intent(SupplierHomeActivity.this, SupplierProfileActivity.class);
                profile.putExtra("supplier", supplier);
                startActivity(profile);
                break;
        }
        return false;
    }

    // Called by presenter
    public void setupRequestList() {
//        supplierCurrentRequestAdapter = new SupplierCurrentRequestAdapter(this, R.layout.row, requestList);
//        requestListView.setAdapter(supplierCurrentRequestAdapter);
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
                                requestList.add(businessRequest);
                                values[i] = businessRequest;
                            }

                            SupplierCurrentRequestAdapter adapter = new SupplierCurrentRequestAdapter(requestList, getApplicationContext());
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

    @Override
    public String getIntentData(Intent intent) {
        return intent.getStringExtra("userEmail");
    }

    @Override
    public void setSupplierUser(User supplier) {
        this.supplier = supplier;
    }
}
