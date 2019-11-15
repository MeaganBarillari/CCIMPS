package com.example.ccimp.ui.interfaces.business;

import android.view.MenuItem;

import com.example.ccimp.ui.model.order_info;

import java.util.ArrayList;

public interface BusinessOrderDetailInterface {

    interface BusinessOrderDetailPresenter {

        void onViewCreate();
    }

    interface BusinessOrderDetailView {

        boolean callSupplierNavigation(MenuItem businessMenuItem);

        void setupOrderItemList(ArrayList<order_info> orderItemArrayList);
    }
}
