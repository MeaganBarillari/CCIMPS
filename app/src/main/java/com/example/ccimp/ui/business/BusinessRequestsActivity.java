package com.example.ccimp.ui.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.business.BusinessRequestsInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessCurrentRequestsAdapter;
import com.example.ccimp.ui.presenter.business.BusinessHistoryRequestsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.ccimp.ui.presenter.business.BusinessRequestsPresenter;

import java.util.ArrayList;

public class BusinessRequestsActivity extends AppCompatActivity implements BusinessRequestsInterface.BusinessRequestsView {

    ListView listView1, listView2;
    private User user = new User("123", "business", "business@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    BottomNavigationView navigation;
    private BusinessCurrentRequestsAdapter businessCurrentRequestsAdapter;
    private BusinessHistoryRequestsAdapter businessHistoryRequestsAdapter;
    private BusinessRequestsInterface.BusinessRequestsPresenter businessRequestsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_requests);

        businessRequestsPresenter = new BusinessRequestsPresenter(this, user.getUserID());

        navigation = findViewById(R.id.businessNavigation);

        listView1 = findViewById(R.id.current_request_listview);


        listView2 = findViewById(R.id.previous_requests_listview);

        businessRequestsPresenter.onViewCreate();

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
                startActivity(c);
                break;
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessRequestsActivity.this,BusinessRequestsActivity.class);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessRequestsActivity.this, BusinessInventoryActivity.class);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessRequestsActivity.this, BusinessProfileActivity.class);
                startActivity(d);
                break;
        }
        return false;
    }

    @Override
    public void setupRequestsList(ArrayList<BusinessRequest> requestsArrayList) {
        businessCurrentRequestsAdapter = new BusinessCurrentRequestsAdapter(this, R.layout.rowtwolines, requestsArrayList);
        listView1.setAdapter(businessCurrentRequestsAdapter);

        businessHistoryRequestsAdapter = new BusinessHistoryRequestsAdapter(this, R.layout.row, requestsArrayList);
        listView2.setAdapter(businessHistoryRequestsAdapter);
    }
}