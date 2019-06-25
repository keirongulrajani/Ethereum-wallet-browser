package com.keiron.eth.smartcontracttest.screens.tokens.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.keiron.eth.smartcontracttest.R;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountUiModel;

import java.util.ArrayList;
import java.util.List;

public class TokenListAdapter extends RecyclerView.Adapter<TokenViewHolder> {

    List<TokenAccountUiModel> uiModels = new ArrayList<>();

    @NonNull
    @Override
    public TokenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_token, parent, false);
        return new TokenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TokenViewHolder holder, int position) {
        holder.onBind(uiModels.get(position));
    }

    @Override
    public int getItemCount() {
        return uiModels.size();
    }

    public void updateUiModels(List<TokenAccountUiModel> uiModels) {
        this.uiModels.clear();
        this.uiModels.addAll(uiModels);

        notifyDataSetChanged();
    }
}
