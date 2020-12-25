package com.example.socialdapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.socialdapp.contracts.Contract;
import com.example.socialdapp.LoginActivity;
import com.example.socialdapp.NewGigActivity;
import com.example.socialdapp.R;
import com.example.socialdapp.ViewRequestActivity;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class ProfileFragment extends Fragment {
    private static String username = null;
    public static BigInteger balance;
    private TextView usernameTv;
    private TextView balanceTv;
    private Button logOutButton;
    private Button addGigButton;
    private Button viewReqButton;
    private Button topUpButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_layout, container, false);

        usernameTv = view.findViewById(R.id.username_tv);
        logOutButton = view.findViewById(R.id.logOut_button);
        addGigButton = view.findViewById(R.id.addGig_button);
        viewReqButton = view.findViewById(R.id.viewRequests_button);
        topUpButton = view.findViewById(R.id.topUp_button);
        balanceTv = view.findViewById(R.id.balance_textView);
        setBalance();

        if (username == null) {
            Log.d("getUsername", "username is retrieved ");
            SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.credentials),
                    Context.MODE_PRIVATE);
            username = sharedPref.getString(getString(R.string.credential_name), "username");
        }

        logOutButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
            SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.credentials),
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove(getString(R.string.credential_name));
                editor.remove(getString(R.string.credential_key));
                editor.remove(getString(R.string.CredentialID));
                editor.commit();

                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });

        addGigButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewGigActivity.class);
                startActivityForResult(i, 0);
            }
        });

        viewReqButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ViewRequestActivity.class);
                startActivityForResult(i, 0);
            }
        });

        topUpButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    Contract.contract.topUpBalance(BigInteger.valueOf(50)).sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getContext(), "Balance topped Up by 5 tokens!", Toast.LENGTH_SHORT).show();
                setBalance();
            }
        });

        usernameTv.setText(username);

        return view;
    }

    private void setBalance(){
        try {
            balance = Contract.contract.getBalance(Contract.userCredentials.getAddress()).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        balanceTv.setText(balance.toString() + " tokens");
    }
}
