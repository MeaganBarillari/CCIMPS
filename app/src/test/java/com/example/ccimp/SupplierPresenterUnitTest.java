package com.example.ccimp;

import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierProfileInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierRequestDetailInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.User;
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

public class SupplierPresenterUnitTest {

    private User user = new User("123", "customer", "customer@gmail.com", "123", "Customer", "2533205453", "123 W Wash");
    private BusinessRequest businessRequest = new BusinessRequest("a","a", "a", "a", "a", "a", "a", "a");



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
        ArrayList<BusinessRequest> testRequests = supplierHomePresenter.getCurrentRequest("12");
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



        SupplierRequestDetailPresenter supplierRequestDetailPresenter = new SupplierRequestDetailPresenter(supplierRequestDetailView, businessRequest);
        supplierRequestDetailPresenter.getSupplier("supplier Email");
    }


}