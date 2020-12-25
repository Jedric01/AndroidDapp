package com.example.socialdapp.custom;

public class Order {
    public String title;
    public int requestID;
    public String status;
    public int buyerID;

    public Order(String title, int requestID, String status, int buyerID) {
        this.title = title;
        this.requestID = requestID;
        this.status = status;
        this.buyerID = buyerID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }
}
