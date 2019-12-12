package com.example.ccimp.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.customer.CustomerHomeInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.customer.CustomerHomeAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierCurrentRequestAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.ccimp.ui.presenter.customer.CustomerHomePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerHomeActivity extends AppCompatActivity implements CustomerHomeInterface.CustomerHomeView {
    ListView businessListView;
    private User customer;
    ArrayList<User> businessList;
    BottomNavigationView navigation;
    private CustomerHomeAdapter customerHomeAdapter;
    private CustomerHomeInterface.CustomerHomePresenter customerHomePresenter;
    User[] values = new User[10000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        Intent intent = getIntent();
        customer = intent.getParcelableExtra("customer");

        //customerHomePresenter = new CustomerHomePresenter(this, customerEmail);
        navigation = findViewById(R.id.customerNavigation);
        businessListView = findViewById(R.id.businesslist);
        //customerHomePresenter.onViewCreate();
        businessList = new ArrayList<>();
        setupBusinessList();

        businessListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomerHomeActivity.this, CustomerMenuActivity.class);
                intent.putExtra("businessID", values[position].getUserID());
                intent.putExtra("customer", customer);
                startActivity(intent);
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
    public boolean callCustomerNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.navigation_home:
                break;
            case R.id.navigation_customer_order:
                Intent a = new Intent(CustomerHomeActivity.this, CustomerOrdersActivity.class);
                a.putExtra("customer", customer);
                startActivity(a);
                break;
            case R.id.navigation_customer_profile:
                Intent b = new Intent(CustomerHomeActivity.this, CustomerProfileActivity.class);
                b.putExtra("customer", customer);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public String getIntentData(Intent intent) {
        return null;
    }

    @Override
    public void setCustomerUser(User customer) {
    }

    @Override
    public void setupBusinessList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getBusinessUsers.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("business");
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);
                                User user = new User(orderObj.getString("userId"),orderObj.getString("username"),  orderObj.getString("email"), orderObj.getString("password"), orderObj.getString("type"), orderObj.getString("phone"), orderObj.getString("address"));
                                businessList.add(user);
                                values[i] = user;

                            }

                            CustomerHomeAdapter adapter = new CustomerHomeAdapter(businessList, getApplicationContext());
                            businessListView.setAdapter(adapter);
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
