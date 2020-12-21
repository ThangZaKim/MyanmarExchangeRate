package com.nono.myanmarexchange.webpageparser;

import android.os.AsyncTask;

import com.nono.myanmarexchange.JSoupUtil;
import com.nono.myanmarexchange.Logger;
import com.nono.myanmarexchange.model.ExchangeRateResponseModel;

import org.jsoup.nodes.Document;

import java.util.ArrayList;


public class AYAExchangeRateParser extends AsyncTask<String, Void, ExchangeRateResponseModel> {
    private static final String AYA_BANK_URL = "https://www.ayabank.com/en_US/";
    private ParserResponse parserResponse;

    public interface ParserResponse {
        void onLoading();

        void onSuccess(ExchangeRateResponseModel exchangeRateResponseModel);
    }

    public AYAExchangeRateParser(ParserResponse parserResponse2) {
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
        Document data = JSoupUtil.getData(AYA_BANK_URL);
        Class<?> cls = getClass();
        Logger.d(cls, "AYA Page Document >> " + data);
        if (data != null) {
            str = data.select("#tablepress-104 > tbody > tr.row-1 > td.column-1").text();
            String text = data.select("#tablepress-104 > tbody > tr.row-2 > td.column-3").text();
            String text2 = data.select("#tablepress-104 > tbody > tr.row-2 > td.column-4").text();
            arrayList.add("USDBUY" + text + "SELL" + text2);
            String text3 = data.select("#tablepress-104 > tbody > tr.row-3 > td.column-3").text();
            String text4 = data.select("#tablepress-104 > tbody > tr.row-3 > td.column-4").text();
            arrayList.add("EURBUY" + text3 + "SELL" + text4);
            String text5 = data.select("#tablepress-104 > tbody > tr.row-4 > td.column-3").text();
            String text6 = data.select("#tablepress-104 > tbody > tr.row-4 > td.column-4").text();
            arrayList.add("SGDBUY" + text5 + "SELL" + text6);
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
