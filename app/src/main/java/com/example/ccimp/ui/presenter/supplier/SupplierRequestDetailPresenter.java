package com.example.ccimp.ui.presenter.supplier;

import com.example.ccimp.ui.interfaces.supplier.SupplierRequestDetailInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.request_info;

import java.util.ArrayList;

public class SupplierRequestDetailPresenter implements SupplierRequestDetailInterface.SupplierRequestDetailPresenter {

    SupplierRequestDetailInterface.SupplierRequestDetailView supplierRequestDetailView;
    ArrayList<request_info> requestItemArrayList;
    String requestID;
    User supplier;

    public SupplierRequestDetailPresenter(SupplierRequestDetailInterface.SupplierRequestDetailView supplierRequestDetailView, BusinessRequest businessRequest){
        this.supplierRequestDetailView = supplierRequestDetailView;
        this.requestID = businessRequest.getRequestID();
        this.supplier = getSupplier(businessRequest.getSupplierID());
        requestItemArrayList = getCurrentRequestItems(businessRequest.getRequestID());
    }

    @Override
    public void onViewCreate()
    {
    }

    @Override
    public User getSupplier(String requestID) {
        //TODO: HAVE CALL TO BACKGROUND WORKER FROM THE MODEL
        return new User("123", "supplier", "supplier@gmail.com", "123", "Supplier", "2533205453", "123 W Wash");
    }

    @Override
    public ArrayList<request_info> getCurrentRequestItems(String requestID) {
        ArrayList<request_info> list = new ArrayList<request_info>();
        //TODO: HAVE CALL TO BACKGROUND WORKER FROM THE MODEL
        return list;
    }


}
