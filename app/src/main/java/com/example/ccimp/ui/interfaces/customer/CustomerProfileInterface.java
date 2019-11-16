package com.example.ccimp.ui.interfaces.customer;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.User;

public interface CustomerProfileInterface {

    interface CustomerProfilePresenter{
        void onViewCreate();
    }

    interface CustomerProfileView{
        boolean callSupplierNavigation(MenuItem supplierMenuItem);
        User getIntentData(Intent intent);
        void setupProfile();
    }
}
