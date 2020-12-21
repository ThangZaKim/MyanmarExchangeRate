package com.nono.myanmarexchange.webpageparser;

import android.os.AsyncTask;
import android.util.Log;

import com.nono.myanmarexchange.JSoupUtil;
import com.nono.myanmarexchange.Logger;
import com.nono.myanmarexchange.model.ExchangeRateResponseModel;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;


public class ExampleRateParser extends AsyncTask<String, Void, ExchangeRateResponseModel> {
    private static final String KBZ_BANK_URL = "https://www.kbzbank.com/en/";
    private ParserResponse parserResponse;

    public interface ParserResponse {
        void onLoading();

        void onSuccess(ExchangeRateResponseModel exchangeRateResponseModel);
    }

    public ExampleRateParser(ParserResponse parserResponse2) {
        this.parserResponse = parserResponse2;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.parserResponse.onLoading();
    }

    /* access modifiers changed from: protected */
    public ExchangeRateResponseModel doInBackground(String... strArr) {
        String str="";
        ArrayList arrayList = new ArrayList();
        Document data = JSoupUtil.getData(KBZ_BANK_URL);
        Class<?> cls = getClass();
        Logger.d(cls, "KBZ PageDocument >> " + data);
        if (data != null) {

            Element et = data.getElementById("");
            Log.i("elementText",et.text());

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
