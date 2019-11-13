package com.example.ccimp.ui.model;

public class BusinessRequest {

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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getRequestID() {
        return requestID;
    }

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
}
