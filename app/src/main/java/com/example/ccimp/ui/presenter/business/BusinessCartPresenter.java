//package com.example.ccimp.ui.presenter.business;
//
//import com.example.ccimp.ui.interfaces.business.BusinessCartInterface;
//import com.example.ccimp.ui.model.inventory_supplier;
//
//import java.util.ArrayList;
//
//public class BusinessCartPresenter implements BusinessCartInterface.BusinessCartPresenter {
//    BusinessCartInterface.BusinessCartView businessCartView;
//    ArrayList<inventory_supplier> inventoryArrayList;
//    String userID;
//    inventory_supplier inventory1 = new inventory_supplier("123", "312", "Beans", "200", "30");
//
//    public BusinessCartPresenter(BusinessCartInterface.BusinessCartView businessCartView, String businessID){
//        this.businessCartView = businessCartView;
//        this.userID = businessID;
//
//        // TODO: Will need to be replaced by a call to another function that populates the list from the model based off of the supplierID
//        inventoryArrayList = new ArrayList();
//        inventoryArrayList.add(inventory1);
//    }
//
//    @Override
//    public void onViewCreate() {
//        businessCartView.setupInventoryList(inventoryArrayList);
//    }
//}
