package com.example.ccimp.ui.interfaces.supplier;

import android.view.MenuItem;

import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;

public interface SupplierInventoryInterface {
    interface SupplierInventoryPresenter{
        void onViewCreate();
    }
    interface SupplierInventoryView{
        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupInventoryList(ArrayList<inventory_supplier> inventoryArrayList);
    }
}
