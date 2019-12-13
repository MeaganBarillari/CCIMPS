package com.example.ccimp.ui.presenter.customer;

import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public class CustomerMenuPresenter implements CustomerMenuInterface.CustomerMenuPresenter {

    CustomerMenuInterface.CustomerMenuView customerMenuView;
    ArrayList<inventory_business> inventoryArrayList;

    public CustomerMenuPresenter(CustomerMenuInterface.CustomerMenuView customerMenuView, String businessID){
        this.customerMenuView = customerMenuView;
        this.inventoryArrayList = getInventoryItems(businessID);
    }

    @Override
    public void onViewCreate() {
    }

    @Override
    public User getCustomer(String customerID) {
        return null;
    }

    @Override
    public ArrayList<inventory_business> getInventoryItems(String businessID) {

        return null;
    }
}
