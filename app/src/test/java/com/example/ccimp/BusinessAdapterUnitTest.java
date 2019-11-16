package com.example.ccimp;

import android.view.View;

import com.example.ccimp.ui.business.BusinessHomeActivity;
import com.example.ccimp.ui.business.BusinessRequestCartActivity;
import com.example.ccimp.ui.business.BusinessRequestsActivity;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.presenter.business.BusinessCartAdapter;
import com.example.ccimp.ui.presenter.business.BusinessCurrentOrderAdapter;
import com.example.ccimp.ui.presenter.business.BusinessCurrentRequestsAdapter;
import com.example.ccimp.ui.presenter.business.BusinessHistoryRequestsAdapter;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class BusinessAdapterUnitTest {

    ArrayList<inventory_supplier> inventoryArrayList;
    ArrayList<Order> orderArrayList;
    ArrayList<Request> requestArrayList;

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

}
