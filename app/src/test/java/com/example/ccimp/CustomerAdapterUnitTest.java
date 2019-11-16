package com.example.ccimp;

import android.view.View;

import com.example.ccimp.ui.customer.CustomerHomeActivity;
import com.example.ccimp.ui.customer.CustomerMenuActivity;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.presenter.customer.CustomerHomeAdapter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestHistoryAdapter;
import com.example.ccimp.ui.supplier.SupplierRequestsHistoryActivity;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class CustomerAdapterUnitTest {


    ArrayList<User> userArrayList;
    ArrayList<inventory_business> inventoryArrayList;


    @Test
    public void homeTest() {
        CustomerHomeActivity customerHomeActivity = new CustomerHomeActivity();
        CustomerHomeAdapter customerHomeAdapter = new CustomerHomeAdapter(customerHomeActivity, R.layout.row, userArrayList);

        try {
            View testView = customerHomeAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }


    @Test
    public void menuTest() {
        CustomerMenuActivity customerMenuActivity = new CustomerMenuActivity();
        CustomerMenuAdapter customerMenuAdapter = new CustomerMenuAdapter(customerMenuActivity, R.layout.row, inventoryArrayList);

        try {
            View testView = customerMenuAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }


}
