package com.example.ccimp.ui.interfaces.supplier;

import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.request_info;

import java.util.ArrayList;

public interface SupplierRequestDetailInterface {

    interface SupplierRequestDetailPresenter {

        void onViewCreate();
        User getSupplier(String supplierID);
        ArrayList<request_info> getCurrentRequestItems(String requestID);
    }

    interface SupplierRequestDetailView {

        boolean callSupplierNavigation(MenuItem supplierMenuItem);
        BusinessRequest getIntentData(Intent intent);
        void setSupplierUser(User supplier);
        void setupRequestItemList();
    }
}
