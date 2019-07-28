package com.syncx.app.Klear.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syncx.app.Klear.R;
import com.syncx.app.Klear.models.Orders;

import java.util.ArrayList;

public class OrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerAdapter.ViewHolder> {
   private ArrayList<Orders>mOrders = new ArrayList<>();
   private OnOrderListener mOnOrderListener;

    public OrdersRecyclerAdapter(ArrayList<Orders> orders, OnOrderListener onOrderListener) {
        this.mOrders = orders;
        this.mOnOrderListener = onOrderListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_item, viewGroup, false);
        return new ViewHolder(view, mOnOrderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.waste.setText(mOrders.get(i).getWaste());
        viewHolder.cost.setText(mOrders.get(i).getCost());
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView waste, cost;
        OnOrderListener onOrderListener;

        public ViewHolder(@NonNull View itemView, OnOrderListener onOrderListener) {
            super(itemView);
            waste = itemView.findViewById(R.id.wasteText);
            cost = itemView.findViewById(R.id.costText);
            this.onOrderListener = onOrderListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onOrderListener.onOrderClick(getAdapterPosition());
        }
    }

    public interface OnOrderListener {
        void onOrderClick(int position);
    }
}
