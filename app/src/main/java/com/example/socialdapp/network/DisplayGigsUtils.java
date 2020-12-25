package com.example.socialdapp.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.socialdapp.fragment.FeedFragment;
import com.example.socialdapp.custom.Gig;
import com.example.socialdapp.adapter.GigAdapter;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

public class DisplayGigsUtils extends AsyncTask<Void, Void, Integer> {
    private ArrayList<String> stringResponse;
    private GigAdapter adapter;

    public DisplayGigsUtils(GigAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        stringResponse = new ArrayList<>();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        int count = NetworkUtils.getCount();
        Log.d("doc_count", Integer.toString(count));
        for(int i = count - 1, j = 0; i >= 0; i--, j++){
            stringResponse.add(NetworkUtils.getGig(i));
        }

        return count;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        FeedFragment.gigs.clear();

        for(int i = 0; i < integer; i++){
            try {
                JSONObject object = new JSONObject(stringResponse.get(i));
                String title = object.getString("_gigTitle");
                String desc = object.getString("_gigDesc");
                int merchantID = object.getInt("_merchantID");
                int price = object.getInt("_price");
                int id = object.getInt("_id");
                Log.d("gigid", String.valueOf(id));
                FeedFragment.gigs.add(new Gig(id, title, desc, merchantID, price));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter.notifyDataSetChanged();
    }


}
