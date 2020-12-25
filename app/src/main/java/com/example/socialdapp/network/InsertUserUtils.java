package com.example.socialdapp.network;

import android.os.AsyncTask;

public class InsertUserUtils extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... strings) {
        NetworkUtils.insertUser(strings[0]);
        return null;
    }
}
