package com.example.socialdapp.fragment;

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
import androidx.fragment.app.DialogFragment;

import com.example.socialdapp.LoginActivity;
import com.example.socialdapp.R;
import com.example.socialdapp.network.DisplayOrderUtils;
import com.example.socialdapp.network.UpdateReqStatusUtils;

public class OrderDialog extends DialogFragment {

    public static OrderDialog newInstance(String title, int id) {
        
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("id", id);
        
        OrderDialog fragment = new OrderDialog();
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
        View view = inflater.inflate(R.layout.fragment_dialog_orders, container, false);

        TextView title = view.findViewById(R.id.order_title);
        TextView idTv = view.findViewById(R.id.order_id);
        Button finishButton = view.findViewById(R.id.completeOrder_button);

        title.setText(getArguments().getString("title"));
        idTv.setText("Order no. #" +  String.valueOf(getArguments().getInt("id")));

        finishButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateReqStatusUtils(LoginActivity.status.COMPLETED).execute(getArguments().getInt("id"));
                new DisplayOrderUtils(OrdersFragment.adapter).execute(LoginActivity.userID);
                getFragmentManager().popBackStackImmediate();
            }
        });

        return view;
    }
}
