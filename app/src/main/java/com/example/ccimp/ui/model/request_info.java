package com.example.ccimp.ui.model;

public class request_info {
    private String requestid, itemid, quantity, totalPrice;
    public request_info(String requestid, String itemid, String quantity, String totalPrice){
        this.requestid = requestid;
        this.itemid = itemid;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
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
