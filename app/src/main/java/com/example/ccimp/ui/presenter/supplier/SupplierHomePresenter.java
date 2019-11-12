package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierHomeInterface;
import com.example.ccimp.ui.model.Request;

import java.util.ArrayList;

public class SupplierHomePresenter implements SupplierHomeInterface.SupplierHomePresenter {

    SupplierHomeInterface.SupplierHomeView supplierHomeView;
    ArrayList<Request> requestArrayList;
    String userID;
    Request request = new Request("Starbucks", "123", "231", "345", "200", "2019/11/1",
            "2019/10/31", "Working");

    public SupplierHomePresenter(SupplierHomeInterface.SupplierHomeView supplierHomeView, String supplierID){
        this.supplierHomeView = supplierHomeView;
        this.userID = supplierID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        requestArrayList = new ArrayList();
        requestArrayList.add(request);
    }

    @Override
    public void onViewCreate() {
        supplierHomeView.setupRequestList(requestArrayList);
    }
}
