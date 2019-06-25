package com.keiron.eth.data.accounts.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class AccountBalanceDto extends BaseEtherscanResponseModelDto {

    @SerializedName("result")
    private BigDecimal result;

    public BigDecimal getResult() {
        return result;
    }
}