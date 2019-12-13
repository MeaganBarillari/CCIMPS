package com.example.ccimp.ui.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ccimp.R;
import com.example.ccimp.ui.customer.CustomerHomeActivity;
import com.example.ccimp.ui.customer.CustomerOrderCartActivity;
import com.example.ccimp.ui.customer.CustomerOrdersActivity;
import com.example.ccimp.ui.customer.CustomerProfileActivity;
import com.example.ccimp.ui.interfaces.business.BusinessCartInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.model.request_info;
import com.example.ccimp.ui.presenter.business.BusinessCartAdapter;

import com.example.ccimp.ui.presenter.customer.CustomerCartAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BusinessRequestCartActivity extends AppCompatActivity {

    ListView listView;
    TextView orderPrice;
    request_info request1 = new request_info("123", "321", "3", "1000");
    private User user = new User("123", "business", "business@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    BottomNavigationView navigation;
    private BusinessCartAdapter businessCartAdapter;
    private BusinessCartInterface.BusinessCartPresenter businessCartPresenter;
    ArrayList<inventory_supplier> local;
    User business;
    Button btnBusinessRequest;
    TextView supplierName, totalPrice;

    Button btnsendrequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_request_cart);

        local = new ArrayList<>();

        String value = getIntent().getStringExtra("supplierName");
        final String tPrice = getIntent().getStringExtra("price");

        Intent i = getIntent();
        Bundle args = getIntent().getBundleExtra("BUNDLE");
        final ArrayList<inventory_supplier> object = (ArrayList<inventory_supplier>) args.getSerializable("ARRAYLIST");
        local.addAll(object);


        Intent intent = getIntent();
        business = intent.getParcelableExtra("business");

        btnBusinessRequest = findViewById(R.id.fab_customer_order);
        //supplierName = findViewById(R.id.BusinessName);
        totalPrice = findViewById(R.id.order_price);
        final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        //supplierName.setText(value);
        totalPrice.setText(tPrice);
        final String supplierID = intent.getStringExtra("supplierID");

//        btnBusinessRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CustomerOrderCartActivity.BackgroundProcess bgProcess = new CustomerOrderCartActivity.BackgroundProcess(CustomerOrderCartActivity.this);
//                bgProcess.execute("order",currentDate, businessID, customer.getUserID(), customer.getUsername(), "Accept", tPrice);
//
//            }
//        });
        listView = findViewById(R.id.business_checkout_listview);
        final BusinessCartAdapter adapter = new BusinessCartAdapter(local, getApplicationContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int tota = Integer.valueOf(tPrice);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (local.size() != 0){
                    tota -= Integer.valueOf(local.get(position).getPrice());
                }
                else{
                    tota = 0;
                }
                local.remove(position);
                totalPrice.setText(Integer.toString(tota));
                listView.setAdapter(adapter);

            }
        });
        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessRequestCartActivity.this, BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessRequestCartActivity.this,BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessRequestCartActivity.this, BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessRequestCartActivity.this, BusinessProfileActivity.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });

//        businessCartPresenter = new BusinessCartPresenter(this, user.getUserID());
//
//        orderPrice = findViewById(R.id.order_price);
//        orderPrice.setText(request1.getTotalPrice());
//        btnsendrequest = findViewById(R.id.fab_customer_order);
//        navigation = findViewById(R.id.businessNavigation);
//        listView = findViewById(R.id.business_checkout_listview);
//
//        businessCartPresenter.onViewCreate();
//
//        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                return callSupplierNavigation(item);
//            }
//
//        });
//
//        btnsendrequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(BusinessRequestCartActivity.this, BusinessRequestsActivity.class));
//            }
//        });

    }



}