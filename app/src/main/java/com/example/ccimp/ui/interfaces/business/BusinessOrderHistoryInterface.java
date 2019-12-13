package com.example.ccimp.ui.interfaces.business;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public interface BusinessOrderHistoryInterface {

    interface BusinessOrderHistoryPresenter {

        void onViewCreate();
        ArrayList<Order> getOrderHistoryList(String orderID);
    }

    interface BusinessOrderHistoryView {
        boolean callBusinessNavigation(MenuItem businessMenuItem);
        User getIntentData(Intent intent);
        void setupOrderHistoryList();
    }
}
