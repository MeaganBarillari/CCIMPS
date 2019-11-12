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

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.supplier.SupplierHomeInterface;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.supplier.SupplierCurrentRequestAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierHomePresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SupplierHomeActivity extends AppCompatActivity implements SupplierHomeInterface.SupplierHomeView {

    // TODO: Change to use shared preferences!
    private User user = new User("123", "supplier", "supplier@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");

    private Button btnseehistory;
    private ListView requestListView;
    BottomNavigationView navigation;
    private SupplierCurrentRequestAdapter supplierCurrentRequestAdapter;
    private SupplierHomeInterface.SupplierHomePresenter supplierHomePresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_home);



        // TODO: Use shared preferences for userID passed to request history activity
        supplierHomePresenter = new SupplierHomePresenter(this, user.getUserID());

        navigation = findViewById(R.id.supplierNavigation);
        btnseehistory = findViewById(R.id.btnHistory);
        requestListView = findViewById(R.id.current_requests_listview);

        // Will populate the request array list by calling to database and creating request objects
        supplierHomePresenter.onViewCreate();



        // Listens for a click on the see history button, simply passes along the supplierID to that acitivity,
        // populating the listview will be handled by the SupplierRequestHistoryPresenter
        // TODO: Use shared preferences for userID passed to request history activity
        btnseehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupplierHomeActivity.this, SupplierRequestsHistoryActivity.class);
                intent.putExtra("SupplierID", user.getUserID());
                startActivity(intent);
            }
        });

        // Get requestID selected and pass it to RequestDetailActivity
        requestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Request  request = (Request) parent.getItemAtPosition(position);
                String requestID = request.getRequestID();
                Intent intent = new Intent(SupplierHomeActivity.this, SupplierRequestDetailActivity.class);
                intent.putExtra("RequestID", requestID);
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

    // Directs supplier to correct activity based on navigation selected
    @Override
    public boolean callSupplierNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.supplier_navigation_home:
                Intent home = new Intent(SupplierHomeActivity.this, SupplierHomeActivity.class);
                startActivity(home);
                break;
            case R.id.navigation_supplier_inventory:
                Intent inventory = new Intent(SupplierHomeActivity.this, SupplierInventoryActivity.class);
                startActivity(inventory);
                break;
            case R.id.navigation_supplier_profile:
                Intent profile = new Intent(SupplierHomeActivity.this, SupplierProfileActivity.class);
                startActivity(profile);
                break;
        }
        return false;
    }

    // Called by presenter
    public void setupRequestList(ArrayList<Request> requestList) {
        supplierCurrentRequestAdapter = new SupplierCurrentRequestAdapter(this, R.layout.row, requestList);
        requestListView.setAdapter(supplierCurrentRequestAdapter);
    }
}
