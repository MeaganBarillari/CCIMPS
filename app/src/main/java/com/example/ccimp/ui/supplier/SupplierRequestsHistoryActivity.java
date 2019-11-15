package com.example.ccimp.ui.supplier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.supplier.SupplierRequestHistoryInterface;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestHistoryAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestHistoryPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SupplierRequestsHistoryActivity extends AppCompatActivity implements SupplierRequestHistoryInterface.SupplierRequestHistoryView {

    private ListView requestListView;
    BottomNavigationView navigation;
    private SupplierRequestHistoryAdapter supplierRequestHistoryAdapter;
    private SupplierRequestHistoryInterface.SupplierRequestHistoryPresenter supplierRequestHistoryPresenter;
    private User supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_request_history);

        // Get supplier object from
        Intent intent = getIntent();
        supplier = getIntentData(intent);
        if(supplier != null){
            requestListView = findViewById(R.id.previous_requests_listview);
            navigation = findViewById(R.id.supplierNavigation);

            // Pass to presenter the userID so we can properly populate the history list from the supplierID
            supplierRequestHistoryPresenter = new SupplierRequestHistoryPresenter(this, supplier.getUserID());

            supplierRequestHistoryPresenter.onViewCreate();

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
                Intent c = new Intent(SupplierRequestsHistoryActivity.this, SupplierHomeActivity.class);
                c.putExtra("userEmail", supplier.getEmail());
                startActivity(c);
                break;
            case R.id.navigation_supplier_inventory:
                Intent d = new Intent(SupplierRequestsHistoryActivity.this, SupplierInventoryActivity.class);
                d.putExtra("supplierID", supplier);
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
    public void setupRequestHistoryList(ArrayList<Request> requestArrayList) {
        supplierRequestHistoryAdapter = new SupplierRequestHistoryAdapter(this, R.layout.row, requestArrayList);
        requestListView.setAdapter(supplierRequestHistoryAdapter);
    }
}
