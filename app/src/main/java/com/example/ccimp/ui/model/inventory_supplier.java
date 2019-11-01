package com.example.ccimp.ui.model;

public class inventory_supplier {

    private String itemID, supplierID, itemName, price, quantity;
    public inventory_supplier(String itemID, String supplierID, String itemName, String price, String quantity){
        this.itemID = itemID;
        this.supplierID = supplierID;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
