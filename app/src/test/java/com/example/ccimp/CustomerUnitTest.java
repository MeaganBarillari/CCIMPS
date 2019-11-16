package com.example.ccimp;

import com.example.ccimp.ui.customer.CustomerHomeActivity;
import com.example.ccimp.ui.customer.CustomerMenuActivity;
import com.example.ccimp.ui.interfaces.customer.CustomerHomeInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerProfileInterface;
import com.example.ccimp.ui.customer.CustomerProfileActivity;

import com.example.ccimp.ui.business.BusinessHomeActivity;
import com.example.ccimp.ui.business.BusinessInventoryActivity;
import com.example.ccimp.ui.business.BusinessOrderDetailActivity;
import com.example.ccimp.ui.business.BusinessOrderHistoryActivity;
import com.example.ccimp.ui.business.BusinessProfileActivity;
import com.example.ccimp.ui.business.BusinessRequestCartActivity;
import com.example.ccimp.ui.business.BusinessRequestFromSupplierActivity;
import com.example.ccimp.ui.business.BusinessRequestPerSupplierActivity;
import com.example.ccimp.ui.business.BusinessRequestsActivity;
import com.example.ccimp.ui.interfaces.business.BusinessCartInterface;
import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.business.BusinessProfileInterface;
import com.example.ccimp.ui.interfaces.business.BusinessRequestFromSupplierInterface;
import com.example.ccimp.ui.interfaces.business.BusinessRequestPerSupplierInterface;
import com.example.ccimp.ui.interfaces.business.BusinessRequestsInterface;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessCartPresenter;
import com.example.ccimp.ui.presenter.business.BusinessHomePresenter;
import com.example.ccimp.ui.presenter.business.BusinessInventoryPresenter;
import com.example.ccimp.ui.presenter.business.BusinessOrderDetailPresenter;
import com.example.ccimp.ui.presenter.business.BusinessOrderHistoryPresenter;
import com.example.ccimp.ui.presenter.business.BusinessProfilePresenter;
import com.example.ccimp.ui.presenter.business.BusinessRequestFromSupplierPresenter;
import com.example.ccimp.ui.presenter.business.BusinessRequestPerSupplierPresenter;
import com.example.ccimp.ui.presenter.business.BusinessRequestsPresenter;
import com.example.ccimp.ui.presenter.customer.CustomerHomePresenter;
import com.example.ccimp.ui.presenter.customer.CustomerMenuPresenter;
import com.example.ccimp.ui.presenter.customer.CustomerProfilePresenter;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class CustomerUnitTest {

    private User user = new User("123", "customer", "customer@gmail.com", "123", "Customer", "2533205453", "123 W Wash");




    @Test
    public void testProfile(){
        CustomerProfileInterface.CustomerProfileView customerProfileView = new CustomerProfileActivity();
        CustomerProfileInterface.CustomerProfilePresenter customerProfilePresenter = new CustomerProfilePresenter(customerProfileView, user.getUserID());

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
