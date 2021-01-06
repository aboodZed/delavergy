package com.webapp.delavergy.utils.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.ToolUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersHolder> {

    private Activity activity;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Order> all = new ArrayList<>();

    public OrdersAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public OrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdersHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersHolder holder, int position) {
        holder.setData(orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setAll(ArrayList<Order> all) {
        this.all = all;
        this.orders = all;
        notifyDataSetChanged();
    }

    public void filter(String type, String date) {

    }

    class OrdersHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date_time) TextView tvDateTime;
        @BindView(R.id.cl_background) ConstraintLayout clBackground;
        @BindView(R.id.tv_order_id) TextView tvOrderId;
        @BindView(R.id.tv_order_type) TextView tvOrderType;
        @BindView(R.id.tv_order_status) TextView tvOrderStatus;

        private Order order;

        public OrdersHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Order order) {
            this.order = order;
            tvDateTime.setText(ToolUtils.getDate(order.getCreate_at()) + "\n" +
                    ToolUtils.getTime(order.getCreate_at()));
            tvOrderId.setText(String.valueOf(order.getId()));
            tvOrderStatus.setText(order.getStatus());
            tvOrderType.setText(order.getType());
        }

        @OnClick(R.id.cl_background)
        public void open() {
            NavigateUtils.openOrder(activity, order.getId(), true);
        }
    }
}
