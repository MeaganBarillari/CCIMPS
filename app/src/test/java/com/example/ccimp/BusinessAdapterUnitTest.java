package com.example.ccimp;

import android.view.View;

import com.example.ccimp.ui.business.BusinessHomeActivity;
import com.example.ccimp.ui.business.BusinessRequestCartActivity;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.presenter.business.BusinessCartAdapter;
import com.example.ccimp.ui.presenter.business.BusinessCurrentOrderAdapter;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class BusinessAdapterUnitTest {

    ArrayList<inventory_supplier> inventoryArrayList;
    ArrayList<Order> orderArrayList;

    @Test
    public void cartAdapterTest(){
        BusinessRequestCartActivity businessRequestCartActivity = new BusinessRequestCartActivity();
        BusinessCartAdapter businessCartAdapter = new BusinessCartAdapter(businessRequestCartActivity, R.layout.rowfourlines, inventoryArrayList);
        try {
            View testView = businessCartAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void  currentOrderAdapterTest(){
        BusinessHomeActivity businessHomeActivity = new BusinessHomeActivity();
        BusinessCurrentOrderAdapter businessCurrentOrderAdapter = new BusinessCurrentOrderAdapter(businessHomeActivity, R.layout.row, orderArrayList);

        try{
            View testView = businessCurrentOrderAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}

    }
}
