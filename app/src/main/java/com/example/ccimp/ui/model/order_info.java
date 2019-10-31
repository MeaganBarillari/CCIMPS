package com.example.ccimp.ui.model;

public class order_info {
    private String OrderID, itemID, quantity;
    public order_info(String OrderID, String itemID, String quantity){
        this.OrderID = OrderID;
        this.itemID = itemID;
        this.quantity = quantity;
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
}
