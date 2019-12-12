package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierRequestHistoryInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public class SupplierRequestHistoryPresenter implements SupplierRequestHistoryInterface.SupplierRequestHistoryPresenter {

    SupplierRequestHistoryInterface.SupplierRequestHistoryView supplierRequestHistoryView;
    ArrayList<BusinessRequest> requestArrayList;


    public SupplierRequestHistoryPresenter(SupplierRequestHistoryInterface.SupplierRequestHistoryView supplierRequestHistoryView, User supplier){
        this.supplierRequestHistoryView = supplierRequestHistoryView;
        this.requestArrayList = getRequestHistoryList(supplier.getUserID());
    }

    @Override
    public void onViewCreate() {
    }

    @Override
    public ArrayList<BusinessRequest> getRequestHistoryList(String requestID) {
        ArrayList<BusinessRequest> list = new ArrayList<BusinessRequest>();
        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        return list;
    }
}
