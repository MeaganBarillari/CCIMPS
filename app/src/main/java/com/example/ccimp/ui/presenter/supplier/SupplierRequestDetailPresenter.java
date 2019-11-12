package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierRequestDetailInterface;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.inventory_supplier;
import com.example.ccimp.ui.model.request_info;

import java.util.ArrayList;

public class SupplierRequestDetailPresenter implements SupplierRequestDetailInterface.SupplierRequestDetailPresenter {

    SupplierRequestDetailInterface.SupplierRequestDetailView supplierRequestDetailView;
    ArrayList<request_info> requestItemArrayList;
    String supplierID;
    request_info request_info1 = new request_info("123", "123", "3", "$300");
    request_info request_info2 = new request_info("123", "124", "1", "$15");

    public SupplierRequestDetailPresenter(SupplierRequestDetailInterface.SupplierRequestDetailView supplierRequestDetailView, String supplierID){
        this.supplierRequestDetailView = supplierRequestDetailView;
        this.supplierID = supplierID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        requestItemArrayList = new ArrayList();
        requestItemArrayList.add(request_info1);
        requestItemArrayList.add(request_info2);
    }

    @Override
    public void onViewCreate()
    {
        supplierRequestDetailView.setupRequestItemList(requestItemArrayList);
    }
}
