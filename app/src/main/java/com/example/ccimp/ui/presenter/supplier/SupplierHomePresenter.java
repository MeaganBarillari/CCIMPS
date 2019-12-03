package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierHomeInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public class SupplierHomePresenter implements SupplierHomeInterface.SupplierHomePresenter {
    private SupplierHomeInterface.SupplierHomeView supplierHomeView;
    private ArrayList<BusinessRequest> requestArrayList;
    private User supplier;
    private BusinessRequest businessRequest = new BusinessRequest("Starbucks", "123", "231", "345", "200", "2019/11/1",
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
        //TODO: HAVE CALL TO BACKGROUND WORKER FROM THE MODEL
        return new User("123", "supplier", "supplier@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    }

    @Override
    public ArrayList<BusinessRequest> getCurrentRequest(String supplierID) {
        ArrayList<BusinessRequest> list = new ArrayList<BusinessRequest>();
        //TODO: HAVE CALL TO BACKGROUND WORKER FROM THE MODEL
        list.add(businessRequest);
        return list;
    }
}
