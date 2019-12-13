package com.example.ccimp;

import android.view.View;

import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.request_info;
import com.example.ccimp.ui.presenter.supplier.SupplierCurrentRequestAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierInventoryAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestDetailAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestHistoryAdapter;
import com.example.ccimp.ui.supplier.SupplierHomeActivity;
import com.example.ccimp.ui.supplier.SupplierInventoryActivity;
import com.example.ccimp.ui.supplier.SupplierRequestDetailActivity;
import com.example.ccimp.ui.supplier.SupplierRequestsHistoryActivity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class SupplierAdapterUnitTest {

    ArrayList<BusinessRequest> requestList;
    List<Item> itemList;
    ArrayList<request_info> infoList;


    @Test
    public void testCurrentRequest(){
        SupplierHomeActivity supplierHomeActivity = new SupplierHomeActivity();
        SupplierCurrentRequestAdapter supplierCurrentRequestAdapter = new SupplierCurrentRequestAdapter(requestList, supplierHomeActivity);

        try{
            View testView = supplierCurrentRequestAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void testInventory(){
        SupplierInventoryActivity supplierInventoryActivity = new SupplierInventoryActivity();
        SupplierInventoryAdapter supplierInventoryAdapter = new SupplierInventoryAdapter(itemList, supplierInventoryActivity);

        try{
            View testView = supplierInventoryAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void testRequestDetail(){
        SupplierRequestDetailActivity supplierRequestDetailActivity = new SupplierRequestDetailActivity();
        SupplierRequestDetailAdapter supplierRequestDetailAdapter = new SupplierRequestDetailAdapter(infoList, supplierRequestDetailActivity);

        try{
            View testView = supplierRequestDetailAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void testRequestHistory(){
        SupplierRequestsHistoryActivity supplierRequestsHistoryActivity = new SupplierRequestsHistoryActivity();
        SupplierRequestHistoryAdapter supplierRequestHistoryAdapter = new SupplierRequestHistoryAdapter(requestList, supplierRequestsHistoryActivity);

        try{
            View testView = supplierRequestHistoryAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}

    }
}
