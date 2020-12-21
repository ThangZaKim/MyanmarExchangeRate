package com.nono.myanmarexchange.webpageparser;

import android.os.AsyncTask;

import com.nono.myanmarexchange.JSoupUtil;
import com.nono.myanmarexchange.Logger;
import com.nono.myanmarexchange.model.ExchangeRateResponseModel;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import java.util.ArrayList;


public class KBZExchangeRateParser extends AsyncTask<String, Void, ExchangeRateResponseModel> {
    private static final String KBZ_BANK_URL = "https://www.kbzbank.com/en/";
    private ParserResponse parserResponse;

    public interface ParserResponse {
        void onLoading();
        void onSuccess(ExchangeRateResponseModel exchangeRateResponseModel);
    }

    public KBZExchangeRateParser(ParserResponse parserResponse2) {
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
        Document data = JSoupUtil.getData(KBZ_BANK_URL);
        Class<?> cls = getClass();
        Logger.d(cls, "KBZ PageDocument >> " + data);
        if (data != null) {
            str = StringUtils.substringAfter(data.select("#kt-layout-id_1e4b8a-06 > div > div.wp-block-kadence-column.inner-column-1.kadence-column_6e8702-29 > div > p.has-text-color.has-vivid-red-color").first().text(), "Date").trim().replace("–", "").replace("-", "");
            Class<?> cls2 = getClass();
            Logger.d(cls2, "KBZ Update Date >> " + str);
            String text = data.select("#kt-layout-id_4a792f-59 > div > div.wp-block-kadence-column.inner-column-1.kadence-column_f853c8-08 > div > p:nth-child(2)").first().text();
            String text2 = data.select("#kt-layout-id_4a792f-59 > div > div.wp-block-kadence-column.inner-column-1.kadence-column_f853c8-08 > div > p:nth-child(3)").first().text();
            arrayList.add("USDBUY" + StringUtils.substringAfter(text, "–").trim() + "SELL" + StringUtils.substringAfter(text2, "–").trim());
            String text3 = data.select("#kt-layout-id_4a792f-59 > div > div.wp-block-kadence-column.inner-column-2.kadence-column_52cef0-90 > div > p:nth-child(2)").first().text();
            String text4 = data.select("#kt-layout-id_4a792f-59 > div > div.wp-block-kadence-column.inner-column-2.kadence-column_52cef0-90 > div > p:nth-child(3)").first().text();
            arrayList.add("SGDBUY" + StringUtils.substringAfter(text3, "–").trim() + "SELL" + StringUtils.substringAfter(text4, "–").trim());
            String text5 = data.select("#kt-layout-id_4a792f-59 > div > div.wp-block-kadence-column.inner-column-3.kadence-column_2c52f6-af > div > p:nth-child(2)").text();
            String text6 = data.select("#kt-layout-id_4a792f-59 > div > div.wp-block-kadence-column.inner-column-3.kadence-column_2c52f6-af > div > p:nth-child(3)").text();
            arrayList.add("EURBUY" + StringUtils.substringAfter(text5, "–").trim() + "SELL" + StringUtils.substringAfter(text6, "–").trim());
            String text7 = data.select("#kt-layout-id_4a792f-59 > div > div.wp-block-kadence-column.inner-column-4.kadence-column_254854-b4 > div > p:nth-child(2)").text();
            String text8 = data.select("#kt-layout-id_4a792f-59 > div > div.wp-block-kadence-column.inner-column-4.kadence-column_254854-b4 > div > p:nth-child(3)").text();
            arrayList.add("THBBUY" + StringUtils.substringAfter(text7, "–").trim() + "SELL" + StringUtils.substringAfter(text8, "–").trim());
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
