package com.example.socialdapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdapp.R;
import com.example.socialdapp.fragment.OrdersFragment;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView gigTitle;
        public TextView buyerID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gigTitle = itemView.findViewById(R.id.order_title);
            buyerID = itemView.findViewById(R.id.order_id);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.order_row_item, parent, false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.gigTitle.setText(OrdersFragment.orders.get(position).title);
        holder.buyerID.setText("buyerID: #" + String.valueOf(OrdersFragment.orders.get(position).buyerID));
    }

    @Override
    public int getItemCount() {
        return OrdersFragment.orders.size();
    }

}
