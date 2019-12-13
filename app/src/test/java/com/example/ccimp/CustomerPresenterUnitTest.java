package com.example.ccimp;

import com.example.ccimp.ui.customer.CustomerHomeActivity;
import com.example.ccimp.ui.customer.CustomerMenuActivity;
import com.example.ccimp.ui.customer.CustomerProfileActivity;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerProfileInterface;
import com.example.ccimp.ui.presenter.customer.CustomerHomePresenter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuPresenter;
import com.example.ccimp.ui.presenter.customer.CustomerProfilePresenter;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CustomerPresenterUnitTest {

    @Test
    public void testProfile(){
        CustomerProfileInterface.CustomerProfileView customerProfileView = new CustomerProfileActivity();
        CustomerProfileInterface.CustomerProfilePresenter customerProfilePresenter = new CustomerProfilePresenter(customerProfileView);

        assertNotNull(customerProfilePresenter);
        assertNotNull(customerProfileView);
    }

    @Test
    public void testHome(){
        CustomerHomeActivity customerHomeView = new CustomerHomeActivity();
        CustomerHomePresenter customerHomePresenter = new CustomerHomePresenter(customerHomeView, "customer@ccimp.com");

        assertNotNull(customerHomePresenter);
        assertNotNull(customerHomeView);
    }

    @Test
    public void testMenu(){
        CustomerMenuInterface.CustomerMenuView customerMenuView = new CustomerMenuActivity();
        CustomerMenuPresenter customerMenuPresenter = new CustomerMenuPresenter(customerMenuView, "customer ID");

        assertNotNull(customerMenuView);
        assertNotNull(customerMenuPresenter);
    }



}
