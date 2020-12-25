package com.example.socialdapp.network;

import android.net.Uri;
import android.util.Log;

import com.example.socialdapp.custom.Gig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    static String baseUrl = "http://10.0.2.2:5000";
    static HttpURLConnection urlConnection = null;
    static BufferedReader reader = null;
    static String jsonString = null;

    public static int getCount(){
        int count = 0;
        try{
            Uri uri = Uri.parse(baseUrl).buildUpon()
                    .appendPath("api")
                    .appendPath("gigs")
                    .appendPath("count")
                    .build();

            Log.d("url", uri.toString());

            openConnection(uri);
            getJsonString();

            jsonString = jsonString.replaceAll("\\s+","");
            count = Integer.parseInt(jsonString);
            Log.d("restRequest", Integer.toString(count));
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            urlConnection.disconnect();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public static String getGig(int index) {
        try {
            Uri uri = Uri.parse(baseUrl).buildUpon()
                    .appendPath("api")
                    .appendPath("gigs")
                    .appendPath("index")
                    .appendQueryParameter("_id", Integer.toString(index))
                    .build();

            Log.d("urlIndex", uri.toString());
            openConnection(uri);
            getJsonString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        Log.d("restRequest", jsonString);

        return jsonString;
    }

    public static String getUser(int index) {
        try {
            Uri uri = Uri.parse(baseUrl).buildUpon()
                    .appendPath("api")
                    .appendPath("users")
                    .appendPath("index")
                    .appendQueryParameter("_id", Integer.toString(index))
                    .build();

            Log.d("urlIndex", uri.toString());
            openConnection(uri);

            getJsonString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        Log.d("restRequest", jsonString);

        return jsonString;
    }

    public static void insertGig(String title, String Desc, int merchantID, int price) {
        boolean success = false;
        try {
            // todo change merchantID to query id before inserting into parameter
            Uri uri = Uri.parse(baseUrl).buildUpon()
                    .appendPath("api")
                    .appendPath("gigs")
                    .appendPath("insert")
                    .appendQueryParameter("_gigTitle", title)
                    .appendQueryParameter("_gigDesc", Desc)
                    .appendQueryParameter("_merchantID", String.valueOf(merchantID))
                    .appendQueryParameter("_price", String.valueOf(price))
                    .build();

            Log.d("url", uri.toString());

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("_gigTitle", title)
                    .appendQueryParameter("_gigDesc", Desc)
                    .appendQueryParameter("_merchantID", String.valueOf(merchantID))
                    .appendQueryParameter("_price", String.valueOf(price));
            String query = builder.build().getEncodedQuery();

            openConnection(uri);
            outputStream(query);
            getJsonString();

            if(jsonString.equals("True"))
                success = true;

//            Log.d("restRequest", String.valueOf(urlConnection.getResponseCode()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertUser(String username) {
        boolean success = false;
        try {
            Uri uri = Uri.parse(baseUrl).buildUpon()
                    .appendPath("api")
                    .appendPath("users")
                    .appendPath("insert")
                    .appendQueryParameter("_username", username)
                    .build();

            Log.d("url", uri.toString());

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("_username", username);
            String query = builder.build().getEncodedQuery();

            openConnection(uri);
            outputStream(query);
            getJsonString();

            Log.d("restRequest", String.valueOf(urlConnection.getResponseCode()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int insertRequest(int gigID, int buyerID) {
        int requestRef = -1;
        try {
            Uri uri = Uri.parse(baseUrl).buildUpon()
                    .appendPath("api")
                    .appendPath("requests")
                    .appendPath("insert")
                    .appendQueryParameter("_gigID", String.valueOf(gigID))
                    .appendQueryParameter("_buyerID", String.valueOf(buyerID))
                    .appendQueryParameter("_status", "ongoing")
                    .build();

            Log.d("url", uri.toString());

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("_gigID", String.valueOf(gigID))
                    .appendQueryParameter("_buyerID", String.valueOf(buyerID))
                    .appendQueryParameter("_status", "ongoing");
            String query = builder.build().getEncodedQuery();

            openConnection(uri);
            outputStream(query);
            getJsonString();

            jsonString = jsonString.replaceAll("\\s+","");
            requestRef = Integer.parseInt(jsonString);

            Log.d("restRequest", String.valueOf(urlConnection.getResponseCode()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return requestRef;
    }

    public static void updateUserOrderRef(int merchantID, int requestRef){
        try {
            Uri uri = Uri.parse(baseUrl).buildUpon()
                    .appendPath("api")
                    .appendPath("users")
                    .appendPath("update")
                    .appendPath("orderRef")
                    .appendQueryParameter("_id", String.valueOf(merchantID))
                    .appendQueryParameter("_orderRef", String.valueOf(requestRef))
                    .build();

            Log.d("url", uri.toString());

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("_id", String.valueOf(merchantID))
                    .appendQueryParameter("_orderRef", String.valueOf(requestRef));
            String query = builder.build().getEncodedQuery();

            openConnection(uri);
            outputStream(query);
            getJsonString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateUserRequestRef(int userID, int requestRef){
        try {
            Uri uri = Uri.parse(baseUrl).buildUpon()
                    .appendPath("api")
                    .appendPath("users")
                    .appendPath("update")
                    .appendPath("requestRef")
                    .appendQueryParameter("_id", String.valueOf(userID))
                    .appendQueryParameter("_requestRef", String.valueOf(requestRef))
                    .build();

            Log.d("url", uri.toString());

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("_id", String.valueOf(userID))
                    .appendQueryParameter("_requestRef", String.valueOf(requestRef));
            String query = builder.build().getEncodedQuery();

            openConnection(uri);
            outputStream(query);
            getJsonString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getRequest(int index) {
        try {
            Uri uri = Uri.parse(baseUrl).buildUpon()
                    .appendPath("api")
                    .appendPath("requests")
                    .appendPath("index")
                    .appendQueryParameter("_id", Integer.toString(index))
                    .build();

            Log.d("urlIndex", uri.toString());

            openConnection(uri);
            getJsonString();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        Log.d("restRequest", jsonString);

        return jsonString;
    }

    public static void updateRequestStatus(int reqID, String status){
        try {
            Uri uri = Uri.parse(baseUrl).buildUpon()
                    .appendPath("api")
                    .appendPath("requests")
                    .appendPath("update")
                    .appendPath("status")
                    .appendQueryParameter("_id", String.valueOf(reqID))
                    .appendQueryParameter("_status", String.valueOf(status))
                    .build();

            Log.d("url", uri.toString());

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("_id", String.valueOf(reqID))
                    .appendQueryParameter("_status", String.valueOf(status));
            String query = builder.build().getEncodedQuery();

            openConnection(uri);
            outputStream(query);
            getJsonString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void openConnection(Uri uri) throws IOException {
        URL url = new URL(uri.toString());
        urlConnection = (HttpURLConnection) url.openConnection();
    }

    private static void getJsonString() throws IOException {

        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        reader = new BufferedReader(inputStreamReader);

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }

        if (builder.length() == 0) {
            jsonString = null;
            return;
        }

        jsonString = builder.toString();
    }

    private static void outputStream(String query) throws IOException {
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput( true );

        OutputStream os = urlConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        urlConnection.connect();
    }
}
