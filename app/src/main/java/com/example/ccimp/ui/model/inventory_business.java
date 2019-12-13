package com.example.ccimp.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class inventory_business implements Parcelable {

    private String itemName, businessID, itemID, quantity, availQuantity, price, customDetail;
    public inventory_business(String itemName, String businessID, String itemID, String quantity, String availQuantity, String price, String customDetail){
        this.itemName = itemName;
        this.businessID = businessID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.availQuantity = availQuantity;
        this.price = price;
        this.customDetail = customDetail;
    }

    protected inventory_business(Parcel in) {
        itemName = in.readString();
        businessID = in.readString();
        itemID = in.readString();
        quantity = in.readString();
        availQuantity = in.readString();
        price = in.readString();
        customDetail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(businessID);
        dest.writeString(itemID);
        dest.writeString(quantity);
        dest.writeString(availQuantity);
        dest.writeString(price);
        dest.writeString(customDetail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<inventory_business> CREATOR = new Creator<inventory_business>() {
        @Override
        public inventory_business createFromParcel(Parcel in) {
            return new inventory_business(in);
        }

        @Override
        public inventory_business[] newArray(int size) {
            return new inventory_business[size];
        }
    };

    public String getCustomDetail(){
        return customDetail;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
