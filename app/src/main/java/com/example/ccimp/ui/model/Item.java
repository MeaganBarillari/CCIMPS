package com.example.ccimp.ui.model;

public class Item {
    String itemID, name, price, supplierID, customDetail;

    public Item(String itemID, String name, String price, String supplierID, String customDetail){
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.supplierID = supplierID;
        this.customDetail = customDetail;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getCustomDetail() {
        return customDetail;
    }

    public void setCustomDetail(String customDetail) {
        this.customDetail = customDetail;
    }
}
