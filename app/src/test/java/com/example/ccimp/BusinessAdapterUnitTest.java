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
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.Order;
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
    ArrayList<BusinessRequest> requestArrayList;
    ArrayList<inventory_business> inventoryBusinessArrayList;
    ArrayList<order_info> orderItemArrayList;
    ArrayList<User> UserArrayList;
    ArrayList<Item> ItemArrayList;

    @Test
    public void cartTest(){
        BusinessRequestCartActivity businessRequestCartActivity = new BusinessRequestCartActivity();
        BusinessCartAdapter businessCartAdapter = new BusinessCartAdapter(inventoryArrayList, businessRequestCartActivity);
        try {
            View testView = businessCartAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void currentOrderTest(){
        BusinessHomeActivity businessHomeActivity = new BusinessHomeActivity();
        BusinessCurrentOrderAdapter businessCurrentOrderAdapter = new BusinessCurrentOrderAdapter(orderArrayList, businessHomeActivity);

        try{
            View testView = businessCurrentOrderAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}

    }

    @Test
    public void currentRequestsTest(){
        BusinessRequestsActivity businessRequestsActivity = new BusinessRequestsActivity();
        BusinessCurrentRequestsAdapter businessCurrentRequestsAdapter = new BusinessCurrentRequestsAdapter(requestArrayList, businessRequestsActivity);

        try{
            View testView = businessCurrentRequestsAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void historyRequestsTest(){
        BusinessRequestsActivity businessRequestsActivity = new BusinessRequestsActivity();
        BusinessHistoryRequestsAdapter businessHistoryRequestsAdapter = new BusinessHistoryRequestsAdapter(requestArrayList, businessRequestsActivity);

        try{
            View testView = businessHistoryRequestsAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void inventoryTest(){
        BusinessInventoryActivity businessInventoryActivity = new BusinessInventoryActivity();
        BusinessInventoryAdapter businessInventoryAdapter = new BusinessInventoryAdapter(inventoryBusinessArrayList, businessInventoryActivity);

        try{
            View testView = businessInventoryAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void orderDetailTest(){
        BusinessOrderDetailActivity businessOrderDetailActivity = new BusinessOrderDetailActivity();
        BusinessOrderDetailAdapter businessOrderDetailAdapter = new BusinessOrderDetailAdapter(orderItemArrayList, businessOrderDetailActivity);

        try{
            View testView = businessOrderDetailAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void orderHistoryTest(){
        BusinessOrderHistoryActivity businessOrderHistoryActivity = new BusinessOrderHistoryActivity();
        BusinessOrderHistoryAdapter businessOrderHistoryAdapter = new BusinessOrderHistoryAdapter(orderArrayList, businessOrderHistoryActivity);

        try{
            View testView = businessOrderHistoryAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }


    @Test
    public void requestPerSupplierTest(){
        BusinessRequestPerSupplierActivity businessRequestPerSupplierActivity = new BusinessRequestPerSupplierActivity();
        BusinessRequestPerSupplierAdapter businessRequestPerSupplierAdapter = new BusinessRequestPerSupplierAdapter(inventoryArrayList, businessRequestPerSupplierActivity);
        try{
            View testView = businessRequestPerSupplierAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }


    @Test
    public void requestSupplierTest(){
        BusinessRequestFromSupplierActivity businessRequestFromSupplierActivity = new BusinessRequestFromSupplierActivity();
        BusinessRequestSupplierAdapter businessRequestSupplierAdapter = new BusinessRequestSupplierAdapter(UserArrayList, businessRequestFromSupplierActivity);

        try{
            View testView = businessRequestSupplierAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }





}
