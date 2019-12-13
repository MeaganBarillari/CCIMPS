package com.example.ccimp.ui.interfaces.business;

import android.view.MenuItem;
import com.example.ccimp.ui.model.BusinessRequest;

import java.util.ArrayList;

public interface BusinessRequestsInterface {

    interface BusinessRequestsPresenter{
        void onViewCreate();
    }

    interface BusinessRequestsView{

        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupRequestsList();
    }
}
