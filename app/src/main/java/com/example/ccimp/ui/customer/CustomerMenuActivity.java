package com.example.ccimp.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import java.io.Serializable;

public class CustomerMenuActivity extends AppCompatActivity implements CustomerMenuInterface.CustomerMenuView{

    Button btnCart;
    int price;
    ListView businessMenuListView;
    private String businessID, businessName;
    private User customer;
    ArrayList<inventory_business> selectedInventoryBusiness, cart;
    BottomNavigationView navigation;
    private CustomerMenuAdapter customerMenuAdapter;
    private CustomerMenuInterface.CustomerMenuPresenter customerMenuPresenter;
    private inventory_business[] values = new inventory_business[10000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);

        price = 0;

        final Intent intent = getIntent();
        businessID = intent.getStringExtra("businessID");
        System.out.println(businessID);
        customer = intent.getParcelableExtra("customer");
        businessName = intent.getStringExtra("businessname");

        //customerMenuPresenter = new CustomerMenuPresenter(this, businessID);

        btnCart = findViewById(R.id.btnCart);
        navigation = findViewById(R.id.customerNavigation);
        businessMenuListView = findViewById(R.id.businessmenulist);
        selectedInventoryBusiness = new ArrayList<>();
        cart = new ArrayList<>();
        setupInventoryList();

       // customerMenuPresenter.onViewCreate();

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartI = new Intent(CustomerMenuActivity.this, CustomerOrderCartActivity.class);
                cartI.putExtra("businessID", businessID);
                cartI.putExtra("customer", customer);
                cartI.putExtra("businessname", businessName);
                cartI.putExtra("price" , Integer.toString(price));
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable) cart);
                cartI.putExtra("BUNDLE",args);
                startActivity(cartI);
            }
        });


        businessMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inventory_business  item = (inventory_business) parent.getItemAtPosition(position);
                Toast toast=Toast. makeText(getApplicationContext(),"Item " + item.getItemName() + " added to cart",Toast. LENGTH_SHORT);
                toast.show();
                cart.add(item);
                price = price + Integer.valueOf(item.getPrice());
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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getBusinessInventory.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("businessInventory");
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);
                                inventory_business item = new inventory_business(orderObj.getString("name"),orderObj.getString("businessID"),  orderObj.getString("itemID"), orderObj.getString("quantity"), orderObj.getString("available quantity"), orderObj.getString("price"));
                                System.out.println(item.getBusinessID());

                                if(item.getBusinessID().equals(businessID)){
                                    System.out.println(businessID);
                                    selectedInventoryBusiness.add(item);

                                    for(int j = 0 ; j < values.length; j++) {
                                        if(values[j] == null) {
                                            values[j] = item;
                                            break;
                                        }
                                    }
                                }
                            }

                            CustomerMenuAdapter adapter = new CustomerMenuAdapter(selectedInventoryBusiness, getApplicationContext());
                            businessMenuListView.setAdapter(adapter);
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

    public void addItemToCart(inventory_business item){
        selectedInventoryBusiness.add(item);
    }
}
