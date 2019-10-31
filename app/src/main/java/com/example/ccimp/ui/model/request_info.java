package com.example.ccimp.ui.model;

public class request_info {
    private String requestid, itemid, quantity;
    public request_info(String requestid, String itemid, String quantity){
        this.requestid = requestid;
        this.itemid = itemid;
        this.quantity = quantity;
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
}
