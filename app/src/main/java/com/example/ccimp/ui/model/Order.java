package com.example.ccimp.ui.model;

public class Order {
    private int customerID, orderID, totalPrice;
    private String customerName, orderDate, orderStatus, needByDate;

    public Order(String customerName, String orderDate, String needByDate, String orderStatus, int orderID, int totalPrice, int customerID) {
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderID = orderID;
        this.customerID = customerID;
        this.totalPrice = totalPrice;
        this.needByDate = needByDate;
    }
}
