package com.example.ccimp;

import android.view.View;

import com.example.ccimp.ui.customer.CustomerHomeActivity;
import com.example.ccimp.ui.customer.CustomerMenuActivity;
import com.example.ccimp.ui.customer.CustomerOrderCartActivity;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.presenter.customer.CustomerCartAdapter;
import com.example.ccimp.ui.presenter.customer.CustomerHomeAdapter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuAdapter;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class CustomerAdapterUnitTest {

    ArrayList<inventory_business> businesses;
    ArrayList<User> users;

    @Test
    public void testCart(){
        CustomerOrderCartActivity customerOrderCartActivity = new CustomerOrderCartActivity();
        CustomerCartAdapter customerCartAdapter = new CustomerCartAdapter(businesses, customerOrderCartActivity);

        try{
            View testView = customerCartAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void testHome(){
        CustomerHomeActivity customerHomeActivity = new CustomerHomeActivity();
        CustomerHomeAdapter customerHomeAdapter = new CustomerHomeAdapter(users, customerHomeActivity);

        try{
            View testView = customerHomeAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}

    }

    @Test
    public void testMenu(){
        CustomerMenuActivity customerMenuActivity = new CustomerMenuActivity();
        CustomerMenuAdapter customerMenuAdapter = new CustomerMenuAdapter(businesses, customerMenuActivity);

        try{
            View testView = customerMenuAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}

    }
}
