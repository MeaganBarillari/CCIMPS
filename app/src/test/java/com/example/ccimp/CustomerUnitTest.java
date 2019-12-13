package com.example.ccimp;

import com.example.ccimp.ui.customer.CustomerHomeActivity;
import com.example.ccimp.ui.customer.CustomerMenuActivity;
import com.example.ccimp.ui.customer.CustomerProfileActivity;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerProfileInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.customer.CustomerHomePresenter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuPresenter;
import com.example.ccimp.ui.presenter.customer.CustomerProfilePresenter;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CustomerUnitTest {

    private User user = new User("123", "customer", "customer@gmail.com", "123", "Customer", "2533205453", "123 W Wash");



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

        //User testUser = customerHomePresenter.getCustomer("business@ccimp.com");
        //ArrayList<Order> testOrders = customerHomePresenter.getCurrentOrders("12");
        assertNotNull(customerHomePresenter);
        assertNotNull(customerHomeView);
        //assertNotNull(testUser);
        //assertNotNull(testOrders);
    }

    @Test
    public void testMenu(){
        CustomerMenuInterface.CustomerMenuView customerMenuView = new CustomerMenuActivity();
        CustomerMenuPresenter customerMenuPresenter = new CustomerMenuPresenter(customerMenuView, "customer ID");

        assertNotNull(customerMenuView);
        assertNotNull(customerMenuPresenter);
    }



}
