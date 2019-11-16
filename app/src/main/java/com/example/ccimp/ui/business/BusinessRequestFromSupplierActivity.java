package com.example.ccimp.ui.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.business.BusinessRequestFromSupplierInterface;
import com.example.ccimp.ui.interfaces.business.BusinessRequestsInterface;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessCurrentRequestsAdapter;
import com.example.ccimp.ui.presenter.business.BusinessHistoryRequestsAdapter;
import com.example.ccimp.ui.presenter.business.BusinessRequestFromMenuAdapter;
import com.example.ccimp.ui.presenter.business.BusinessRequestFromSupplierPresenter;
import com.example.ccimp.ui.presenter.business.BusinessRequestSupplierAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BusinessRequestFromSupplierActivity extends AppCompatActivity implements BusinessRequestFromSupplierInterface.BusinessRequestFromSupplierView {

    ListView listView1, listView2;
    private User user = new User("123", "business", "business@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    BottomNavigationView navigation;
    private BusinessRequestFromMenuAdapter businessRequestFromMenuAdapter;
    private BusinessRequestSupplierAdapter businessRequestSupplierAdapter;
    private BusinessRequestFromSupplierInterface.BusinessRequestFromSupplierPresenter businessRequestFromSupplierPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_from_supplier);

        businessRequestFromSupplierPresenter = new BusinessRequestFromSupplierPresenter(this, user.getUserID());

        navigation = findViewById(R.id.businessNavigation);

        listView1 = findViewById(R.id.current_request_listview);


        listView2 = findViewById(R.id.previous_requests_listview);

        businessRequestFromSupplierPresenter.onViewCreate();

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
                Intent c = new Intent(BusinessRequestFromSupplierActivity.this, BusinessHomeActivity.class);
                startActivity(c);
                break;
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessRequestFromSupplierActivity.this, BusinessRequestsActivity.class);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessRequestFromSupplierActivity.this, BusinessInventoryActivity.class);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessRequestFromSupplierActivity.this, BusinessProfileActivity.class);
                startActivity(d);
                break;
        }
        return false;
    }

    @Override
    public void setupItemList(ArrayList<Item> ItemArrayList) {
        businessRequestFromMenuAdapter = new BusinessRequestFromMenuAdapter(this, R.layout.rowoneline, ItemArrayList);
        listView1.setAdapter(businessRequestFromMenuAdapter);
    }

    @Override
    public void setupSupplierList(ArrayList<User> UserArrayList) {
        businessRequestSupplierAdapter = new BusinessRequestSupplierAdapter(this, R.layout.rowoneline, UserArrayList);
        listView2.setAdapter(businessRequestSupplierAdapter);

    }

}
