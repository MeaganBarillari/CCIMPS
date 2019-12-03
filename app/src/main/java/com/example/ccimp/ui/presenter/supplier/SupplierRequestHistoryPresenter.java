package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierRequestHistoryInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public class SupplierRequestHistoryPresenter implements SupplierRequestHistoryInterface.SupplierRequestHistoryPresenter {

    SupplierRequestHistoryInterface.SupplierRequestHistoryView supplierRequestHistoryView;
    ArrayList<BusinessRequest> requestArrayList;
    BusinessRequest businessRequest1 = new BusinessRequest("Starbucks", "123", "231", "345", "200", "2019/11/1",
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
    public ArrayList<BusinessRequest> getRequestHistoryList(String requestID) {
        ArrayList<BusinessRequest> list = new ArrayList<BusinessRequest>();
        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        list.add(businessRequest1);
        return list;
    }
}
