package com.example.ccimp.ui.model;

public class Item {
    private int itemID, quantity, price;
    private String supplier, itemName;

    public Item(String supplier, String itemName, int itemID, int quantity, int price){
        this.itemID = itemID;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
        this.itemName = itemName;
    }
}
