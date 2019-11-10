package com.example.ccimp.ui.supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.presenter.SupplierCurrentRequestAdapter;
import com.example.ccimp.ui.presenter.SupplierHomePresenter;
import com.example.ccimp.ui.view.SupplierHomeView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SupplierHomeActivity extends AppCompatActivity implements SupplierHomeView {

    Request request1 = new Request("Starbucks", "123", "231", "345", "200", "2019/11/1",
            "2019/10/31", "Working");
    Request[] values = new Request[]{request1};

    Button btnseehistory;
    ArrayList<Request> requestArrayList;
    SupplierCurrentRequestAdapter supplierCurrentRequestAdapter;

    protected void onCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_home);

        btnseehistory = findViewById(R.id.btnHistory);
        btnseehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SupplierHomeActivity.this, SupplierRequestsHistoryActivity.class));
            }
        });

        requestArrayList = new ArrayList<Request>();
        View rootView = inflater.inflate(R.layout.activity_supplier_home, container, false);

        supplierCurrentRequestAdapter = new SupplierCurrentRequestAdapter(this, R.layout.row, requestArrayList);
        ListView requestListView = (ListView) rootView.findViewById(R.id.current_request_listview);
        requestListView.setAdapter(supplierCurrentRequestAdapter);

        requestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SupplierHomeActivity.this, SupplierRequestDetailActivity.class);
                intent.putExtra("businessName", values[position].getBusinessName());
                intent.putExtra("requestID", values[position].getRequestID());
                intent.putExtra("businessID", values[position].getBusinessID());
                intent.putExtra("status", values[position].getStatus());
                intent.putExtra("totalPrice", values[position].getPrice());
                startActivity(intent);
            }
        });

        BottomNavigationView navigation = findViewById(R.id.supplierNavigation);
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
                Intent c = new Intent(SupplierHomeActivity.this,SupplierHomeActivity.class);
                startActivity(c);
                break;
            case R.id.navigation_supplier_inventory:
                Intent d = new Intent(SupplierHomeActivity.this, SupplierInventoryActivity.class);
                startActivity(d);
                break;
            case R.id.navigation_supplier_profile:
                Intent b = new Intent(SupplierHomeActivity.this, SupplierProfileActivity.class);
                startActivity(b);
                break;
        }
        return false;
    }



    @Override
    public void SupplierRequestHistory(int supplierID) {

    }

    @Override
    public void SupplierRequestDetail(int requestID) {

    }
}
