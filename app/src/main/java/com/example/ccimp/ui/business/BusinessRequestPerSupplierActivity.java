package com.example.ccimp.ui.business;

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
import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.business.BusinessRequestPerSupplierInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.presenter.business.BusinessInventoryAdapter;
import com.example.ccimp.ui.presenter.business.BusinessRequestPerSupplierAdapter;
import com.example.ccimp.ui.presenter.business.BusinessRequestPerSupplierPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BusinessRequestPerSupplierActivity extends AppCompatActivity implements BusinessRequestPerSupplierInterface.BusinessRequestPerSupplierView{
    Button btnBack, btnCart, btnFeedback;
    ListView listView;
    private User user = new User("123", "business", "business@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    BottomNavigationView navigation;
    private BusinessRequestPerSupplierAdapter businessRequestPerSupplierAdapter;
    private BusinessRequestPerSupplierInterface.BusinessRequestPerSupplierPresenter businessRequestPerSupplierPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_per_supplier);

        btnBack = findViewById(R.id.btBack);
        btnCart = findViewById(R.id.btCart);
        btnFeedback = findViewById(R.id.btFeedback);

        businessRequestPerSupplierPresenter = new BusinessRequestPerSupplierPresenter(this, user.getUserID());

        navigation = findViewById(R.id.businessNavigation);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessRequestPerSupplierActivity.this, BusinessSupplierListActivity.class));
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessRequestPerSupplierActivity.this, BusinessRequestCartActivity.class));
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        listView = findViewById(R.id.suppliermenu);

        businessRequestPerSupplierPresenter.onViewCreate();

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
                Intent c = new Intent(BusinessRequestPerSupplierActivity.this, BusinessHomeActivity.class);
                startActivity(c);
                break;
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessRequestPerSupplierActivity.this, BusinessRequestsActivity.class);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessRequestPerSupplierActivity.this, BusinessInventoryActivity.class);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessRequestPerSupplierActivity.this, BusinessProfileActivity.class);
                startActivity(d);
                break;
        }
        return false;
    }

    @Override
    public void setupInventoryList(ArrayList<inventory_supplier> inventoryArrayList) {
        businessRequestPerSupplierAdapter = new BusinessRequestPerSupplierAdapter(this, R.layout.rowoneline, inventoryArrayList);
        listView.setAdapter(businessRequestPerSupplierAdapter);
    }

}
