package com.example.ccimp.ui.supplier;

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
import com.example.ccimp.ui.interfaces.supplier.SupplierProfileInterface;
import com.example.ccimp.ui.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.ccimp.ui.presenter.supplier.SupplierProfilePresenter;


public class SupplierProfileActivity extends AppCompatActivity implements SupplierProfileInterface.SupplierProfileView {

    TextView userName, userEmail, userMobile, userAddress;
    Button btnlogout;
    BottomNavigationView navigation;
    private User supplier;
    SupplierProfileInterface.SupplierProfilePresenter supplierProfilePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_profile);

        Intent intent = getIntent();
        supplier = getIntentData(intent);
        if(supplier != null){

            supplierProfilePresenter = new SupplierProfilePresenter(this);

            btnlogout = findViewById(R.id.btn_logout);
            navigation = findViewById(R.id.supplierNavigation);
            userName = findViewById(R.id.user_profile_name);
            userEmail = findViewById(R.id.user_email);
            userMobile = findViewById(R.id.user_mobile);
            userAddress = findViewById(R.id.user_address);

            supplierProfilePresenter.onViewCreate();

            btnlogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SupplierProfileActivity.this, MainActivity.class));
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
                Intent c = new Intent(SupplierProfileActivity.this, SupplierHomeActivity.class);
                c.putExtra("userEmail", supplier.getEmail());
                startActivity(c);
                break;
            case R.id.navigation_supplier_inventory:
                Intent d = new Intent(SupplierProfileActivity.this, SupplierInventoryActivity.class);
                d.putExtra("supplierID", supplier.getUserID());
                startActivity(d);
                break;
            case R.id.navigation_supplier_profile:
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
    public void setupProfile() {
        userName.setText(supplier.getUsername());
        userEmail.setText(supplier.getEmail());
        userMobile.setText(supplier.getPhone());
        userAddress.setText(supplier.getAddress());
    }


}