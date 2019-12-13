package com.example.ccimp.ui.presenter.business;

import com.example.ccimp.ui.interfaces.business.BusinessRequestsInterface;
import com.example.ccimp.ui.model.BusinessRequest;

import java.util.ArrayList;

public class BusinessRequestsPresenter implements BusinessRequestsInterface.BusinessRequestsPresenter {

    BusinessRequestsInterface.BusinessRequestsView businessRequestsView;
    ArrayList<BusinessRequest> requestsArrayList;
    String userID;

    public BusinessRequestsPresenter(BusinessRequestsInterface.BusinessRequestsView businessRequestsView, String businessID){
        this.businessRequestsView = businessRequestsView;
        this.userID = businessID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
    }

    @Override
    public void onViewCreate() {

    }
}
