package com.example.ccimp.ui.presenter.business;

import com.example.ccimp.ui.business.BusinessHomeActivity;
import com.example.ccimp.ui.interfaces.business.BusinessHomeInterface;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public class BusinessHomePresenter implements BusinessHomeInterface.BusinessHomePresenter {

    private BusinessHomeInterface.BusinessHomeView businessHomeView;
    private ArrayList<Order> orderArrayList;
    private User business;

    public BusinessHomePresenter(BusinessHomeActivity businessHomeView, String businessEmail) {
        this.businessHomeView = businessHomeView;
        this.business = getBusiness(businessEmail);
        orderArrayList = getCurrentOrders(this.business.getUserID());
    }

    @Override
    public void onViewCreate() {

    }

    @Override
    public User getBusiness(String businessEmail) {
return null;    }

    @Override
    public ArrayList<Order> getCurrentOrders(String businessID) {

        return null;
    }
}
