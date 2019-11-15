package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierHomeInterface;
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SupplierHomePresenter implements SupplierHomeInterface.SupplierHomePresenter {

    private SupplierHomeInterface.SupplierHomeView supplierHomeView;
    private ArrayList<Request> requestArrayList;
    private User supplier;
    private Request request = new Request("Starbucks", "123", "231", "345", "200", "2019/11/1",
            "2019/10/31", "Working");

    public SupplierHomePresenter(SupplierHomeInterface.SupplierHomeView supplierHomeView, String supplierEmail){
        this.supplierHomeView = supplierHomeView;

        this.supplier = getSupplier(supplierEmail);

        requestArrayList = getCurrentRequest(this.supplier.getUserID());
    }

    @Override
    public void onViewCreate() {
        supplierHomeView.setupRequestList(requestArrayList);
        supplierHomeView.setSupplierUser(supplier);
    }

    @Override
    public User getSupplier(String supplierEmail) {
        return new User("123", "supplier", "supplier@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    }

    @Override
    public ArrayList<Request> getCurrentRequest(String supplierID) {
        ArrayList<Request> list = new ArrayList<Request>();
        //TODO: HAVE CALL TO BACKGROUND WORKER FROM THE MODEL
        list.add(request);
        return list;
    }
}
