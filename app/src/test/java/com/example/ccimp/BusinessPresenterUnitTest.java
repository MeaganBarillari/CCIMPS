package com.example.ccimp;

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

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class BusinessPresenterUnitTest {

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
        BusinessInventoryInterface.BusinessInventoryView businessInventoryView = new BusinessInventoryActivity();
        BusinessInventoryPresenter businessInventoryPresenter = new BusinessInventoryPresenter(businessInventoryView, "business ID");

        assertNotNull(businessInventoryView);
        assertNotNull(businessInventoryPresenter);
    }

    @Test
    public void testRequestFromSupplier(){
        BusinessRequestFromSupplierInterface.BusinessRequestFromSupplierView businessRequestFromSupplierView = new BusinessRequestFromSupplierActivity();
        BusinessRequestFromSupplierPresenter businessRequestFromSupplierPresenter = new BusinessRequestFromSupplierPresenter(businessRequestFromSupplierView, "12");

        assertNotNull(businessRequestFromSupplierPresenter);
        assertNotNull(businessRequestFromSupplierView);
    }

    @Test
    public void testOrderHistory(){

        BusinessOrderHistoryActivity businessOrderHistoryActivity = new BusinessOrderHistoryActivity();
        BusinessOrderHistoryPresenter businessOrderHistoryPresenter = new BusinessOrderHistoryPresenter(businessOrderHistoryActivity, user);

        businessOrderHistoryPresenter.getOrderHistoryList("1");

        assertNotNull(businessOrderHistoryActivity);
        assertNotNull(businessOrderHistoryPresenter);
    }

    @Test
    public void testRequestPerSupplier(){
        BusinessRequestPerSupplierInterface.BusinessRequestPerSupplierView businessRequestPerSupplierActivity = new BusinessRequestPerSupplierActivity();
        BusinessRequestPerSupplierPresenter businessRequestPerSupplierPresenter = new BusinessRequestPerSupplierPresenter(businessRequestPerSupplierActivity, "12");

        assertNotNull(businessRequestPerSupplierActivity);
        assertNotNull(businessRequestPerSupplierPresenter);
    }

    @Test
    public void testRequest(){
        BusinessRequestsInterface.BusinessRequestsView businessRequestsView = new BusinessRequestsActivity();
        BusinessRequestsPresenter businessRequestsPresenter = new BusinessRequestsPresenter(businessRequestsView, "12");

        assertNotNull(businessRequestsPresenter);
        assertNotNull(businessRequestsView);
    }
}
