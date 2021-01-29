package com.webapp.delavergy.utils.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.UIUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersHolder> {

    private Activity activity;
    private ArrayList<Order> orders = new ArrayList<>();

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
        orders.clear();
        this.orders.addAll(all);
        notifyDataSetChanged();
    }

   /*   public void filter(String type, String date, String status) {
      orders.clear();
        notifyDataSetChanged();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getType().equals(type)
                    && UIUtils.getDate(all.get(i).getCreated_timestamp()).equals(date)
                    && all.get(i).getStatus().equals(status)) {
                Log.e(getClass().getName() + ": status", "true");
                orders.add(all.get(i));
                notifyItemInserted(orders.size() - 1);
            }
        }
    }*/

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
            tvDateTime.setText(UIUtils.getDate(order.getCreated_timestamp()) + "\n" +
                    UIUtils.getTime(order.getCreated_timestamp()));
            tvOrderId.setText("#" + order.getInvoice_number());
            tvOrderStatus.setText(order.getStatus_translation());
            tvOrderType.setText(order.getType_translation());
        }

        @OnClick(R.id.cl_background)
        public void open() {
            NavigateUtils.openOrder(activity, order.getId(), true);
        }
    }
}
