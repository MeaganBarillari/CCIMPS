package com.example.ccimp;

import com.example.ccimp.ui.business.BusinessHomeActivity;
import com.example.ccimp.ui.business.BusinessInventoryActivity;
import com.example.ccimp.ui.business.BusinessOrderDetailActivity;
import com.example.ccimp.ui.business.BusinessProfileActivity;
import com.example.ccimp.ui.business.BusinessRequestCartActivity;
import com.example.ccimp.ui.interfaces.business.BusinessCartInterface;
import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.business.BusinessProfileInterface;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.presenter.business.BusinessCartPresenter;
import com.example.ccimp.ui.presenter.business.BusinessHomePresenter;
import com.example.ccimp.ui.presenter.business.BusinessInventoryPresenter;
import com.example.ccimp.ui.presenter.business.BusinessOrderDetailPresenter;
import com.example.ccimp.ui.presenter.business.BusinessProfilePresenter;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class BusinessUnitTest {

    private User user = new User("123", "business", "business@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");


    @Test
    public void testBusinessDetail(){
       BusinessOrderDetailActivity businessOrderDetailView = new BusinessOrderDetailActivity();
        BusinessOrderDetailPresenter businessOrderDetailPresenter = new BusinessOrderDetailPresenter(businessOrderDetailView, "businessID");
        businessOrderDetailPresenter.getBusiness("business Email");
    }

    @Test
    public void testCart(){
        BusinessCartInterface.BusinessCartView businessCartView = new BusinessRequestCartActivity();
        BusinessCartPresenter businessCartPresenter = new BusinessCartPresenter(businessCartView, user.getUserID());

        assertNotNull(businessCartPresenter);
        assertNotNull(businessCartView);

    }

    @Test
    public void testProfile(){
        BusinessProfileInterface.BusinessProfileView businessProfileView = new BusinessProfileActivity();
        BusinessProfilePresenter businessProfilePresenter = new BusinessProfilePresenter(businessProfileView, user.getUserID());

        assertNotNull(businessProfilePresenter);
        assertNotNull(businessProfileView);
    }

    @Test
    public void testHome(){
        BusinessHomeActivity businessHomeView = new BusinessHomeActivity();
        BusinessHomePresenter businessHomePresenter = new BusinessHomePresenter(businessHomeView, "business@ccimp.com");

        User testUser = businessHomePresenter.getBusiness("business@ccimp.com");
        ArrayList<Order> testOrders = businessHomePresenter.getCurrentOrders("12");
        assertNotNull(businessHomePresenter);
        assertNotNull(businessHomeView);
        assertNotNull(testUser);
        assertNotNull(testOrders);
    }

    @Test
    public void testInventory(){
        BusinessInventoryInterface.BusinessInventoryView businessInventoryActivity = new BusinessInventoryActivity();
        BusinessInventoryPresenter businessInventoryPresenter = new BusinessInventoryPresenter(businessInventoryActivity, "business ID");

        assertNotNull(businessInventoryActivity);
        assertNotNull(businessInventoryPresenter);
    }

}
