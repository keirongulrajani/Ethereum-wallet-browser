package com.keiron.eth.smartcontracttest.screens.tokens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.keiron.eth.smartcontracttest.R;
import com.keiron.eth.smartcontracttest.di.application.ApplicationComponentHolder;
import com.keiron.eth.smartcontracttest.screens.tokens.adapter.TokenListAdapter;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountUiModel;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountViewState;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountsDetailsNavigationParameters;
import com.keiron.eth.smartcontracttest.view.LoadingView;
import com.keiron.eth.uicomponents.recyclerview.itemdecoration.SimpleDividerItemDecoration;
import com.keiron.eth.uicomponents.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;
import java.util.List;

public class TokenAccountsFragment extends Fragment {

    private static final String ARG_ADDRESS = "address";

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private TokenAccountViewModel tokenAccountViewModel;
    private LoadingView progressView;
    private TextView errorText;
    private View container;
    private RecyclerView tokenList;
    private TokenListAdapter tokenListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationComponentHolder.getInstance().getApplicationComponent().inject(this);

        tokenAccountViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(TokenAccountViewModel.class);

        tokenAccountViewModel.tokenAccountsViewState.observe(this, tokenAccountViewState -> tokenAccountViewStateChanged(tokenAccountViewState));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_token_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressView = view.findViewById(R.id.progress);
        errorText = view.findViewById(R.id.errorText);
        container = view.findViewById(R.id.container);
        tokenList = view.findViewById(R.id.tokenList);

        tokenListAdapter = new TokenListAdapter();
        tokenList.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        tokenList.setAdapter(tokenListAdapter);

        String address = getArguments().getString(ARG_ADDRESS);
        tokenAccountViewModel.onNavigateToView(address);
    }

    private void tokenAccountViewStateChanged(TokenAccountViewState tokenAccountViewState) {

        progressView.setVisibility(tokenAccountViewState.isLoading() ? View.VISIBLE : View.GONE);
        errorText.setVisibility(tokenAccountViewState.getError() != TokenAccountViewState.Error.NONE ? View.VISIBLE : View.GONE);
        errorText.setText(getErrorText(tokenAccountViewState.getError()));

        container.setVisibility(tokenAccountViewState.getError() == TokenAccountViewState.Error.NONE && !tokenAccountViewState.isLoading() ? View.VISIBLE : View.GONE);

        List<TokenAccountUiModel> uiModels = tokenAccountViewState.getUiModels();
        if (!uiModels.isEmpty()) {
            tokenListAdapter.updateUiModels(uiModels);
        }
    }

    private String getErrorText(TokenAccountViewState.Error error) {
        String errorText = "";

        if (error instanceof TokenAccountViewState.Error.NetworkIssue) {
            errorText = ((TokenAccountViewState.Error.NetworkIssue) error).getTitle();
        }
        return errorText;
    }

    public static TokenAccountsFragment newInstance(TokenAccountsDetailsNavigationParameters parameters) {

        Bundle args = new Bundle();
        args.putString(ARG_ADDRESS, parameters.getAddress());
        TokenAccountsFragment fragment = new TokenAccountsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
