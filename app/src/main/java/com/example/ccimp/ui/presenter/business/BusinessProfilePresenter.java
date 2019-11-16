package com.example.ccimp.ui.presenter.business;

import com.example.ccimp.ui.interfaces.business.BusinessProfileInterface;

public class BusinessProfilePresenter implements BusinessProfileInterface.BusinessProfilePresenter {

    BusinessProfileInterface.BusinessProfileView businessProfileView;
    String userID;

    public BusinessProfilePresenter(BusinessProfileInterface.BusinessProfileView businessProfileView, String businessID){
        this.businessProfileView = businessProfileView;
        this.userID = businessID;
    }

    @Override
    public void onViewCreate() {
        businessProfileView.setupProfile();
    }
}
