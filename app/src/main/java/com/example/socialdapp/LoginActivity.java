package com.example.socialdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdapp.contracts.Contract;
import com.example.socialdapp.custom.Request;
import com.example.socialdapp.network.InsertUserUtils;
import com.example.socialdapp.network.NetworkUtils;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText keyEditText;
    private TextView errorTextView;
    public static int userID;

    public static enum status{ONGOING, COMPLETED, PAID}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check storage for credentials
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.credentials),
                Context.MODE_PRIVATE);
        if(sharedPref.contains(getString(R.string.credential_name)) && sharedPref.contains(getString(R.string.credential_key))) {
            userID = sharedPref.getInt(getString(R.string.CredentialID), -1);
            loadContract(sharedPref.getString(getString(R.string.credential_key), ""));
            launchMainFeed();
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.remove(getString(R.string.credential_name));
//            editor.remove(getString(R.string.credential_key));
//            editor.remove(getString(R.string.CredentialID));
//            editor.commit();
        }

        setContentView(R.layout.activtity_login);

        nameEditText = findViewById(R.id.name_editText);
        keyEditText = findViewById(R.id.privateKey_editText);
        errorTextView = findViewById(R.id.error_textView);
    }

    public void logIn(View view) {
        String privateKey = keyEditText.getText().toString();
        // todo call log in function from smart contract
        loadContract(privateKey);
        if(!userExists()){
            Toast.makeText(this, "User not Found", Toast.LENGTH_SHORT).show();
            return;
        }
        userID = getUserID();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here
                String jsonString = NetworkUtils.getUser(userID);
                String name = null;
                try {
                    JSONObject object = new JSONObject(jsonString);
                    name = object.getString("_username");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("name", name);
                saveCredentials(name, privateKey, userID);
            }
        });


        // save Name and key to storage

        launchMainFeed();
    }

    public void registerUser(View view) {
        String name = nameEditText.getText().toString();
        String privateKey = keyEditText.getText().toString();

        // deploy contract
//        Contract.getCredentialsFromPrivateKey(privateKey);
//        Contract.DeployContract(Contract.getWeb3jInstance());

        loadContract(privateKey);
        if(userExists()){
            Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Contract.contract.registerUser(name).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        userID = getUserID();

        //save Name and key to storage
        new InsertUserUtils().execute(name);
        saveCredentials(name, privateKey, userID);
//        launchMainFeed();
    }

    private void saveCredentials(String name, String privateKey, int id){
        SharedPreferences sharedPref= this.getSharedPreferences(getString(R.string.credentials),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.credential_name), name);
        editor.putString(getString(R.string.credential_key), privateKey);
        editor.putInt(getString(R.string.CredentialID), id);
        editor.apply();
    }

    private void launchMainFeed(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void setErrorMessage(){
        errorTextView.setText("User already registered! Please Log in instead");
    }

    private void loadContract(String privateKey){
        Contract.getCredentialsFromPrivateKey(privateKey);
        Contract.loadContract(Contract.getWeb3jInstance());
    }

    private boolean userExists(){
        Object response = null;
        try {
            response = Contract.contract.userExists(Contract.userCredentials.getAddress())
                    .sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("userExists", response.toString());

        if(response.toString().equals("true"))
            return true;
        else if(response.toString().equals("false"))
            return false;
        return false;
    }

    private int getUserID() {
        Object response = null;
        try {
            response = Contract.contract.getId(Contract.userCredentials.getAddress()).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("userid", response.toString());

        return Integer.parseInt(response.toString());
    }
}