package com.example.ccimp.ui.business;

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
import com.example.ccimp.ui.interfaces.business.BusinessRequestFromSupplierInterface;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessRequestFromSupplierPresenter;
import com.example.ccimp.ui.presenter.business.BusinessRequestSupplierAdapter;
import com.example.ccimp.ui.presenter.customer.CustomerHomeAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusinessRequestFromSupplierActivity extends AppCompatActivity implements BusinessRequestFromSupplierInterface.BusinessRequestFromSupplierView {

    ArrayList<User> supplierList;
    User[] values = new User[10000];
    ListView supplierListView;
    User business;
    BottomNavigationView navigation;
    private BusinessRequestSupplierAdapter businessRequestSupplierAdapter;
    private BusinessRequestFromSupplierInterface.BusinessRequestFromSupplierPresenter businessRequestFromSupplierPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_from_supplier);

        Intent intent = getIntent();
        business= intent.getParcelableExtra("business");

        navigation = findViewById(R.id.businessNavigation);

        supplierListView = findViewById(R.id.previous_requests_listview);


        supplierList = new ArrayList<>();
        setupSupplierList();

        supplierListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BusinessRequestFromSupplierActivity.this, BusinessRequestPerSupplierActivity.class);
                intent.putExtra("supplierID", values[position].getUserID());
                intent.putExtra("supplierName", values[position].getUsername());
                intent.putExtra("business", business);
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
            case R.id.navigation_home:
                Intent c = new Intent(BusinessRequestFromSupplierActivity.this, BusinessHomeActivity.class);
                c.putExtra("business", business);
                startActivity(c);
                break;
            case R.id.navigation_requests:
                Intent a = new Intent(BusinessRequestFromSupplierActivity.this, BusinessRequestsActivity.class);
                a.putExtra("business", business);
                startActivity(a);
                break;
            case R.id.navigation_inventory:
                Intent b = new Intent(BusinessRequestFromSupplierActivity.this, BusinessInventoryActivity.class);
                b.putExtra("business", business);
                startActivity(b);
                break;
            case R.id.navigation_business_profile:
                Intent d = new Intent(BusinessRequestFromSupplierActivity.this, BusinessProfileActivity.class);
                d.putExtra("business", business);
                startActivity(d);
                break;
        }
        return false;
    }


    @Override
    public void setupSupplierList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getSupplierUser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("supplier");
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);
                                User user = new User(orderObj.getString("userId"),orderObj.getString("username"),  orderObj.getString("email"), orderObj.getString("password"), orderObj.getString("type"), orderObj.getString("phone"), orderObj.getString("address"));
                                supplierList.add(user);
                                values[i] = user;

                            }

                            BusinessRequestSupplierAdapter adapter = new BusinessRequestSupplierAdapter(supplierList, getApplicationContext());
                            supplierListView.setAdapter(adapter);
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
