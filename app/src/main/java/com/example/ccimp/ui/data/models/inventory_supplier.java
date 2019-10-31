package com.example.ccimp.ui.data.models;

public class inventory_supplier {

    private String itemID, supplierID, itemName, price;
    public inventory_supplier(String itemID, String supplierID, String itemName, String price){
        this.itemID = itemID;
        this.supplierID = supplierID;
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
