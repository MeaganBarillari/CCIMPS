package com.example.ccimp.ui.data.models;

public class inventory_business {

    private String itemName, businessID, itemID, quantity, availQuantity;
    public inventory_business(String itemName, String businessID, String itemID, String quantity, String availQuantity){
        this.itemName = itemName;
        this.businessID = businessID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.availQuantity = availQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBusinessID() {
        return businessID;
    }

    public void setBusinessID(String businessID) {
        this.businessID = businessID;
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

    public String getAvailQuantity() {
        return availQuantity;
    }

    public void setAvailQuantity(String availQuantity) {
        this.availQuantity = availQuantity;
    }
}
