package com.example.ccimp.ui.interfaces.supplier;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public interface SupplierHomeInterface {

    interface SupplierHomePresenter {

        void onViewCreate();

        User getSupplier(String supplierEmail);

        ArrayList<Request> getCurrentRequest(String supplierID);
    }

    interface SupplierHomeView {
        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupRequestList(ArrayList<Request> requestArrayList);

        String getIntentData(Intent intent);

        void setSupplierUser(User supplier);
    }
}
