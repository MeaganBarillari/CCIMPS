package com.example.ccimp.ui.model;

public class request_info {
    private String requestid, itemName, quantity, totalPrice;
    public request_info(String requestid, String itemName, String quantity, String totalPrice){
        this.requestid = requestid;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemid(String itemName) {
        this.itemName = itemName;
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
