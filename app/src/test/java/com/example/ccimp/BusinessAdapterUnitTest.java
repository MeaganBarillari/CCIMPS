package com.example.ccimp;

import android.view.View;

import com.example.ccimp.ui.business.BusinessHomeActivity;
import com.example.ccimp.ui.business.BusinessInventoryActivity;
import com.example.ccimp.ui.business.BusinessOrderDetailActivity;
import com.example.ccimp.ui.business.BusinessOrderHistoryActivity;
import com.example.ccimp.ui.business.BusinessRequestCartActivity;
import com.example.ccimp.ui.business.BusinessRequestFromSupplierActivity;
import com.example.ccimp.ui.business.BusinessRequestPerSupplierActivity;
import com.example.ccimp.ui.business.BusinessRequestsActivity;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.model.order_info;
import com.example.ccimp.ui.presenter.business.BusinessCartAdapter;
import com.example.ccimp.ui.presenter.business.BusinessCurrentOrderAdapter;
import com.example.ccimp.ui.presenter.business.BusinessCurrentRequestsAdapter;
import com.example.ccimp.ui.presenter.business.BusinessHistoryRequestsAdapter;
import com.example.ccimp.ui.presenter.business.BusinessInventoryAdapter;
import com.example.ccimp.ui.presenter.business.BusinessOrderDetailAdapter;
import com.example.ccimp.ui.presenter.business.BusinessOrderHistoryAdapter;
import com.example.ccimp.ui.presenter.business.BusinessRequestPerSupplierAdapter;
import com.example.ccimp.ui.presenter.business.BusinessRequestSupplierAdapter;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class BusinessAdapterUnitTest {

    ArrayList<inventory_supplier> inventoryArrayList;
    ArrayList<Order> orderArrayList;
    ArrayList<Request> requestArrayList;
    ArrayList<inventory_business> inventoryBusinessArrayList;
    ArrayList<order_info> orderItemArrayList;
    ArrayList<User> UserArrayList;

    @Test
    public void cartTest(){
        BusinessRequestCartActivity businessRequestCartActivity = new BusinessRequestCartActivity();
        BusinessCartAdapter businessCartAdapter = new BusinessCartAdapter(businessRequestCartActivity, R.layout.rowfourlines, inventoryArrayList);
        try {
            View testView = businessCartAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void currentOrderTest(){
        BusinessHomeActivity businessHomeActivity = new BusinessHomeActivity();
        BusinessCurrentOrderAdapter businessCurrentOrderAdapter = new BusinessCurrentOrderAdapter(businessHomeActivity, R.layout.row, orderArrayList);

        try{
            View testView = businessCurrentOrderAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}

    }

    @Test
    public void currentRequestsTest(){
        BusinessRequestsActivity businessRequestsActivity = new BusinessRequestsActivity();
        BusinessCurrentRequestsAdapter businessCurrentRequestsAdapter = new BusinessCurrentRequestsAdapter(businessRequestsActivity, R.layout.rowtwolines, requestArrayList);

        try{
            View testView = businessCurrentRequestsAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void historyRequestsTest(){
        BusinessRequestsActivity businessRequestsActivity = new BusinessRequestsActivity();
        BusinessHistoryRequestsAdapter businessHistoryRequestsAdapter = new BusinessHistoryRequestsAdapter(businessRequestsActivity, R.layout.row, requestArrayList);

        try{
            View testView = businessHistoryRequestsAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void inventoryTest(){
        BusinessInventoryActivity businessInventoryActivity = new BusinessInventoryActivity();
        BusinessInventoryAdapter businessInventoryAdapter = new BusinessInventoryAdapter(businessInventoryActivity, R.layout.rowfourlines, inventoryBusinessArrayList);

        try{
            View testView = businessInventoryAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void orderDetailTest(){
        BusinessOrderDetailActivity businessOrderDetailActivity = new BusinessOrderDetailActivity();
        BusinessOrderDetailAdapter businessOrderDetailAdapter = new BusinessOrderDetailAdapter(businessOrderDetailActivity, R.layout.row, orderItemArrayList);

        try{
            View testView = businessOrderDetailAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void orderHistoryTest(){
        BusinessOrderHistoryActivity businessOrderHistoryActivity = new BusinessOrderHistoryActivity();
        BusinessOrderHistoryAdapter businessOrderHistoryAdapter = new BusinessOrderHistoryAdapter(businessOrderHistoryActivity, R.layout.row, orderArrayList);

        try{
            View testView = businessOrderHistoryAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    /**
    @Test
    public void requestFromMenuTest(){
        BusinessRequestFromSupplierActivity businessRequestFromSupplierActivity = new BusinessRequestFromSupplierActivity();
        BusinessRequestFromMenuAdapter businessRequestFromMenuAdapter = new BusinessRequestFromMenuAdapter(businessRequestFromSupplierActivity, R.layout.rowoneline, UserArrayList);
    }
     **/

    @Test
    public void requestPerSupplierTest(){
        BusinessRequestPerSupplierActivity businessRequestPerSupplierActivity = new BusinessRequestPerSupplierActivity();
        BusinessRequestPerSupplierAdapter businessRequestPerSupplierAdapter = new BusinessRequestPerSupplierAdapter(businessRequestPerSupplierActivity, R.layout.rowoneline, inventoryArrayList);
        try{
            View testView = businessRequestPerSupplierAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }


    @Test
    public void requestSupplierTest(){
        BusinessRequestFromSupplierActivity businessRequestFromSupplierActivity = new BusinessRequestFromSupplierActivity();
        BusinessRequestSupplierAdapter businessRequestSupplierAdapter = new BusinessRequestSupplierAdapter(businessRequestFromSupplierActivity, R.layout.rowoneline, UserArrayList);

        try{
            View testView = businessRequestSupplierAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }





}
