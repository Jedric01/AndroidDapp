package com.example.socialdapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.socialdapp.adapter.RecyclerTouchListener;
import com.example.socialdapp.adapter.RequestAdapter;
import com.example.socialdapp.custom.Request;
import com.example.socialdapp.fragment.RequestDialog;
import com.example.socialdapp.network.DisplayRequestUtils;

import java.util.ArrayList;

public class ViewRequestActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static RequestAdapter adapter;
    public static ArrayList<Request> requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);

        adapter = new RequestAdapter();

        recyclerView = findViewById(R.id.requests_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                RequestDialog requestDialog = RequestDialog.newInstance(requests.get(position).getRequestTitle(),
                        requests.get(position).getRequestID(),
                        requests.get(position).getPrice(),
                        requests.get(position).getStatus(),
                        requests.get(position).getMerchantID(),
                        position);
                requestDialog.show(ft, "requestDialog");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        new DisplayRequestUtils(adapter).execute(LoginActivity.userID);
    }
}