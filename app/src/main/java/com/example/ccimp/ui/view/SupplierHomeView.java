package com.example.ccimp.ui.view;

import android.view.MenuItem;

public interface SupplierHomeView {
    boolean callSupplierNavigation(MenuItem supplierMenuItem);


    void SupplierRequestHistory(int supplierID);

    void SupplierRequestDetail(int requestID);
}
