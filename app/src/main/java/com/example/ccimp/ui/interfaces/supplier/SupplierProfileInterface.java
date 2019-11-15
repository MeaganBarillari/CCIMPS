package com.example.ccimp.ui.interfaces.supplier;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.User;

public interface SupplierProfileInterface {
    interface SupplierProfilePresenter{
        void onViewCreate();

    }

    interface SupplierProfileView{
        boolean callSupplierNavigation(MenuItem supplierMenuItem);
        User getIntentData(Intent intent);
        void setupProfile();
    }
}
