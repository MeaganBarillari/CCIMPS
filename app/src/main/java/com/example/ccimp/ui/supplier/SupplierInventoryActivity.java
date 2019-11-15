package com.example.ccimp.ui.supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
    private User supplier;
    private String supplierID;
    BottomNavigationView navigation;
    private SupplierInventoryAdapter supplierInventoryAdapter;
    private SupplierInventoryInterface.SupplierInventoryPresenter supplierInventoryPresenter;
    Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_inventory);

        Intent intent = getIntent();
        supplierID = getIntentData(intent);
        if(supplierID!=null){
            supplierInventoryPresenter = new SupplierInventoryPresenter(this, supplierID);

            btnAddItem = findViewById(R.id.btnadditem);
            navigation = findViewById(R.id.supplierNavigation);
            listView = findViewById(R.id.supplier_inventory_listview);
            supplierInventoryPresenter.onViewCreate();

            //TODO: AHHHHHHHHHHHHHHHHHHH
            btnAddItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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
                Intent c = new Intent(SupplierInventoryActivity.this, SupplierHomeActivity.class);
                c.putExtra("userEmail", supplier.getEmail());
                startActivity(c);
                break;
            case R.id.navigation_supplier_inventory:
                break;
            case R.id.navigation_supplier_profile:
                Intent b = new Intent(SupplierInventoryActivity.this, SupplierProfileActivity.class);
                b.putExtra("supplier", supplier);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public String getIntentData(Intent intent) {
        return intent.getStringExtra("supplierID");
    }

    @Override
    public void setSupplierUser(User supplier) {
        this.supplier = supplier;
    }

    @Override
    public void setupInventoryList(ArrayList<inventory_supplier> inventoryArrayList) {
        supplierInventoryAdapter = new SupplierInventoryAdapter(this, R.layout.rowtwolines, inventoryArrayList);
        listView.setAdapter(supplierInventoryAdapter);
    }
}
