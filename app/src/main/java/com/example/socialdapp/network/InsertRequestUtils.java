package com.example.socialdapp.network;

import android.net.Network;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class InsertRequestUtils extends AsyncTask<Integer, Void, Void> {
    @Override
    protected Void doInBackground(Integer... integers) {
        // first parameter -> gigID, second parameter -> userID, third parameter -> merchantID
        int requestID = NetworkUtils.insertRequest(integers[0], integers[1]);
        Log.d("requestID", String.valueOf(requestID));
        NetworkUtils.updateUserRequestRef(integers[1], requestID);
        NetworkUtils.updateUserOrderRef(integers[2], requestID);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
