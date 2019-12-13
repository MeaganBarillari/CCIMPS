package com.example.ccimp.ui.interfaces.business;

import android.view.MenuItem;

import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public interface BusinessRequestFromSupplierInterface {
    interface BusinessRequestFromSupplierPresenter{
        void onViewCreate();
    }

    interface BusinessRequestFromSupplierView{

        boolean callSupplierNavigation(MenuItem supplierMenuItem);

        void setupSupplierList();
    }
}
