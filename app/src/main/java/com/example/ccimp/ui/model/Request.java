package com.example.ccimp.ui.model;

public class Request {
    private int requestID, totalPrice;
    private String businessName, orderDate, orderStatus, needByDate;

    public Request(String businessName, String orderDate, String needByDate, String orderStatus, int requestID, int totalPrice) {
        this.businessName = businessName;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.requestID = requestID;
        this.totalPrice = totalPrice;
        this.needByDate = needByDate;
    }
}
