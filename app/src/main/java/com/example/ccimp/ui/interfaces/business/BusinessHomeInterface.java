package com.example.ccimp.ui.interfaces.business;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public interface BusinessHomeInterface {

    interface BusinessHomePresenter {

        void onViewCreate();

        User getBusiness(String businessEmail);

        ArrayList<Order> getCurrentOrders(String businessID);
    }

    interface BusinessHomeView {
        boolean callBusinessNavigation(MenuItem businessMenuItem);

        void setupOrderList(ArrayList<Order> orderArrayList);

        String getIntentData(Intent intent);

        void setBusinessUser(User business);
    }
}
