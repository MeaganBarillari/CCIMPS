package com.example.ccimp.ui.presenter.customer;

import com.example.ccimp.ui.interfaces.customer.CustomerHomeInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public class CustomerHomePresenter implements CustomerHomeInterface.CustomerHomePresenter {
    CustomerHomeInterface.CustomerHomeView customerHomeView;
    ArrayList<User> userArrayList;
    String userID;
    User user1 = new User("123", "Starbucks", "star@gmail.com", "200", "business", "123123123", "123 State");

    public CustomerHomePresenter(CustomerHomeInterface.CustomerHomeView customerHomeView, String customerID){
        this.customerHomeView = customerHomeView;
        this.userID = customerID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        userArrayList = new ArrayList();
        userArrayList.add(user1);
    }

    @Override
    public void onViewCreate() {
        customerHomeView.setupBusinessList(userArrayList);
    }

    @Override
    public User getSupplier(String supplierEmail) {
        return null;
    }

    @Override
    public ArrayList<User> getBusinessList() {
        return null;
    }
}
