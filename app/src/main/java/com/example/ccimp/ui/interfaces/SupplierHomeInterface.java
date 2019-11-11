package com.example.ccimp.ui.interfaces;

import android.content.Context;
import android.view.MenuItem;

import com.example.ccimp.ui.model.Request;

import java.util.ArrayList;

public interface SupplierHomeInterface {

    interface SupplierHomePresenter {

        void onViewCreate();

//        void viewHistory(int supplierID);
//
//        void viewRequestDetail(int requestID, Context context);
    }

    interface SupplierHomeView {
        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupRequestList(ArrayList<Request> requestArrayList);
    }
}
