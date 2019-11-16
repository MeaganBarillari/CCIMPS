package com.example.ccimp.ui.interfaces.customer;

import android.view.MenuItem;

import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public interface CustomerMenuInterface {
    interface CustomerMenuPresenter{
        void onViewCreate();
    }

    interface CustomerMenuView{

        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupInventoryList(ArrayList<inventory_business> inventoryArrayList);
    }
}
