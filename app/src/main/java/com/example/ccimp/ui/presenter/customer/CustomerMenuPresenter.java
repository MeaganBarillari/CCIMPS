package com.example.ccimp.ui.presenter.customer;

import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public class CustomerMenuPresenter implements CustomerMenuInterface.CustomerMenuPresenter {

    CustomerMenuInterface.CustomerMenuView customerMenuView;
    ArrayList<inventory_business> inventoryArrayList;
    inventory_business inventory1 = new inventory_business("coffee", "123", "Beans", "200", "30", "30");

    public CustomerMenuPresenter(CustomerMenuInterface.CustomerMenuView customerMenuView, String businessID){
        this.customerMenuView = customerMenuView;
        this.inventoryArrayList = getInventoryItems(businessID);
    }

    @Override
    public void onViewCreate() {
        customerMenuView.setupInventoryList(inventoryArrayList);
    }

    @Override
    public User getCustomer(String customerID) {
        return new User("400", "customer", "customer@gmail.com", "123", "Customer", "2533205453", "123 W Wash");
    }

    @Override
    public ArrayList<inventory_business> getInventoryItems(String businessID) {
        ArrayList<inventory_business> list = new ArrayList();
        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        list.add(inventory1);
        return list;
    }
}
