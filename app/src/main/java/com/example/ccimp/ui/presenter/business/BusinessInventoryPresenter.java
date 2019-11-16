package com.example.ccimp.ui.presenter.business;

import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;

public class BusinessInventoryPresenter implements BusinessInventoryInterface.BusinessInventoryPresenter {
    BusinessInventoryInterface.BusinessInventoryView businessInventoryView;
    ArrayList<inventory_business> inventoryArrayList;
    String userID;
    inventory_business inventory1 = new inventory_business("123", "312", "Beans", "200", "30", "30");

    public BusinessInventoryPresenter(BusinessInventoryInterface.BusinessInventoryView businessInventoryView, String businessID){
        this.businessInventoryView = businessInventoryView;
        this.userID = businessID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        inventoryArrayList = new ArrayList();
        inventoryArrayList.add(inventory1);
    }

    @Override
    public void onViewCreate() {
        businessInventoryView.setupInventoryList(inventoryArrayList);
    }
}
