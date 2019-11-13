package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierHomeInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;

public class SupplierInventoryPresenter implements SupplierInventoryInterface.SupplierInventoryPresenter {
    SupplierInventoryInterface.SupplierInventoryView supplierInventoryView;
    ArrayList<inventory_supplier> inventoryArrayList;
    String userID;
    inventory_supplier inventory1 = new inventory_supplier("123", "312", "Beans", "200", "30");

    public SupplierInventoryPresenter(SupplierInventoryInterface.SupplierInventoryView supplierInventoryView, String supplierID){
        this.supplierInventoryView = supplierInventoryView;
        this.userID = supplierID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        inventoryArrayList = new ArrayList();
        inventoryArrayList.add(inventory1);
    }

    @Override
    public void onViewCreate() {
        supplierInventoryView.setupInventoryList(inventoryArrayList);
    }
}
