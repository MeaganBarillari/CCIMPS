package com.example.ccimp.ui.interfaces.business;

import android.view.MenuItem;

import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.order_info;

import java.util.ArrayList;

public interface BusinessOrderDetailInterface {

    interface BusinessOrderDetailPresenter {

        void onViewCreate();
        User getBusiness(String businessEmail);
        ArrayList<order_info> getOrderItems(String businessID);
    }

    interface BusinessOrderDetailView {

        boolean callBusinessNavigation(MenuItem businessMenuItem);
        void setupOrderItemList(ArrayList<order_info> orderItemArrayList);
        void setBusinessUser(User business);
    }
}
