package com.example.ccimp.ui.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.customer.CustomerMenuActivity;
import com.example.ccimp.ui.customer.CustomerOrderCartActivity;
import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.business.BusinessRequestPerSupplierInterface;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.presenter.business.BusinessInventoryAdapter;
import com.example.ccimp.ui.presenter.business.BusinessRequestPerSupplierAdapter;
import com.example.ccimp.ui.presenter.business.BusinessRequestPerSupplierPresenter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class BusinessRequestPerSupplierActivity extends AppCompatActivity implements BusinessRequestPerSupplierInterface.BusinessRequestPerSupplierView{
    Button btnBack, btnCart, btnFeedback;
    ListView listView;
    private User business;
    private String supplierID, supplierName;
    ArrayList<inventory_supplier> selectedInventorySupplier, cart;
    BottomNavigationView navigation;
    private BusinessRequestPerSupplierAdapter businessRequestPerSupplierAdapter;
    private BusinessRequestPerSupplierInterface.BusinessRequestPerSupplierPresenter businessRequestPerSupplierPresenter;
    private inventory_supplier[] values = new inventory_supplier[10000];

    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_per_supplier);

        price = 0;

        final Intent intent = getIntent();
        supplierID = intent.getStringExtra("supplierID");
        business = intent.getParcelableExtra("business");
        supplierName = intent.getStringExtra("supplierName");

        btnBack = findViewById(R.id.btBack);
        btnCart = findViewById(R.id.btCart);
        btnFeedback = findViewById(R.id.btFeedback);
        listView = findViewById(R.id.suppliermenu);

        selectedInventorySupplier = new ArrayList<>();
        cart = new ArrayList<>();
        setupInventoryList();


        navigation = findViewById(R.id.businessNavigation);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessRequestPerSupplierActivity.this, BusinessRequestFromSupplierActivity.class));
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartI = new Intent(BusinessRequestPerSupplierActivity.this, BusinessRequestCartActivity.class);
                cartI.putExtra("suppplierID", supplierID);
                cartI.putExtra("business", business);
                cartI.putExtra("supplierName", supplierName);
                cartI.putExtra("price" , Integer.toString(price));
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable) cart);
                cartI.putExtra("BUNDLE",args);
                startActivity(cartI);
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inventory_supplier  item = (inventory_supplier) parent.getItemAtPosition(position);
                Toast toast=Toast. makeText(getApplicationContext(),"Item " + item.getItemName() + " added to cart",Toast. LENGTH_SHORT);
                toast.show();
                cart.add(item);
                price = price + Integer.valueOf(item.getPrice());
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
            case R.id.navigation_home:
                Intent c = new Intent(BusinessRequestPerSupplierActivity.this, BusinessHomeActivity.class);
                c.putExtra("business", business);
                startActivity(c);
                break;
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessRequestPerSupplierActivity.this, BusinessRequestsActivity.class);
                a.putExtra("business", business);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessRequestPerSupplierActivity.this, BusinessInventoryActivity.class);
                b.putExtra("business", business);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessRequestPerSupplierActivity.this, BusinessProfileActivity.class);
                d.putExtra("business", business);
                startActivity(d);
                break;
        }
        return false;
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
                                JSONObject orderObj = array.getJSONObject(i);
                                inventory_supplier item = new inventory_supplier(orderObj.getString("itemID"),orderObj.getString("supplierID"),  orderObj.getString("itemName"), orderObj.getString("price"), orderObj.getString("quantity"));

                                if(item.getSupplierID().equals(supplierID)){
                                    selectedInventorySupplier.add(item);

                                    for(int j = 0 ; j < values.length; j++) {
                                        if(values[j] == null) {
                                            values[j] = item;
                                            break;
                                        }
                                    }
                                }
                            }

                            BusinessRequestPerSupplierAdapter adapter = new BusinessRequestPerSupplierAdapter(selectedInventorySupplier, getApplicationContext());
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
