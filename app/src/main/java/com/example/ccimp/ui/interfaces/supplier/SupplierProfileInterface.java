package com.example.ccimp.ui.interfaces.supplier;

import android.view.MenuItem;

public interface SupplierProfileInterface {
    interface SupplierProfilePresenter{
        void onViewCreate();
    }

    interface SupplierProfileView{
        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupProfile();
    }
}
