package com.keiron.eth.data.accounts.model;

import com.google.gson.annotations.SerializedName;

public class BaseEtherscanResponseModelDto {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}


