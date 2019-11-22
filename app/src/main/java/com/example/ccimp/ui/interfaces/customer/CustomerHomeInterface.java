package com.example.ccimp.ui.interfaces.customer;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public interface CustomerHomeInterface {

    interface CustomerHomePresenter{
        void onViewCreate();
        //User getSupplier(String supplierEmail);
        //ArrayList<User> getBusinessList();
    }

    interface CustomerHomeView{

        boolean callCustomerNavigation(MenuItem customerMenuItem);
        String getIntentData(Intent intent);
        void setCustomerUser(User customer);
        void setupBusinessList(ArrayList<User> userArrayList);
    }
}
