package com.example.ccimp.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.presenter.customer.CustomerMenuAdapter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuPresenter;
import com.example.ccimp.ui.presenter.supplier.SupplierCurrentRequestAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerMenuActivity extends AppCompatActivity implements CustomerMenuInterface.CustomerMenuView{

    Button btnCart;
    ListView businessMenuListView;
    private String businessID;
    private User customer;
    ArrayList<inventory_business> selectedInventoryBusiness;
    BottomNavigationView navigation;
    private CustomerMenuAdapter customerMenuAdapter;
    private CustomerMenuInterface.CustomerMenuPresenter customerMenuPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);

        final Intent intent = getIntent();
        businessID = intent.getParcelableExtra("businessID");
        customer = intent.getParcelableExtra("customer");

        //customerMenuPresenter = new CustomerMenuPresenter(this, businessID);

        btnCart = findViewById(R.id.btnCart);
        navigation = findViewById(R.id.customerNavigation);
        businessMenuListView = findViewById(R.id.businessmenulist);

       // customerMenuPresenter.onViewCreate();

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(CustomerMenuActivity.this, CustomerOrderCartActivity.class);
                cart.putExtra("businessID", businessID);
                cart.putExtra("customer", customer);
                startActivity(cart);
            }
        });


        businessMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inventory_business  item = (inventory_business) parent.getItemAtPosition(position);
                addItemToCart(item);
            }
        });

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return callCustomerNavigation(item);
            }

        });

    }

    @Override
    public boolean callCustomerNavigation(MenuItem customerMenuItem) {
        switch (customerMenuItem.getItemId()) {
            case R.id.navigation_home:
                Intent c = new Intent(CustomerMenuActivity.this, CustomerHomeActivity.class);
                c.putExtra("customer", customer);
                startActivity(c);
                break;
            case R.id.navigation_customer_order:
                Intent a = new Intent(CustomerMenuActivity.this, CustomerOrdersActivity.class);
                a.putExtra("customer", customer);
                startActivity(a);
                break;
            case R.id.navigation_customer_profile:
                Intent b = new Intent(CustomerMenuActivity.this, CustomerProfileActivity.class);
                b.putExtra("customer", customer);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public String getIntentBusinessID(Intent intent) {
        return null;
    }

    @Override
    public User getIntentCustomerObject(Intent intent) {
        return null;
    }

    @Override
    public void setupInventoryList() {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getBusinessInventory.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try{
//                            JSONObject obj = new JSONObject(response);
//                            JSONArray array = obj.getJSONArray("businessInventory");
//                            for(int i = 0; i< array.length();i++){
//                                JSONObject orderObj = array.getJSONObject(i);
//                                inventory_business item = new inventory_business(orderObj.getString("businessName"),orderObj.getString("requestID"),  orderObj.getString("supplierID"), orderObj.getString("businessID"), orderObj.getString("price"), orderObj.getString("needByDate"), orderObj.getString("requestDate"), orderObj.getString("status"));
//                                if(item){
//                                    requestList.add(businessRequest);
//                                    for(int j = 0 ; j < values.length; j++) {
//                                        if(values[j] == null) {
//                                            values[j] = businessRequest;
//                                            break;
//                                        }
//                                    }
//                                }
//                            }
//
//                            SupplierCurrentRequestAdapter adapter = new SupplierCurrentRequestAdapter(requestList, getApplicationContext());
//                            requestListView.setAdapter(adapter);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//
//        };
//        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }

    public void addItemToCart(inventory_business item){
        selectedInventoryBusiness.add(item);
    }
}
