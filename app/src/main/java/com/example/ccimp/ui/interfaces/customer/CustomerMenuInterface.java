package com.example.ccimp.ui.interfaces.customer;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public interface CustomerMenuInterface {
    interface CustomerMenuPresenter{
        void onViewCreate();
        User getCustomer(String customerID);
        ArrayList<inventory_business> getInventoryItems(String businessID);
    }

    interface CustomerMenuView{

        boolean callCustomerNavigation(MenuItem customerMenuItem);
        String getIntentBusinessID(Intent intent);
        User getIntentCustomerObject(Intent intent);
        void setupInventoryList(ArrayList<inventory_business> inventoryArrayList);
    }
}
