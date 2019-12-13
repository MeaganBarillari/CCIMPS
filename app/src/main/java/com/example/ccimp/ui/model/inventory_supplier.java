package com.example.ccimp.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class inventory_supplier implements Parcelable {

    private String itemID, supplierID, itemName, price, quantity;
    public inventory_supplier(String itemID, String supplierID, String itemName, String price, String quantity){
        this.itemID = itemID;
        this.supplierID = supplierID;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    protected inventory_supplier(Parcel in) {
        itemID = in.readString();
        supplierID = in.readString();
        itemName = in.readString();
        price = in.readString();
        quantity = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemID);
        dest.writeString(supplierID);
        dest.writeString(itemName);
        dest.writeString(price);
        dest.writeString(quantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<inventory_supplier> CREATOR = new Creator<inventory_supplier>() {
        @Override
        public inventory_supplier createFromParcel(Parcel in) {
            return new inventory_supplier(in);
        }

        @Override
        public inventory_supplier[] newArray(int size) {
            return new inventory_supplier[size];
        }
    };

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
