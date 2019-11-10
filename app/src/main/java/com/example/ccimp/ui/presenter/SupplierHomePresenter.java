package com.example.ccimp.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.R;
import com.example.ccimp.ui.supplier.SupplierHomeActivity;
import com.example.ccimp.ui.supplier.SupplierInventoryActivity;
import com.example.ccimp.ui.supplier.SupplierProfileActivity;
import com.example.ccimp.ui.view.SupplierHomeView;

public class SupplierHomePresenter implements ISupplierHomePresenter{

    SupplierHomeView supplierHomeView;

    public SupplierHomePresenter(SupplierHomeView supplierHomeView){
        this.supplierHomeView = supplierHomeView;
    }
    @Override
    public void viewAllCurrentRequests(int supplierId, Context context) {

    }

    @Override
    public void viewHistory(int supplierID, Context context) {

    }

    @Override
    public void viewRequestDetail(int requestID, Context context) {
        supplierHomeView.SupplierRequestDetail(requestID);
    }

    @Override
    public void handleSupplierNavigation(MenuItem itemID, Context context) {
        supplierHomeView.callSupplierNavigation(itemID);
    }
}
