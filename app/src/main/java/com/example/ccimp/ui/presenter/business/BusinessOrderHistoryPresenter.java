package com.example.ccimp.ui.presenter.business;

import com.example.ccimp.ui.business.BusinessOrderHistoryActivity;
import com.example.ccimp.ui.interfaces.business.BusinessOrderHistoryInterface;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public class BusinessOrderHistoryPresenter implements BusinessOrderHistoryInterface.BusinessOrderHistoryPresenter {

    Order order1 = new Order("William", "123456", "2019/11/1", "124578", "987654321", "Done", "600");
    BusinessOrderHistoryInterface.BusinessOrderHistoryView businessOrderHistoryView;
    ArrayList<Order> orderArrayList;

    public BusinessOrderHistoryPresenter(BusinessOrderHistoryActivity businessOrderHistoryActivity, User business) {

        this.businessOrderHistoryView = businessOrderHistoryActivity;
        this.orderArrayList = getOrderHistoryList(business.getUserID());
    }

    @Override
    public void onViewCreate() {
        businessOrderHistoryView.setupOrderHistoryList(orderArrayList);
    }

    @Override
    public ArrayList<Order> getOrderHistoryList(String orderID) {
        ArrayList<Order> list = new ArrayList<Order>();
        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        list.add(order1);
        return list;
    }
}
