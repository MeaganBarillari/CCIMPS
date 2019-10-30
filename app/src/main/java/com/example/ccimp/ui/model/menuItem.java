package com.example.ccimp.ui.model;

public class menuItem {
    private int menuitemID, quantity, price;
    private String business, menuItemName;

    public menuItem(String business, String menuItemName, int itemID, int quantity, int price){
        this.menuitemID = menuitemID;
        this.quantity = quantity;
        this.price = price;
        this.business = business;
        this.menuItemName = menuItemName;
    }
}
