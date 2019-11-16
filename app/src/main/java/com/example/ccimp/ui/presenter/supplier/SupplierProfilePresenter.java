package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierProfileInterface;

public class SupplierProfilePresenter implements SupplierProfileInterface.SupplierProfilePresenter {

    SupplierProfileInterface.SupplierProfileView supplierProfileView;

    public SupplierProfilePresenter(SupplierProfileInterface.SupplierProfileView supplierProfileView){
        this.supplierProfileView = supplierProfileView;
    }

    @Override
    public void onViewCreate() {
        supplierProfileView.setupProfile();
    }
}
