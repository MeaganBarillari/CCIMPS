package com.example.ccimp.ui.interfaces.business;

import android.view.MenuItem;

import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;

public interface BusinessCartInterface {

    interface BusinessCartPresenter{
        void onViewCreate();
    }

    interface BusinessCartView{

        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupInventoryList(ArrayList<inventory_supplier> inventoryArrayList);
    }
}
