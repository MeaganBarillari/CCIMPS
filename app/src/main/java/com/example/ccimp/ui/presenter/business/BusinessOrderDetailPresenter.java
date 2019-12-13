package com.example.ccimp.ui.presenter.business;

import com.example.ccimp.ui.business.BusinessOrderDetailActivity;
import com.example.ccimp.ui.interfaces.business.BusinessOrderDetailInterface;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.order_info;

import java.util.ArrayList;

public class BusinessOrderDetailPresenter implements BusinessOrderDetailInterface.BusinessOrderDetailPresenter {

    BusinessOrderDetailInterface.BusinessOrderDetailView businessOrderDetailView;
    ArrayList<order_info> orderItemArrayList;
    User business;


    public BusinessOrderDetailPresenter(BusinessOrderDetailActivity businessOrderDetailActivity, String businessID) {
        this.businessOrderDetailView = businessOrderDetailActivity;
        this.business = getBusiness(businessID);
        orderItemArrayList = getOrderItems(this.business.getUserID());
    }

    @Override
    public void onViewCreate() {

    }

    @Override
    public User getBusiness(String businessEmail) {
        return null;
    }

    @Override
    public ArrayList<order_info> getOrderItems(String businessID) {

        return null;
    }
}
