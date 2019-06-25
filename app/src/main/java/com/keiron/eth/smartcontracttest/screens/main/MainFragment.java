package com.keiron.eth.smartcontracttest.screens.main;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.keiron.eth.smartcontracttest.R;
import com.keiron.eth.smartcontracttest.di.application.ApplicationComponentHolder;
import com.keiron.eth.smartcontracttest.screens.main.model.MainUiModel;
import com.keiron.eth.smartcontracttest.screens.main.model.MainViewState;
import com.keiron.eth.smartcontracttest.view.LoadingView;
import com.keiron.eth.uicomponents.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class MainFragment extends Fragment {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private MainViewModel mainViewModel;
    private LoadingView progressView;
    private TextView errorText;
    private View container;
    private TextInputEditText addressInput;
    private TextView accountBalanceValue;
    private TextView tokenBalanceValue;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationComponentHolder.getInstance().getApplicationComponent().inject(this);

        mainViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(MainViewModel.class);
        mainViewModel.mainViewState.observe(this, mainViewState -> onMainViewStateChanged(mainViewState));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressView = view.findViewById(R.id.progress);
        errorText = view.findViewById(R.id.errorText);
        container = view.findViewById(R.id.container);

        accountBalanceValue = view.findViewById(R.id.accountBalanceValue);
        tokenBalanceValue = view.findViewById(R.id.tokenBalanceValue);

        addressInput = view.findViewById(R.id.addressInputText);

        addressInput.setOnKeyListener((v, keyCode, event) -> {

            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard();
                mainViewModel.onEthAddressSubmitted(addressInput.getText().toString());
                return true;
            }
            return false;
        });

        container.setVisibility(View.GONE);
        progressView.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void onMainViewStateChanged(MainViewState mainViewState) {
        progressView.setVisibility(mainViewState.isLoading() ? View.VISIBLE : View.GONE);
        errorText.setVisibility(mainViewState.getError() != MainViewState.Error.NONE ? View.VISIBLE : View.GONE);
        errorText.setText(getErrorText(mainViewState.getError()));

        container.setVisibility(mainViewState.getError() == MainViewState.Error.NONE && !mainViewState.isLoading() ? View.VISIBLE : View.GONE);

        MainUiModel mainUiModel = mainViewState.getMainUiModel();
        if (mainUiModel != null) {
            accountBalanceValue.setText(mainUiModel.getEthBalance());
            tokenBalanceValue.setText(mainUiModel.getTokenBalance());
        }


    }

    private String getErrorText(MainViewState.Error error) {
        String errorText = "";

        if (error instanceof MainViewState.Error.NetworkIssue) {
            errorText = ((MainViewState.Error.NetworkIssue) error).getTitle();
        }
        return errorText;
    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
