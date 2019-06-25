package com.keiron.eth.smartcontracttest.screens.tokens.model;

import java.util.List;

public class TokenAccountViewState {

    private final boolean loading;
    private final Error error;
    private List<TokenAccountUiModel> uiModels;

    public TokenAccountViewState(boolean loading, Error error, List<TokenAccountUiModel> uiModels) {

        this.loading = loading;
        this.error = error;
        this.uiModels = uiModels;
    }

    public boolean isLoading() {
        return loading;
    }

    public Error getError() {
        return error;
    }

    public List<TokenAccountUiModel> getUiModels() {
        return uiModels;
    }

    public static class Error {
        public static Error NONE = new Error();

        public static class NetworkIssue extends Error {
            private String title;

            public NetworkIssue(String title) {
                this.title = title;
            }

            public String getTitle() {
                return title;
            }
        }
    }
}
