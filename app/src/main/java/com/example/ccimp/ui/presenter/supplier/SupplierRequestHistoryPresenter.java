package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierHomeInterface;
import com.example.ccimp.ui.interfaces.supplier.SupplierRequestHistoryInterface;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public class SupplierRequestHistoryPresenter implements SupplierRequestHistoryInterface.SupplierRequestHistoryPresenter {

    SupplierRequestHistoryInterface.SupplierRequestHistoryView supplierRequestHistoryView;
    ArrayList<Request> requestArrayList;
    Request request1 = new Request("Starbucks", "123", "231", "345", "200", "2019/11/1",
            "2019/10/31", "Working");

    public SupplierRequestHistoryPresenter(SupplierRequestHistoryInterface.SupplierRequestHistoryView supplierRequestHistoryView, User supplier){
        this.supplierRequestHistoryView = supplierRequestHistoryView;
        this.requestArrayList = getRequestHistoryList(supplier.getUserID());
    }

    @Override
    public void onViewCreate() {
        supplierRequestHistoryView.setupRequestHistoryList(requestArrayList);
    }

    @Override
    public ArrayList<Request> getRequestHistoryList(String requestID) {
        ArrayList<Request> list = new ArrayList<Request>();
        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        list.add(request1);
        return list;
    }
}
