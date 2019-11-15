package com.example.ccimp.ui.interfaces.supplier;

import android.view.MenuItem;

import com.example.ccimp.ui.model.Request;

import java.util.ArrayList;

public interface SupplierRequestHistoryInterface {

    interface SupplierRequestHistoryPresenter {

        void onViewCreate();
    }

    interface SupplierRequestHistoryView {
        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupRequestHistoryList(ArrayList<Request> requestArrayList);
    }
}
