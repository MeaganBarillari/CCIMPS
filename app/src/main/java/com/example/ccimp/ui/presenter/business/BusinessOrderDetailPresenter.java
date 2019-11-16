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
    order_info order_info = new order_info("business@ccimp.com", "200", "3", "4", "$200");


    public BusinessOrderDetailPresenter(BusinessOrderDetailActivity businessOrderDetailActivity, String businessID) {
        this.businessOrderDetailView = businessOrderDetailActivity;
        this.business = getBusiness(businessID);
        orderItemArrayList = getOrderItems(this.business.getUserID());
    }

    @Override
    public void onViewCreate() {
        businessOrderDetailView.setupOrderItemList(orderItemArrayList);
        businessOrderDetailView.setBusinessUser(business);
    }

    @Override
    public User getBusiness(String businessEmail) {
        return new User("124", "business", "business@ccimp.com", "123", "Supplier", "2533205453", "123 W Wash");
    }

    @Override
    public ArrayList<order_info> getOrderItems(String businessID) {
        ArrayList<order_info> list = new ArrayList<order_info>();
        //TODO: HAVE CALL TO BACKGROUND WORKER FROM THE MODEL
        list.add(order_info);
        return list;
    }
}
