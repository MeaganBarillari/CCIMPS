package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierInventoryInterface;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierInventoryPresenter implements SupplierInventoryInterface.SupplierInventoryPresenter {

    private SupplierInventoryInterface.SupplierInventoryView supplierInventoryView;
    private ArrayList<inventory_supplier> inventoryArrayList;
    private User supplier;

    public SupplierInventoryPresenter(SupplierInventoryInterface.SupplierInventoryView supplierInventoryView, String supplierID){
        this.supplierInventoryView = supplierInventoryView;
        this.supplier = getSupplier(supplierID);
    }

    @Override
    public void onViewCreate() {
        supplierInventoryView.setupInventoryList();
        supplierInventoryView.setSupplierUser(this.supplier);
    }

    @Override
    public User getSupplier(String supplierID) {
        return new User("123", "supplier", "supplier@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    }

    @Override
    public List<Item> getInventoryItems(String supplierID) {
        ArrayList<Item> list = new ArrayList<>();
        return list;
    }
}
