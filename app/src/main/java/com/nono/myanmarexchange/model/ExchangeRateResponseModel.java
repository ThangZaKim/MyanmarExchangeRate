package com.nono.myanmarexchange.model;

import java.util.List;

public class ExchangeRateResponseModel {
    public List<String> rawRate;
    public String updatedDate;

    public ExchangeRateResponseModel(String str, List<String> list) {
        this.updatedDate = str;
        this.rawRate = list;
    }
}
