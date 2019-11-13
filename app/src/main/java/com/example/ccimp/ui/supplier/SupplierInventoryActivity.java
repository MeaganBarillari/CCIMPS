package com.example.ccimp.ui.supplier;

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
import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.presenter.supplier.SupplierInventoryAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.ccimp.ui.presenter.supplier.SupplierInventoryPresenter;

import java.util.ArrayList;

public class SupplierInventoryActivity extends AppCompatActivity implements SupplierInventoryInterface.SupplierInventoryView {

    ListView listView;
    inventory_supplier inventory1 = new inventory_supplier("123", "312", "Beans", "200", "30");
    private User user = new User("123", "supplier", "supplier@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    BottomNavigationView navigation;
    private SupplierInventoryAdapter supplierInventoryAdapter;
    private SupplierInventoryInterface.SupplierInventoryPresenter supplierInventoryPresenter;

    Button btnadditem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_inventory);

        // TODO: Use shared preferences for userID passed to request history activity
        supplierInventoryPresenter = new SupplierInventoryPresenter(this, user.getUserID());

        btnadditem = findViewById(R.id.btnadditem);
        navigation = findViewById(R.id.supplierNavigation);

        btnadditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        listView = findViewById(R.id.supplier_inventory_listview);
        supplierInventoryPresenter.onViewCreate();

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
                Intent c = new Intent(SupplierInventoryActivity.this, SupplierHomeActivity.class);
                startActivity(c);
                break;
            case R.id.navigation_supplier_inventory:
                Intent d = new Intent(SupplierInventoryActivity.this,SupplierInventoryActivity.class);
                startActivity(d);
                break;
            case R.id.navigation_supplier_profile:
                Intent b = new Intent(SupplierInventoryActivity.this, SupplierProfileActivity.class);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public void setupInventoryList(ArrayList<inventory_supplier> inventoryArrayList) {
        supplierInventoryAdapter = new SupplierInventoryAdapter(this, R.layout.rowtwolines, inventoryArrayList);
        listView.setAdapter(supplierInventoryAdapter);
    }
}
