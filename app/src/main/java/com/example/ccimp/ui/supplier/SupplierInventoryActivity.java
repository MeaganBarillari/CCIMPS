package com.example.ccimp.ui.supplier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.presenter.supplier.SupplierInventoryAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.ccimp.ui.presenter.supplier.SupplierInventoryPresenter;

import java.util.ArrayList;
import java.util.List;

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
                    Intent intent = new Intent(SupplierInventoryActivity.this, SupplierAddToInventory.class);
                    startActivity(intent);
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

    class supplierInventoryAdapter extends ArrayAdapter<Item> {
        private List<Item> itemList;
        private Context ctx;
        public supplierInventoryAdapter(List<Item> P, Context c){
            super(c, R.layout.activity_supplier_inventory, P);
            this.itemList = P;
            this.ctx = c;
        }

        @Override
        public View getView(int position, View mView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = mView;

            if(view == null) {
                view = inflater.inflate(R.layout.rowtwolines, parent, false);

            }
            TextView itemName = view.findViewById(R.id.column1);
            TextView price = view.findViewById(R.id.column2);


            Item item = itemList.get(position);
            itemName.setText(item.getName());
            price.setText(item.getPrice());


            return view;
        }
    }
}
