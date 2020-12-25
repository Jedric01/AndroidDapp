package com.example.socialdapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.socialdapp.LoginActivity;
import com.example.socialdapp.R;
import com.example.socialdapp.network.DisplayUserUtils;
import com.example.socialdapp.network.InsertRequestUtils;


public class ViewGigFragment extends Fragment {
    private String title;
    private String desc;
    private int merchantID;
    private int gigID;
    private int price;

    public ViewGigFragment() {
        // Required empty public constructor
        title = null;
        desc = null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("gigTitle");
            desc = getArguments().getString("gigDesc");
            merchantID = getArguments().getInt("merchantID");
            gigID = getArguments().getInt("gigID");
            price = getArguments().getInt("price");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_gig, container, false);
        TextView titleTv = view.findViewById(R.id.title_view);
        TextView descTv = view.findViewById(R.id.desc_view);
        TextView usernameTv = view.findViewById(R.id.merchant_name);
        TextView priceTv = view.findViewById(R.id.price_tv);
        final Button requestButton = view.findViewById(R.id.requestService);

        titleTv.setText(title);
        descTv.setText(desc);
        priceTv.setText(String.valueOf(price));
        new DisplayUserUtils(usernameTv).execute(merchantID);

        requestButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo change buyerID
                new InsertRequestUtils().execute(gigID, LoginActivity.userID, merchantID);
                requestButton.setClickable(false);
                requestButton.setVisibility(View.INVISIBLE);
            }
        });

        return view;

    }
}