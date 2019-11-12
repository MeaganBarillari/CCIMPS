package com.example.ccimp.ui.supplier;

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
import com.example.ccimp.ui.interfaces.supplier.SupplierRequestDetailInterface;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.model.request_info;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestDetailAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestDetailPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SupplierRequestDetailActivity extends AppCompatActivity implements SupplierRequestDetailInterface.SupplierRequestDetailView {

    private TextView businessName, requestID, status, totalPrice;
    private ListView requestItemListView;
    BottomNavigationView navigation;
    private String supplierID;
    private Request tempRequest;
    private SupplierRequestDetailAdapter supplierRequestDetailAdapter;
    private SupplierRequestDetailInterface.SupplierRequestDetailPresenter supplierRequestDetailPresenter;
    Button btnChangeStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_request_detail);

        requestItemListView = findViewById(R.id.requestiems);
        navigation = findViewById(R.id.supplierNavigation);
        btnChangeStatus = findViewById(R.id.btnChangeStatus);
        businessName = findViewById(R.id.business_name);
        requestID = findViewById(R.id.request_number);
        status = findViewById(R.id.request_status);
        totalPrice = findViewById(R.id.totalPrice);

        // Gets request object and sets text view's based on request fields
        // returns the request object that we use to get the supplierID and get the requestItem Listview
        tempRequest = getRequestObjectFromIntent();
        if(tempRequest != null){
            supplierID = tempRequest.getSupplierID();

            supplierRequestDetailPresenter = new SupplierRequestDetailPresenter(this, supplierID);
            supplierRequestDetailPresenter.onViewCreate();
        }

        btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
                Intent c = new Intent(SupplierRequestDetailActivity.this, SupplierHomeActivity.class);
                startActivity(c);
                break;
            case R.id.navigation_supplier_inventory:
                Intent d = new Intent(SupplierRequestDetailActivity.this, SupplierInventoryActivity.class);
                startActivity(d);
                break;
            case R.id.navigation_supplier_profile:
                Intent b = new Intent(SupplierRequestDetailActivity.this, SupplierProfileActivity.class);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public void setupRequestItemList(ArrayList<request_info> requestItemArrayList) {
        supplierRequestDetailAdapter = new SupplierRequestDetailAdapter(this, R.layout.row, requestItemArrayList);
        requestItemListView.setAdapter(supplierRequestDetailAdapter);
    }

    public Request getRequestObjectFromIntent(){
        Intent intent = getIntent();
        Request request = intent.getParcelableExtra("Request");

        if (request != null){
            businessName.setText(request.getBusinessName());
            requestID.setText(request.getRequestID());
            status.setText(request.getStatus());
            totalPrice.setText(request.getPrice());
            return request;
        }
        return null;
    }
}
