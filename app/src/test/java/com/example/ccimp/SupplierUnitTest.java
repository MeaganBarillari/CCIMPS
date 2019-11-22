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
import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierProfileInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierRequestDetailInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierRequestHistoryInterface;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.Request;
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
import com.example.ccimp.ui.presenter.supplier.SupplierHomePresenter;
import com.example.ccimp.ui.presenter.supplier.SupplierInventoryPresenter;
import com.example.ccimp.ui.presenter.supplier.SupplierProfilePresenter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestDetailPresenter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestHistoryPresenter;
import com.example.ccimp.ui.supplier.SupplierHomeActivity;
import com.example.ccimp.ui.supplier.SupplierInventoryActivity;
import com.example.ccimp.ui.supplier.SupplierProfileActivity;
import com.example.ccimp.ui.supplier.SupplierRequestDetailActivity;
import com.example.ccimp.ui.supplier.SupplierRequestsHistoryActivity;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class SupplierUnitTest {

    private User user = new User("123", "customer", "customer@gmail.com", "123", "Customer", "2533205453", "123 W Wash");
    private Request request = new Request("a","a", "a", "a", "a", "a", "a", "a");



    @Test
    public void testProfile(){
        SupplierProfileInterface.SupplierProfileView supplierProfileView = new SupplierProfileActivity();
        SupplierProfilePresenter supplierProfilePresenter = new SupplierProfilePresenter(supplierProfileView);

        assertNotNull(supplierProfilePresenter);
        assertNotNull(supplierProfileView);
    }

    @Test
    public void testHome(){
        SupplierHomeActivity supplierHomeView = new SupplierHomeActivity();
        SupplierHomePresenter supplierHomePresenter = new SupplierHomePresenter(supplierHomeView, "supplier@ccimp.com");

        User testUser = supplierHomePresenter.getSupplier("supplier@ccimp.com");
        ArrayList<Request> testRequests = supplierHomePresenter.getCurrentRequest("12");
        assertNotNull(supplierHomePresenter);
        assertNotNull(supplierHomeView);
        assertNotNull(testUser);
        assertNotNull(testRequests);
    }

    @Test
    public void testInventory(){
        SupplierInventoryInterface.SupplierInventoryView supplierInventoryView = new SupplierInventoryActivity();
        SupplierInventoryPresenter supplierInventoryPresenter = new SupplierInventoryPresenter(supplierInventoryView, "supplier ID");

        assertNotNull(supplierInventoryView);
        assertNotNull(supplierInventoryPresenter);
    }

    @Test
    public void testRequestHistory(){

        SupplierRequestsHistoryActivity supplierOrderHistoryActivity = new SupplierRequestsHistoryActivity();
        SupplierRequestHistoryPresenter businessOrderHistoryPresenter = new SupplierRequestHistoryPresenter(supplierOrderHistoryActivity, user);

        businessOrderHistoryPresenter.getRequestHistoryList("1");

        assertNotNull(supplierOrderHistoryActivity);
        assertNotNull(businessOrderHistoryPresenter);
    }

    @Test
    public void testRequestDetail(){
        SupplierRequestDetailInterface.SupplierRequestDetailView supplierRequestDetailView = new SupplierRequestDetailActivity();



        SupplierRequestDetailPresenter supplierRequestDetailPresenter = new SupplierRequestDetailPresenter(supplierRequestDetailView, request);
        supplierRequestDetailPresenter.getSupplier("supplier Email");
    }


}