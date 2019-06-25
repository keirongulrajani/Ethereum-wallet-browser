package com.keiron.eth.smartcontracttest.screens.tokens.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.keiron.eth.smartcontracttest.R;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountUiModel;

public class TokenViewHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView tokenBalance;
    private final TextView tokenBalanceInEth;

    public TokenViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        tokenBalance = itemView.findViewById(R.id.tokenBalance);
        tokenBalanceInEth = itemView.findViewById(R.id.tokenBalanceInEth);
    }

    public void onBind(TokenAccountUiModel uiModel) {
        title.setText(uiModel.getTitle());
        tokenBalance.setText(uiModel.getBalance());
        tokenBalanceInEth.setText(uiModel.getBalanceInEth());
    }
}
