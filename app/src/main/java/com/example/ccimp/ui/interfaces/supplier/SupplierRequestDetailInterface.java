package com.example.ccimp.ui.interfaces.supplier;

import android.view.MenuItem;

import com.example.ccimp.ui.model.request_info;

import java.util.ArrayList;

public interface SupplierRequestDetailInterface {

    interface SupplierRequestDetailPresenter {

        void onViewCreate();
    }

    interface SupplierRequestDetailView {

        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupRequestItemList(ArrayList<request_info> requestItemArrayList);
    }
}
