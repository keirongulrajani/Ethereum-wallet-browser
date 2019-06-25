package com.keiron.eth.smartcontracttest.screens.main.model;


public class MainViewState {

    private final boolean loading;
    private final Error error;
    private MainUiModel mainUiModel;

    public MainViewState(boolean loading, Error error, MainUiModel mainUiModel) {

        this.loading = loading;
        this.error = error;
        this.mainUiModel = mainUiModel;
    }

    public boolean isLoading() {
        return loading;
    }

    public Error getError() {
        return error;
    }

    public MainUiModel getMainUiModel() {
        return mainUiModel;
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
