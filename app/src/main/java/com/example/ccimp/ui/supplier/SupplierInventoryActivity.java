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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.presenter.supplier.SupplierInventoryAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.ccimp.ui.presenter.supplier.SupplierInventoryPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SupplierInventoryActivity extends AppCompatActivity implements SupplierInventoryInterface.SupplierInventoryView {

    List<Item> itemList;
    ListView listView;
    private User supplier;
    BottomNavigationView navigation;

    Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_inventory);

        Intent intent = getIntent();
        supplier = intent.getParcelableExtra("supplier");

        btnAddItem = findViewById(R.id.btnadditem);
        navigation = findViewById(R.id.supplierNavigation);
        listView = findViewById(R.id.supplier_inventory_listview);
        itemList = new ArrayList<>();
        setupInventoryList();

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupplierInventoryActivity.this, SupplierAddToInventory.class);
                intent.putExtra("supplier", supplier);
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

    @Override
    public boolean callSupplierNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.supplier_navigation_home:
                Intent c = new Intent(SupplierInventoryActivity.this, SupplierHomeActivity.class);
                c.putExtra("supplier", supplier);
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
    }

    @Override
    public void setupInventoryList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getSupplierInventory.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("supplierInventory");

                            for(int i = 0; i< array.length();i++){
                                JSONObject itemObj = array.getJSONObject(i);
                                Item item = new Item(itemObj.getString("itemID"), itemObj.getString("itemName"), itemObj.getString("price"),itemObj.getString("supplierID"), itemObj.getString("quantity"), itemObj.getString("customDetail"));
                                if(item.getSupplierID().equals(supplier.getUserID())) {
                                    itemList.add(item);
                                }
                            }

                            SupplierInventoryAdapter adapter = new SupplierInventoryAdapter(itemList, getApplicationContext());
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }
}
