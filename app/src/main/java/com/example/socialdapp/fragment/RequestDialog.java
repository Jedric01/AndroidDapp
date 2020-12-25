package com.example.socialdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.socialdapp.contracts.Contract;
import com.example.socialdapp.LoginActivity;
import com.example.socialdapp.R;
import com.example.socialdapp.ViewRequestActivity;
import com.example.socialdapp.network.DisplayRequestUtils;
import com.example.socialdapp.network.UpdateReqStatusUtils;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class RequestDialog extends DialogFragment {

    public static RequestDialog newInstance(String title, int id, int charge, String status, int merchantID, int position) {

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("id", id);
        args.putInt("charge", charge);
        args.putString("status", status);
        args.putInt("merchantID", merchantID);
        args.putInt("position", position);

        RequestDialog fragment = new RequestDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(1000, 1000);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_dialog_request, container, false);

        TextView titleTv = view.findViewById(R.id.request_title);
        TextView idTv = view.findViewById(R.id.request_id);
        TextView chargeTv = view.findViewById(R.id.dueCharge_tv);
        Button payButton = view.findViewById(R.id.pay_button);

        titleTv.setText(getArguments().getString("title"));
        idTv.setText("RequestID: #" + String.valueOf(getArguments().getInt("id")));
        chargeTv.setText(String.valueOf(getArguments().getInt("charge")));

        payButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo pay in smart contract and change status to paid

                if(ProfileFragment.balance.compareTo(BigInteger.valueOf(getArguments().getInt("charge"))) < 0){
                    Toast.makeText(getContext(), "Not enough Tokens! Please Top Up",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Object response = null;
                try {
                    response = Contract.contract.transfer(
                            BigInteger.valueOf(getArguments().getInt("merchantID")),
                            BigInteger.valueOf(getArguments().getInt("charge"))).sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(response.toString().equals("false")){
                    Toast.makeText(getContext(), "Transaction Failed", Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStackImmediate();
                    return;
                }
                new UpdateReqStatusUtils(LoginActivity.status.PAID).execute(getArguments().getInt("id"));
                Toast.makeText(getContext(), "Transaction successful", Toast.LENGTH_SHORT).show();
                ViewRequestActivity.requests.get(getArguments().getInt("position")).setStatus("paid");
                new DisplayRequestUtils(ViewRequestActivity.adapter).execute(LoginActivity.userID);
                getFragmentManager().popBackStackImmediate();
            }
        });

        if(!getArguments().getString("status").equals("completed")) {
            payButton.setClickable(false);
            payButton.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}
