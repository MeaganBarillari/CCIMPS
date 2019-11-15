package com.example.ccimp.ui.interfaces.supplier;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public interface SupplierRequestHistoryInterface {

    interface SupplierRequestHistoryPresenter {

        void onViewCreate();
        User Supplier(String supplierID);
        ArrayList<Request> getRequestHistoryItems(String requestID);
    }

    interface SupplierRequestHistoryView {
        boolean callSupplierNavigation(MenuItem supplierMenuItem);
        User getIntentData(Intent intent);
        void setupRequestHistoryList(ArrayList<Request> requestHistoryArrayList);
    }
}
