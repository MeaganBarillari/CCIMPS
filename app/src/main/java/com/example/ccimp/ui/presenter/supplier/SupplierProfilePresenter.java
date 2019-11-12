package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierProfileInterface;

public class SupplierProfilePresenter implements SupplierProfileInterface.SupplierProfilePresenter {

    SupplierProfileInterface.SupplierProfileView supplierProfileView;
    String userID;

    public SupplierProfilePresenter(SupplierProfileInterface.SupplierProfileView supplierProfileView, String supplierID){
        this.supplierProfileView = supplierProfileView;
        this.userID = supplierID;
    }

    @Override
    public void onViewCreate() {
        supplierProfileView.setupProfile();
    }
}
