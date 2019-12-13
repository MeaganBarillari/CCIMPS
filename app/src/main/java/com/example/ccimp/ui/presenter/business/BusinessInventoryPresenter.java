package com.example.ccimp.ui.presenter.business;

import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;

public class BusinessInventoryPresenter implements BusinessInventoryInterface.BusinessInventoryPresenter {
    BusinessInventoryInterface.BusinessInventoryView businessInventoryView;
    String userID;

    public BusinessInventoryPresenter(BusinessInventoryInterface.BusinessInventoryView businessInventoryView, String businessID){
        this.businessInventoryView = businessInventoryView;
        this.userID = businessID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID

    }

    @Override
    public void onViewCreate() {

    }
}
