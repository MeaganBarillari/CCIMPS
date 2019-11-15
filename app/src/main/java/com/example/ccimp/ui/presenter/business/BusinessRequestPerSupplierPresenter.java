package com.example.ccimp.ui.presenter.business;

import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.business.BusinessRequestPerSupplierInterface;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;

public class BusinessRequestPerSupplierPresenter implements BusinessRequestPerSupplierInterface.BusinessRequestPerSupplierPresenter {

    BusinessRequestPerSupplierInterface.BusinessRequestPerSupplierView businessRequestPerSupplierView;
    ArrayList<inventory_supplier> inventoryArrayList;
    String userID;
    inventory_supplier inventory1 = new inventory_supplier("123", "312", "Beans", "200", "30");

    public BusinessRequestPerSupplierPresenter(BusinessRequestPerSupplierInterface.BusinessRequestPerSupplierView businessRequestPerSupplierView, String businessID){
        this.businessRequestPerSupplierView = businessRequestPerSupplierView;
        this.userID = businessID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        inventoryArrayList = new ArrayList();
        inventoryArrayList.add(inventory1);
    }

    @Override
    public void onViewCreate() {
        businessRequestPerSupplierView.setupInventoryList(inventoryArrayList);
    }
}
