package com.example.ccimp.ui.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.MainActivity;
import com.example.ccimp.ui.interfaces.business.BusinessProfileInterface;
import com.example.ccimp.ui.presenter.business.BusinessProfilePresenter;

import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.supplier.SupplierHomeActivity;
import com.example.ccimp.ui.supplier.SupplierInventoryActivity;
import com.example.ccimp.ui.supplier.SupplierProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BusinessProfileActivity extends AppCompatActivity implements BusinessProfileInterface.BusinessProfileView{

    TextView userName, userEmail, userMobile, userAddress;
    Button btnLogout;
    BottomNavigationView navigation;
    BusinessProfileInterface.BusinessProfilePresenter businessProfilePresenter;
    private User user = new User("123", "business", "business@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile);

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessProfileActivity.this, MainActivity.class));
            }
        });

        businessProfilePresenter = new BusinessProfilePresenter(this, user.getUserID());

        navigation = findViewById(R.id.businessNavigation);

        userName = findViewById(R.id.business_profile_name);
        userEmail = findViewById(R.id.user_email);
        userMobile = findViewById(R.id.business_mobile);
        userAddress = findViewById(R.id.business_address);

        businessProfilePresenter.onViewCreate();

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
                Intent c = new Intent(BusinessProfileActivity.this, BusinessHomeActivity.class);
                startActivity(c);
                break;
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessProfileActivity.this, BusinessRequestsActivity.class);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessProfileActivity.this, BusinessInventoryActivity.class);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessProfileActivity.this,BusinessProfileActivity.class);
                startActivity(d);
                break;
        }
        return false;
    }

    @Override
    public void setupProfile() {
        userName.setText(user.getUsername());
        userEmail.setText(user.getEmail());
        userMobile.setText(user.getPhone());
        userAddress.setText(user.getAddress());
    }
}
