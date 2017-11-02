package com.mycelium.wallet.activity.modern.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mycelium.wallet.R;
import com.mycelium.wallet.activity.util.ToggleableCurrencyDisplay;


public class GroupTitleViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTitle;
    public ToggleableCurrencyDisplay tvBalance;

    public GroupTitleViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvBalance = (ToggleableCurrencyDisplay) itemView.findViewById(R.id.tvBalance);
    }
}