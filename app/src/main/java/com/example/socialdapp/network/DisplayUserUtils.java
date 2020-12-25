package com.example.socialdapp.network;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class DisplayUserUtils extends AsyncTask<Integer, Void, String> {
    private WeakReference<TextView> tv;

    public DisplayUserUtils(TextView tv){
        this.tv = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Integer... integers) {
        return NetworkUtils.getUser(integers[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject object = new JSONObject(s);
            String username = object.getString("_username");
            tv.get().setText("Merchant: " + username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
