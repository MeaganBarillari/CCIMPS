package com.example.ccimp.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.SupplierHomeInterface;
import com.example.ccimp.ui.model.Request;

import java.util.ArrayList;

public class SupplierHomePresenter implements SupplierHomeInterface.SupplierHomePresenter {

    SupplierHomeInterface.SupplierHomeView supplierHomeView;
    ArrayList<Request> requestArrayList;
    Request request1 = new Request("Starbucks", "123", "231", "345", "200", "2019/11/1",
            "2019/10/31", "Working");

    public SupplierHomePresenter(SupplierHomeInterface.SupplierHomeView supplierHomeView){
        this.supplierHomeView = supplierHomeView;

        // Will need to be replaced by a call to another function that populates the list from the model
        requestArrayList = new ArrayList();
        requestArrayList.add(request1);
    }

    @Override
    public void onViewCreate() {
        supplierHomeView.setupRequestList(requestArrayList);
    }
}