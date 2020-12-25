package com.example.socialdapp.network;

import android.os.AsyncTask;

import com.example.socialdapp.custom.Gig;

public class InsertGigsUtils extends AsyncTask<Void, Void, Void> {
    private String title;
    private String desc;
    private int merchantID;
    private int price;
    public InsertGigsUtils(String title, String desc, int merchantID, int price){
        this.title = title;
        this.desc = desc;
        this.merchantID  =merchantID;
        this.price = price;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        NetworkUtils.insertGig(title, desc, merchantID, price);
        return null;
    }
}
