package com.example.ccimp.ui.interfaces.customer;

import android.view.MenuItem;

public interface CustomerProfileInterface {

    interface CustomerProfilePresenter{
        void onViewCreate();
    }

    interface CustomerProfileView{
        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupProfile();
    }
}
