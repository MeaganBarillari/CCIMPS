package com.example.ccimp.ui.interfaces.supplier;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;
import java.util.List;

public interface SupplierInventoryInterface {
    interface SupplierInventoryPresenter{
        void onViewCreate();
        User getSupplier(String supplierID);
        List<Item> getInventoryItems(String supplierID);
    }
    interface SupplierInventoryView{
        boolean callSupplierNavigation(MenuItem supplierMenuItem);
        String getIntentData(Intent intent);
        void setSupplierUser(User supplier);
        void setupInventoryList();
    }
}
