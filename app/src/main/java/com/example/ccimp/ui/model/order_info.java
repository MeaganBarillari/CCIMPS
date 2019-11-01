package com.example.ccimp.ui.model;

public class order_info {
    private String businessName, OrderID, itemID, quantity, totalPrice;
    public order_info(String businessName, String OrderID, String itemID, String quantity, String totalPrice){
        this.businessName = businessName;
        this.OrderID = OrderID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
