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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.presenter.business.BusinessInventoryAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierInventoryAdapter;
import com.example.ccimp.ui.presenter.business.BusinessInventoryPresenter;
import com.example.ccimp.ui.supplier.SupplierHomeActivity;
import com.example.ccimp.ui.supplier.SupplierInventoryActivity;
import com.example.ccimp.ui.supplier.SupplierProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusinessInventoryActivity extends AppCompatActivity implements BusinessInventoryInterface.BusinessInventoryView {

    ArrayList<inventory_business> itemList;
    User business;
    ListView listView;
    BottomNavigationView navigation;
    private BusinessInventoryAdapter businessInventoryAdapter;
    private BusinessInventoryInterface.BusinessInventoryPresenter businessInventoryPresenter;

    Button btnRequestItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_inventory);

        Intent intent = getIntent();
        business = intent.getParcelableExtra("business");


        navigation = findViewById(R.id.businessNavigation);

        btnRequestItems = findViewById(R.id.requestItem);

        btnRequestItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BusinessInventoryActivity.this, BusinessRequestFromSupplierActivity.class);
                intent1.putExtra("business", business);
                startActivity(intent1);
            }
        });

        listView = findViewById(R.id.listitem);
        itemList = new ArrayList<>();
        setupInventoryList();

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
                Intent c = new Intent(BusinessInventoryActivity.this, BusinessHomeActivity.class);
                c.putExtra("business", business);
                startActivity(c);
                break;
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessInventoryActivity.this, BusinessRequestsActivity.class);
                a.putExtra("business", business);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessInventoryActivity.this,BusinessInventoryActivity.class);
                b.putExtra("business", business);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessInventoryActivity.this, BusinessProfileActivity.class);
                d.putExtra("business", business);
                startActivity(d);
                break;
        }
        return false;
    }

    @Override
    public void setupInventoryList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getBusinessInventory.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("businessInventory");

                            for(int i = 0; i< array.length();i++){
                                JSONObject itemObj = array.getJSONObject(i);
                                inventory_business item = new inventory_business(itemObj.getString("name"), itemObj.getString("businessID"), itemObj.getString("itemID"),itemObj.getString("quantity"), itemObj.getString("available quantity"), itemObj.getString("price"), itemObj.getString("customDetail"));
                                if(item.getBusinessID().equals(business.getUserID())) {
                                    itemList.add(item);
                                }
                            }

                            BusinessInventoryAdapter adapter = new BusinessInventoryAdapter(itemList, getApplicationContext());
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
