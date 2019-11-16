package com.example.ccimp.ui.interfaces.customer;

import android.view.MenuItem;

import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public interface CustomerHomeInterface {

    interface CustomerHomePresenter{
        void onViewCreate();
    }

    interface CustomerHomeView{

        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupBusinessList(ArrayList<User> userArrayList);
    }
}
