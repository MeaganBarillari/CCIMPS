package com.example.ccimp.ui.interfaces.supplier;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;

public interface SupplierInventoryInterface {
    interface SupplierInventoryPresenter{
        void onViewCreate();
        User getSupplier(String supplierID);
        ArrayList<inventory_supplier> getInventoryItems(String supplierID);
    }
    interface SupplierInventoryView{
        boolean callSupplierNavigation(MenuItem supplierMenuItem);
        String getIntentData(Intent intent);
        void setSupplierUser(User supplier);
        void setupInventoryList(ArrayList<inventory_supplier> inventoryArrayList);
    }
}
