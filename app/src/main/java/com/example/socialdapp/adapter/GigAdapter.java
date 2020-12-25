package com.example.socialdapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdapp.fragment.FeedFragment;
import com.example.socialdapp.R;

public class GigAdapter extends RecyclerView.Adapter<GigAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView gigTitle;
        public TextView gigDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gigTitle = itemView.findViewById(R.id.gig_title);
            gigDesc = itemView.findViewById(R.id.gig_desc);
        }
    }

    @NonNull
    @Override
    public GigAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.gig_row_item, parent, false);
        return new GigAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GigAdapter.ViewHolder holder, int position) {
        holder.gigTitle.setText(FeedFragment.gigs.get(position).getTitle());
        holder.gigDesc.setText(FeedFragment.gigs.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return FeedFragment.gigs.size();
    }
}
