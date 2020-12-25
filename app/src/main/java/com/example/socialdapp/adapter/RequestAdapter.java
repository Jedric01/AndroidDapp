package com.example.socialdapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdapp.R;
import com.example.socialdapp.ViewRequestActivity;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView IDTv;
        private TextView chargeTv;
        private TextView statusTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.request_title);
            IDTv = itemView.findViewById(R.id.request_id);
            chargeTv = itemView.findViewById(R.id.dueCharge_tv);
            statusTv = itemView.findViewById(R.id.statusRequest_tv);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.request_row_item, parent, false);

        return new RequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTv.setText(ViewRequestActivity.requests.get(position).getRequestTitle());
        holder.IDTv.setText("Request: #" + String.valueOf(ViewRequestActivity.requests.get(position).getRequestID()));
        holder.chargeTv.setText(String.valueOf(ViewRequestActivity.requests.get(position).getPrice()));
        holder.statusTv.setText(ViewRequestActivity.requests.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return ViewRequestActivity.requests.size();
    }
}
