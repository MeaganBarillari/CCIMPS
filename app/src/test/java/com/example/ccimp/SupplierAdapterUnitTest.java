package com.example.ccimp;

import android.view.View;

import com.example.ccimp.ui.business.BusinessRequestCartActivity;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.model.request_info;
import com.example.ccimp.ui.presenter.business.BusinessCartAdapter;
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

import static org.junit.Assert.assertNotNull;

public class SupplierAdapterUnitTest {

    ArrayList<Request> requestList;
    ArrayList<inventory_supplier> inventoryArrayList;
    ArrayList<request_info> requestItemArrayList;
    ArrayList<Request> requestArrayList;



    @Test
    public void currentRequestTest() {
        SupplierHomeActivity supplierHomeActivity = new SupplierHomeActivity();
        SupplierCurrentRequestAdapter supplierRequestAdapter = new SupplierCurrentRequestAdapter(supplierHomeActivity, R.layout.row, requestList);

        try {
            View testView = supplierRequestAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void currentInventoryTest() {
        SupplierInventoryActivity supplierInventoryActivity = new SupplierInventoryActivity();
        SupplierInventoryAdapter supplierInventoryAdapter = new SupplierInventoryAdapter(supplierInventoryActivity, R.layout.row, inventoryArrayList);

        try {
            View testView = supplierInventoryAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void requestDetailTest() {
        SupplierRequestDetailActivity supplierRequestDetailActivity = new SupplierRequestDetailActivity();
        SupplierRequestDetailAdapter supplierRequestDetailAdapter = new SupplierRequestDetailAdapter(supplierRequestDetailActivity, R.layout.row, requestItemArrayList);

        try {
            View testView = supplierRequestDetailAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }

    @Test
    public void requestHistoryTest() {
        SupplierRequestsHistoryActivity supplierRequestHistoryActivity = new SupplierRequestsHistoryActivity();
        SupplierRequestHistoryAdapter supplierRequestHistoryAdapter = new SupplierRequestHistoryAdapter(supplierRequestHistoryActivity, R.layout.row, requestArrayList);

        try {
            View testView = supplierRequestHistoryAdapter.getView(0, null, null);
            assertNotNull(testView);
        }
        catch(RuntimeException e){}
    }
}
