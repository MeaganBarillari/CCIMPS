package com.example.ccimp.ui.presenter.business;

import com.example.ccimp.ui.interfaces.business.BusinessRequestsInterface;
import com.example.ccimp.ui.model.BusinessRequest;

import java.util.ArrayList;

public class BusinessRequestsPresenter implements BusinessRequestsInterface.BusinessRequestsPresenter {

    BusinessRequestsInterface.BusinessRequestsView businessRequestsView;
    ArrayList<BusinessRequest> requestsArrayList;
    String userID;
    BusinessRequest businessRequest1 = new BusinessRequest("Starbucks", "123", "231", "345", "200", "2019/11/1", "2019/10/31", "Working");

    public BusinessRequestsPresenter(BusinessRequestsInterface.BusinessRequestsView businessRequestsView, String businessID){
        this.businessRequestsView = businessRequestsView;
        this.userID = businessID;

        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
        requestsArrayList = new ArrayList();
        requestsArrayList.add(businessRequest1);
    }

    @Override
    public void onViewCreate() {
        businessRequestsView.setupRequestsList(requestsArrayList);
    }
}
