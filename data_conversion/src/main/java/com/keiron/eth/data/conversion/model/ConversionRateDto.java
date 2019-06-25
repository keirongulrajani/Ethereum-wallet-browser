package com.keiron.eth.data.conversion.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ConversionRateDto {

    @SerializedName("pair")
    private String pair;

    @SerializedName("rate")
    private BigDecimal rate;

    @SerializedName("minerFee")
    private BigDecimal minerFee;

    @SerializedName("limit")
    private BigDecimal limit;

    @SerializedName("minimum")
    private BigDecimal minimum;

    @SerializedName("maxLimit")
    private BigDecimal maxLimit;

    public String getPair() {
        return pair;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getMinerFee() {
        return minerFee;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getMinimum() {
        return minimum;
    }

    public BigDecimal getMaxLimit() {
        return maxLimit;
    }
}
