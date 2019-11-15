package com.example.ccimp.ui.presenter.customer;

import com.example.ccimp.ui.interfaces.business.BusinessProfileInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerProfileInterface;

public class CustomerProfilePresenter implements CustomerProfileInterface.CustomerProfilePresenter {
    CustomerProfileInterface.CustomerProfileView customerProfileView;
    String userID;

    public CustomerProfilePresenter(CustomerProfileInterface.CustomerProfileView customerProfileView, String customerID){
        this.customerProfileView = customerProfileView;
        this.userID = customerID;
    }

    @Override
    public void onViewCreate() {
        customerProfileView.setupProfile();
    }
}
