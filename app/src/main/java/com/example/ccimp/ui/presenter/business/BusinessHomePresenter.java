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
    private Order order = new Order("meagan", "222", "2019/10/11",
            "124", "111", "working", "$200");

    public BusinessHomePresenter(BusinessHomeActivity businessHomeView, String businessEmail) {
        this.businessHomeView = businessHomeView;
        this.business = getBusiness(businessEmail);
        orderArrayList = getCurrentOrders(this.business.getUserID());
    }

    @Override
    public void onViewCreate() {
        businessHomeView.setupOrderList(orderArrayList);
        businessHomeView.setBusinessUser(business);
    }

    @Override
    public User getBusiness(String businessEmail) {
        return new User("124", "business", "business@ccimp.com", "123", "Supplier", "2533205453", "123 W Wash");
    }

    @Override
    public ArrayList<Order> getCurrentOrders(String businessID) {
        ArrayList<Order> list = new ArrayList<Order>();
        //TODO: HAVE CALL TO BACKGROUND WORKER FROM THE MODEL
        list.add(order);
        return list;
    }
}
