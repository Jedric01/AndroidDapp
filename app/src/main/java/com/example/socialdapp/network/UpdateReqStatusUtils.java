package com.example.socialdapp.network;

import android.os.AsyncTask;

import com.example.socialdapp.LoginActivity;

public class UpdateReqStatusUtils extends AsyncTask<Integer, Void, Void> {
    LoginActivity.status status;

    public UpdateReqStatusUtils(LoginActivity.status status){
        this.status = status;
    }


    @Override
    protected Void doInBackground(Integer... integers) {
        // param 1 -> reqID
        String statusString = "ongoing";
        switch(status){
            case ONGOING:
                statusString = "ongoing";
                break;
            case COMPLETED:
                statusString = "completed";
                break;
            case PAID:
                statusString = "paid";
                break;
            default:
        }
        NetworkUtils.updateRequestStatus(integers[0], statusString);
        return null;
    }
}
