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
    SupplierProfileInterface.SupplierProfilePresenter supplierProfilePresenter;
    private User user = new User("123", "supplier", "supplier@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_profile);

        supplierProfilePresenter = new SupplierProfilePresenter(this, user.getUserID());

        btnlogout = findViewById(R.id.btn_login);

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

    @Override
    public boolean callSupplierNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.supplier_navigation_home:
                Intent c = new Intent(SupplierProfileActivity.this, SupplierHomeActivity.class);
                startActivity(c);
                break;
            case R.id.navigation_supplier_inventory:
                Intent d = new Intent(SupplierProfileActivity.this, SupplierInventoryActivity.class);
                startActivity(d);
                break;
            case R.id.navigation_supplier_profile:
                Intent b = new Intent(SupplierProfileActivity.this,SupplierProfileActivity.class);
                startActivity(b);
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