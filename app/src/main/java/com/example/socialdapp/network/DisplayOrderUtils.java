package com.example.socialdapp.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.socialdapp.adapter.OrderAdapter;
import com.example.socialdapp.custom.Order;
import com.example.socialdapp.fragment.OrdersFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayOrderUtils extends AsyncTask<Integer, Void, Void> {
    OrderAdapter adapter;

    public DisplayOrderUtils(OrderAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        OrdersFragment.orders = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        // first parameter -> userID
        String jsonString = NetworkUtils.getUser(integers[0]);
        JSONArray requestIDs;
        try {
            JSONObject object = new JSONObject(jsonString);
            requestIDs = object.getJSONArray("_orderRef");
            for(int i = 0; i < requestIDs.length(); i++){
                jsonString = NetworkUtils.getRequest(requestIDs.getInt(i));
                Log.d("requestFields", jsonString);
                object = new JSONObject(jsonString);
                if(!object.getString("_status").equals("ongoing"))
                    continue;

                int requestID = object.getInt("_id");
                int buyerID = object.getInt("_buyerID");
                int gigID = object.getInt("_gigID");
                String status = object.getString("_status");

                jsonString = NetworkUtils.getGig(gigID);
                object = new JSONObject(jsonString);
                String title = object.getString("_gigTitle");

                OrdersFragment.orders.add(new Order(title, requestID, status, buyerID));
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
