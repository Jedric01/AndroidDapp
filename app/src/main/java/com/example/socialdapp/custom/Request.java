package com.example.socialdapp.custom;

public class Request {
    private int requestID;
    private int price;
    private int merchantID;
    private String requestTitle;
    private String status;

    public Request(int requestID, int price,  int merchantID, String requestTitle, String status) {
        this.requestID = requestID;
        this.price = price;
        this.merchantID = merchantID;
        this.requestTitle = requestTitle;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(int merchantID) {
        this.merchantID = merchantID;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }
}
