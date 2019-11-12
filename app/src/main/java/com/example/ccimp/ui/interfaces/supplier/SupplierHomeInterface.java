package com.example.ccimp.ui.interfaces.supplier;

import android.content.Context;
import android.view.MenuItem;

import com.example.ccimp.ui.model.Request;

import java.util.ArrayList;

public interface SupplierHomeInterface {

    interface SupplierHomePresenter {

        void onViewCreate();
    }

    interface SupplierHomeView {
        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupRequestList(ArrayList<Request> requestArrayList);
    }
}
