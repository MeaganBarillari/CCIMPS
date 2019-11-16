package com.example.ccimp.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {

    private String customerName, orderID, createDateTime, businessID, userID, status, totalPrice;

    public Order(String customerName, String orderID, String createDateTime, String businessID, String userID, String status, String totalPrice){
        this.customerName = customerName;
        this.orderID = orderID;
        this.createDateTime = createDateTime;
        this.businessID = businessID;
        this.userID = userID;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    protected Order(Parcel in) {
        customerName = in.readString();
        orderID = in.readString();
        createDateTime = in.readString();
        businessID = in.readString();
        userID = in.readString();
        status = in.readString();
        totalPrice = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getBusinessID() {
        return businessID;
    }

    public void setBusinessID(String businessID) {
        this.businessID = businessID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerName);
        dest.writeString(orderID);
        dest.writeString(createDateTime);
        dest.writeString(businessID);
        dest.writeString(userID);
        dest.writeString(status);
        dest.writeString(totalPrice);
    }
}
