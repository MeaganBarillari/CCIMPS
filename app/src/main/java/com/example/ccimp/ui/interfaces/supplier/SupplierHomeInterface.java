package com.example.ccimp.ui.interfaces.supplier;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public interface SupplierHomeInterface {

    interface SupplierHomePresenter {

        void onViewCreate();

        User getSupplier(String supplierEmail);

        ArrayList<BusinessRequest> getCurrentRequest(String supplierID);
    }

    interface SupplierHomeView {
        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupRequestList(ArrayList<BusinessRequest> requestArrayList);

        String getIntentData(Intent intent);

        void setSupplierUser(User supplier);
    }
}
