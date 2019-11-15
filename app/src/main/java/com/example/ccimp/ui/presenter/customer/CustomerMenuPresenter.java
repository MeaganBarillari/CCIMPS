package com.example.ccimp.ui.presenter.customer;

import com.example.ccimp.ui.interfaces.business.BusinessInventoryInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerMenuInterface;
import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public class CustomerMenuPresenter implements CustomerMenuInterface.CustomerMenuPresenter {

    CustomerMenuInterface.CustomerMenuView customerMenuView;
    ArrayList<inventory_business> inventoryArrayList;
    String userID;
    inventory_business inventory1 = new inventory_business("123", "312", "Beans", "200", "30", "30");

    public CustomerMenuPresenter(CustomerMenuInterface.CustomerMenuView customerMenuView, String customerID){
        this.customerMenuView = customerMenuView;
        this.userID = customerID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        inventoryArrayList = new ArrayList();
        inventoryArrayList.add(inventory1);
    }

    @Override
    public void onViewCreate() {
        customerMenuView.setupInventoryList(inventoryArrayList);
    }
}
