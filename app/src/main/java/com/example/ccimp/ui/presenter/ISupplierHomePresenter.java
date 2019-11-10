package com.example.ccimp.ui.presenter;

import android.content.Context;
import android.view.MenuItem;

public interface ISupplierHomePresenter {

    void viewAllCurrentRequests(int supplierId, Context context);

    void viewHistory(int supplierID, Context context);

    void viewRequestDetail(int requestID, Context context);

    void handleSupplierNavigation(MenuItem itemID, Context context);
}
