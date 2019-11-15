package com.example.ccimp.ui.interfaces.business;

import android.view.MenuItem;

import com.example.ccimp.ui.model.Order;

import java.util.ArrayList;

public interface BusinessOrderHistoryInterface {

    interface BusinessOrderHistoryPresenter {

        void onViewCreate();
    }

    interface BusinessOrderHistoryView {
        boolean callBusinessNavigation(MenuItem businessMenuItem);
        void setupOrderHistoryList(ArrayList<Order> orderArrayList);
    }
}
