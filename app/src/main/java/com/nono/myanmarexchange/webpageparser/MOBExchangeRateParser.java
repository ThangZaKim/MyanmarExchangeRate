package com.nono.myanmarexchange.webpageparser;

import android.os.AsyncTask;

import com.nono.myanmarexchange.JSoupUtil;
import com.nono.myanmarexchange.Logger;
import com.nono.myanmarexchange.model.ExchangeRateResponseModel;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import java.util.ArrayList;


public class MOBExchangeRateParser extends AsyncTask<String, Void, ExchangeRateResponseModel> {
    private static final String MOB_BANK_URL = "https://www.mobmyanmar.com/";
    private ParserResponse parserResponse;

    public interface ParserResponse {
        void onLoading();

        void onSuccess(ExchangeRateResponseModel exchangeRateResponseModel);
    }

    public MOBExchangeRateParser(ParserResponse parserResponse2) {
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
        Document data = JSoupUtil.getData(MOB_BANK_URL);
        Class<?> cls = getClass();
        Logger.d(cls, "MOB PageDocument >> " + data);
        if (data != null) {
            str = data.select("body > div.themesflat-boxed > div.flat-top.header-style2 > div > div > div.col-md-11 > div > div > h1").first().text();
            String text = data.select("body > div.themesflat-boxed > div.flat-top.header-style2 > div > div > div.col-md-11 > div > div > div:nth-child(3)").first().text();
            String trim = StringUtils.substringBetween(text, "BUY", "SELL").trim();
            String trim2 = StringUtils.substringAfter(text, "SELL").trim();
            arrayList.add("USDBUY" + trim + "SELL" + trim2);
            String trim3 = StringUtils.substringBetween(data.select("body > div.themesflat-boxed > div.flat-top.header-style2 > div > div > div.col-md-11 > div > div > div:nth-child(4)").first().text(), "BUY", "SELL").trim();
            String trim4 = StringUtils.substringAfter(text, "SELL").trim();
            arrayList.add("SGDBUY" + trim3 + "SELL" + trim4);
            String text2 = data.select("body > div.themesflat-boxed > div.flat-top.header-style2 > div > div > div.col-md-11 > div > div > div:nth-child(5)").first().text();
            String trim5 = StringUtils.substringBetween(text2, "BUY", "SELL").trim();
            String trim6 = StringUtils.substringAfter(text2, "SELL").trim();
            arrayList.add("EURBUY" + trim5 + "SELL" + trim6);
            String text3 = data.select("body > div.themesflat-boxed > div.flat-top.header-style2 > div > div > div.col-md-11 > div > div > div:nth-child(6)").first().text();
            String trim7 = StringUtils.substringBetween(text3, "BUY", "SELL").trim();
            String trim8 = StringUtils.substringAfter(text3, "SELL").trim();
            arrayList.add("THBBUY" + trim7 + "SELL" + trim8);
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
