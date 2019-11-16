package com.example.ccimp.ui.presenter.customer;

import com.example.ccimp.ui.interfaces.business.BusinessProfileInterface;
import com.example.ccimp.ui.interfaces.customer.CustomerProfileInterface;
import com.example.ccimp.ui.model.User;

public class CustomerProfilePresenter implements CustomerProfileInterface.CustomerProfilePresenter {
    CustomerProfileInterface.CustomerProfileView customerProfileView;

    public CustomerProfilePresenter(CustomerProfileInterface.CustomerProfileView customerProfileView){
        this.customerProfileView = customerProfileView;
    }

    @Override
    public void onViewCreate() {
        customerProfileView.setupProfile();
    }
}
