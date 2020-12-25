package com.example.socialdapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialdapp.LoginActivity;
import com.example.socialdapp.R;
import com.example.socialdapp.adapter.OrderAdapter;
import com.example.socialdapp.adapter.RecyclerTouchListener;
import com.example.socialdapp.custom.Order;
import com.example.socialdapp.network.DisplayOrderUtils;

import java.util.ArrayList;


public class OrdersFragment extends Fragment {
    public static OrderAdapter adapter = new OrderAdapter();
    public static ArrayList<Order> orders;

    public OrdersFragment() {
        // Required empty public constructor
        new DisplayOrderUtils(adapter).execute(LoginActivity.userID);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.orders_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                OrderDialog orderDialog = OrderDialog.newInstance(orders.get(position).getTitle(),
                        orders.get(position).getRequestID());
                orderDialog.show(ft, "orderDialog");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }
}