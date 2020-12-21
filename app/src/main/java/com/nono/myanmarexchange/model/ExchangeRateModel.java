package com.nono.myanmarexchange.model;

public class ExchangeRateModel {
    public String buyRate;
    public String currency;
    public String sellRate;

    public ExchangeRateModel(String str, String str2, String str3) {
        this.currency = str;
        this.buyRate = str2;
        this.sellRate = str3;
    }
}
