package com.webapp.delavergy.utils.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Notification;
import com.webapp.delavergy.utils.ToolUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    private Activity activity;
    private ArrayList<Notification> notifications = new ArrayList<>();

    public NotificationAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
        holder.setData(notifications.get(position));
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    class NotificationHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title) TextView tvTitle;
        @BindView(R.id.iv_read_it) ImageView ivReadIt;
        @BindView(R.id.tv_date) TextView tvDate;
        @BindView(R.id.tv_details) TextView tvDetails;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Notification notification) {
            tvTitle.setText(notification.getNotificationData().getTitle());
            tvDate.setText(ToolUtils.getDate(notification.getCreated_at()));
            tvDetails.setText(notification.getNotificationData().getMsg());
            if (notification.getRead_at() != null) {
                ivReadIt.setVisibility(View.VISIBLE);
            }
        }
    }
}
