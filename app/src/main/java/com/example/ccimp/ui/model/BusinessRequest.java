package com.example.ccimp.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BusinessRequest implements Parcelable {

    private String businessName, requestID, supplierID, businessID, price, needByDate, requestDate, status;

    public BusinessRequest(String businessName, String requestID, String supplierID, String businessID, String price, String needByDate, String requestDate, String status) {
        this.businessName = businessName;
        this.requestID = requestID;
        this.supplierID = supplierID;
        this.businessID = businessID;
        this.price = price;
        this.needByDate = needByDate;
        this.requestDate = requestDate;
        this.status = status;
    }

    protected BusinessRequest(Parcel in) {
        businessName = in.readString();
        requestID = in.readString();
        supplierID = in.readString();
        businessID = in.readString();
        price = in.readString();
        needByDate = in.readString();
        requestDate = in.readString();
        status = in.readString();
    }

    public static final Creator<BusinessRequest> CREATOR = new Creator<BusinessRequest>() {
        @Override
        public BusinessRequest createFromParcel(Parcel in) {
            return new BusinessRequest(in);
        }

        @Override
        public BusinessRequest[] newArray(int size) {
            return new BusinessRequest[size];
        }
    };

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getRequestID() { return requestID; }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getBusinessID() {
        return businessID;
    }

    public void setBusinessID(String businessID) {
        this.businessID = businessID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNeedByDate() {
        return needByDate;
    }

    public void setNeedByDate(String needByDate) {
        this.needByDate = needByDate;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(businessName);
        dest.writeString(requestID);
        dest.writeString(supplierID);
        dest.writeString(businessID);
        dest.writeString(price);
        dest.writeString(needByDate);
        dest.writeString(requestDate);
        dest.writeString(status);
    }
}
