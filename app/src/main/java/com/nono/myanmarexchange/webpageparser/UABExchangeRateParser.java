package com.nono.myanmarexchange.webpageparser;

import android.os.AsyncTask;

import com.nono.myanmarexchange.JSoupUtil;
import com.nono.myanmarexchange.Logger;
import com.nono.myanmarexchange.model.ExchangeRateResponseModel;

import org.jsoup.nodes.Document;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;


public class UABExchangeRateParser extends AsyncTask<String, Void, ExchangeRateResponseModel> {
    private static final String UAB_BANK_URL = "https://www.uab.com.mm/";
    private ParserResponse parserResponse;

    public interface ParserResponse {
        void onLoading();

        void onSuccess(ExchangeRateResponseModel exchangeRateResponseModel);
    }

    public UABExchangeRateParser(ParserResponse parserResponse2) {
        this.parserResponse = parserResponse2;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.parserResponse.onLoading();
    }

    /* access modifiers changed from: protected */
    public ExchangeRateResponseModel doInBackground(String... strArr) {
        String str;
        ArrayList arrayList = new ArrayList();
        Document data = JSoupUtil.getData(UAB_BANK_URL);
        Class<?> cls = getClass();
        Logger.d(cls, "UAB PageDocument > " + data);
        if (data != null) {
            str = StringUtils.substringAfter(data.select("#block-block-1 > div > div > div > p > span.ex-date").first().text(), "Exchange Rate").trim();
            String text = data.select("#block-block-1 > div > div > div > p > span.ex-usd").first().text();
            String trim = StringUtils.substringBetween(text, "Buy", ",").trim();
            String trim2 = StringUtils.substringAfter(text, "Sell").trim();
            arrayList.add("USDBUY" + trim + "SELL" + trim2);
            String trim3 = StringUtils.substringBetween(data.select("#block-block-1 > div > div > div > p > span.ex-sgd").first().text(), "Buy", ",").trim();
            String trim4 = StringUtils.substringAfter(text, "Sell").trim();
            arrayList.add("SGDBUY" + trim3 + "SELL" + trim4);
            String text2 = data.select("#block-block-1 > div > div > div > p > span.ex-eur").first().text();
            String trim5 = StringUtils.substringBetween(text2, "Buy", ",").trim();
            String trim6 = StringUtils.substringAfter(text2, "Sell").trim();
            arrayList.add("EURBUY" + trim5 + "SELL" + trim6);
        } else {
            str = null;
        }
        return new ExchangeRateResponseModel(str, arrayList);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(ExchangeRateResponseModel exchangeRateResponseModel) {
        super.onPostExecute(exchangeRateResponseModel);
        this.parserResponse.onSuccess(exchangeRateResponseModel);
    }
}
