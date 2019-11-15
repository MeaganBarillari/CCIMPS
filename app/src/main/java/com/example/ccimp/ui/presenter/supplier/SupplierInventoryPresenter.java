package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;

public class SupplierInventoryPresenter implements SupplierInventoryInterface.SupplierInventoryPresenter {

    private SupplierInventoryInterface.SupplierInventoryView supplierInventoryView;
    private ArrayList<inventory_supplier> inventoryArrayList;
    private User supplier;
    private inventory_supplier inventory1 = new inventory_supplier("123", "312", "Beans", "200", "30");

    public SupplierInventoryPresenter(SupplierInventoryInterface.SupplierInventoryView supplierInventoryView, String supplierID){
        this.supplierInventoryView = supplierInventoryView;
        this.supplier = getSupplier(supplierID);
        this.inventoryArrayList = getInventoryItems(supplierID);
    }

    @Override
    public void onViewCreate() {
        supplierInventoryView.setupInventoryList(inventoryArrayList);
        supplierInventoryView.setSupplierUser(this.supplier);
    }

    @Override
    public User getSupplier(String supplierID) {
        return new User("123", "supplier", "supplier@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    }

    @Override
    public ArrayList<inventory_supplier> getInventoryItems(String supplierID) {
        ArrayList<inventory_supplier> list = new ArrayList<inventory_supplier>();
        inventoryArrayList.add(inventory1);
        return list;
    }
}
