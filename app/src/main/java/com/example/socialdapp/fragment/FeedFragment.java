package com.example.socialdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdapp.MainActivity;
import com.example.socialdapp.R;
import com.example.socialdapp.adapter.RecyclerTouchListener;
import com.example.socialdapp.adapter.GigAdapter;
import com.example.socialdapp.custom.Gig;
import com.example.socialdapp.network.DisplayGigsUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {
    private RecyclerView recyclerView;
    private GigAdapter adapter = new GigAdapter();
    private MainActivity mainActivity;
    public static List<Gig> gigs = new ArrayList<>();

    public FeedFragment(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        new DisplayGigsUtils(adapter).execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_layout, container, false);

        recyclerView = view.findViewById(R.id.gig_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        mainActivity.showViewFragment(gigs.get(position).getId(),
                                gigs.get(position).getTitle(),
                                gigs.get(position).getDesc(),
                                gigs.get(position).getMerchantID(),
                                gigs.get(position).getPrice());
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return view;
    }
}
