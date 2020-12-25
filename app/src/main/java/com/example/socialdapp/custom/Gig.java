package com.example.socialdapp.custom;

import android.text.Editable;

public class Gig {
    private int id;
    private String title;
    private String desc;
    private int merchantID;
    private int price;

    public Gig(int id, String title, String desc, int merchantID, int price) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.merchantID = merchantID;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Gig(Editable text, Editable text1) {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(int merchantID) {
        this.merchantID = merchantID;
    }
}
