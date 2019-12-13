package com.example.ccimp.ui.presenter.business;

import com.example.ccimp.ui.interfaces.business.BusinessRequestFromSupplierInterface;
import com.example.ccimp.ui.interfaces.business.BusinessRequestsInterface;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public class BusinessRequestFromSupplierPresenter implements BusinessRequestFromSupplierInterface.BusinessRequestFromSupplierPresenter {
    BusinessRequestFromSupplierInterface.BusinessRequestFromSupplierView businessRequestFromSupplierView;
    ArrayList<Item> itemArrayList;
    ArrayList<User> userArrayList;

    String userID;
    Item item1 = new Item("123", "Sugar", "10", "123", "23", "chewy");
    User user1 = new User("123", "Sup", "sup@gmail.com", "123", "supplier", "123456789", "123 Wash");

    public BusinessRequestFromSupplierPresenter(BusinessRequestFromSupplierInterface.BusinessRequestFromSupplierView businessRequestFromSupplierView, String businessID){
        this.businessRequestFromSupplierView = businessRequestFromSupplierView;
        this.userID = businessID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        itemArrayList = new ArrayList();
        itemArrayList.add(item1);

        userArrayList = new ArrayList();
        userArrayList.add(user1);
    }

    @Override
    public void onViewCreate() {
        businessRequestFromSupplierView.setupSupplierList(userArrayList);
    }
}
