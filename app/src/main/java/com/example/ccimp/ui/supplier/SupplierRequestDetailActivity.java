package com.example.ccimp.ui.supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.supplier.SupplierRequestDetailInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.request_info;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestDetailAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestDetailPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SupplierRequestDetailActivity extends AppCompatActivity implements SupplierRequestDetailInterface.SupplierRequestDetailView {

    private TextView businessName, requestID, status, totalPrice;
    private ListView requestItemListView;
    BottomNavigationView navigation;
    private BusinessRequest tempRequest;
    private User supplier;
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
        Intent intent = getIntent();
        tempRequest = getIntentData(intent);
        if(tempRequest != null){
            supplierRequestDetailPresenter = new SupplierRequestDetailPresenter(this, tempRequest);
            supplierRequestDetailPresenter.onViewCreate();

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
    }

    @Override
    public boolean callSupplierNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.supplier_navigation_home:
                Intent c = new Intent(SupplierRequestDetailActivity.this, SupplierHomeActivity.class);
                c.putExtra("userEmail", supplier.getEmail());
                startActivity(c);
                break;
            case R.id.navigation_supplier_inventory:
                Intent d = new Intent(SupplierRequestDetailActivity.this, SupplierInventoryActivity.class);
                d.putExtra("supplierID", supplier.getUserID());
                startActivity(d);
                break;
            case R.id.navigation_supplier_profile:
                Intent b = new Intent(SupplierRequestDetailActivity.this, SupplierProfileActivity.class);
                b.putExtra("supplier", supplier);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public BusinessRequest getIntentData(Intent intent) {
        BusinessRequest businessRequest = intent.getParcelableExtra("BusinessRequest");

        if (businessRequest != null){
            businessName.setText(businessRequest.getBusinessName());
            requestID.setText(businessRequest.getRequestID());
            status.setText(businessRequest.getStatus());
            totalPrice.setText(businessRequest.getPrice());
            return businessRequest;
        }
        return null;
    }

    @Override
    public void setSupplierUser(User supplier) {
        this.supplier = supplier;
    }

    @Override
    public void setupRequestItemList(ArrayList<request_info> requestItemArrayList) {
        supplierRequestDetailAdapter = new SupplierRequestDetailAdapter(this, R.layout.row, requestItemArrayList);
        requestItemListView.setAdapter(supplierRequestDetailAdapter);
    }

}
