package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierHomeInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierRequestHistoryInterface;
import com.example.ccimp.ui.model.Request;

import java.util.ArrayList;

public class SupplierRequestHistoryPresenter implements SupplierRequestHistoryInterface.SupplierRequestHistoryPresenter {

    SupplierRequestHistoryInterface.SupplierRequestHistoryView supplierRequestHistoryView;
    ArrayList<Request> requestArrayList;
    String userID;
    Request request1 = new Request("Starbucks", "123", "231", "345", "200", "2019/11/1",
            "2019/10/31", "Working");

    public SupplierRequestHistoryPresenter(SupplierRequestHistoryInterface.SupplierRequestHistoryView supplierRequestHistoryView, String supplierID){
        this.supplierRequestHistoryView = supplierRequestHistoryView;
        this.userID = supplierID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        requestArrayList = new ArrayList();
        requestArrayList.add(request1);
    }

    @Override
    public void onViewCreate() {
        supplierRequestHistoryView.setupRequestHistoryList(requestArrayList);
    }
}
