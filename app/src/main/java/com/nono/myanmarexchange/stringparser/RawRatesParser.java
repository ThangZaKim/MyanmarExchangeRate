package com.nono.myanmarexchange.stringparser;

import com.nono.myanmarexchange.model.ExchangeRateModel;

import org.apache.commons.lang3.StringUtils;

public class RawRatesParser {
    public static ExchangeRateModel parse(String str) {
        if (str != null) {
            return new ExchangeRateModel(StringUtils.substringBefore(str, "BUY").trim(),
                    StringUtils.substringBetween(str, "BUY", "SELL").trim(),
                    StringUtils.substringAfter(str, "SELL").trim());
        }
        return null;
    }
}
