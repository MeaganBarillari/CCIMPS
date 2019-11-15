package com.example.ccimp.ui.interfaces.business;

import android.view.MenuItem;

public interface BusinessProfileInterface {
    interface BusinessProfilePresenter{
        void onViewCreate();
    }

    interface BusinessProfileView{
        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupProfile();
    }
}
