package com.example.socialdapp.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.socialdapp.ViewRequestActivity;
import com.example.socialdapp.adapter.RequestAdapter;
import com.example.socialdapp.custom.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayRequestUtils extends AsyncTask<Integer, Void, Void> {
    private RequestAdapter adapter;

    public DisplayRequestUtils(RequestAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ViewRequestActivity.requests = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        // param 1 -> userID
        String jsonString = NetworkUtils.getUser(integers[0]);
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray requestIDs = object.getJSONArray("_requestRef");
            for(int i = requestIDs.length() - 1; i >= 0; i-- ){
                jsonString = NetworkUtils.getRequest(requestIDs.getInt(i));
                object = new JSONObject(jsonString);
                int requestID = object.getInt("_id");
                int gigID = object.getInt("_gigID");
                String status = object.getString("_status");

                jsonString = NetworkUtils.getGig(gigID);
                Log.d("GigString", jsonString);
                object = new JSONObject(jsonString);
                int merchantID = object.getInt("_merchantID");
                String title = object.getString("_gigTitle");
                int price = object.getInt("_price");
                Log.d("priceTag", String.valueOf(price));

                ViewRequestActivity.requests.add(new Request(requestID, price, merchantID, title, status));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.notifyDataSetChanged();
    }
}
