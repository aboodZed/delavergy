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
import com.webapp.delavergy.models.Wallet;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.ToolUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletHolder> {

    private Activity activity;
    private ArrayList<Wallet> wallets = new ArrayList<>();

    public WalletAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public WalletHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WalletHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wallet, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WalletHolder holder, int position) {
        holder.setData(wallets.get(position));
    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }

    public void setWallets(ArrayList<Wallet> wallets) {
        this.wallets = wallets;
        notifyDataSetChanged();
    }

    class WalletHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cl_background) ConstraintLayout clBackground;
        @BindView(R.id.tv_order_id) TextView tvOrderId;
        @BindView(R.id.tv_date) TextView tvDate;
        @BindView(R.id.tv_value) TextView tvValue;

        private Wallet wallet;

        public WalletHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setData(Wallet wallet) {
            this.wallet = wallet;
            tvOrderId.setText(String.valueOf(wallet.getOrder_id()));
            tvDate.setText(ToolUtils.getDate(wallet.getCreate_at()));
            tvValue.setText(String.valueOf(wallet.getValue()));
        }

        @OnClick(R.id.cl_background)
        public void open() {
            NavigateUtils.openOrder(activity, wallet.getOrder_id(), true);
        }

    }
}
