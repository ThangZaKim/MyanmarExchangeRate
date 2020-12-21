package com.nono.myanmarexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.nono.myanmarexchange.model.ExchangeRateModel;
import com.nono.myanmarexchange.model.ExchangeRateResponseModel;
import com.nono.myanmarexchange.stringparser.RawRatesParser;
import com.nono.myanmarexchange.webpageparser.ExampleRateParser;
import com.nono.myanmarexchange.webpageparser.KBZExchangeRateParser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            @Override
            public void run() {

                Document doc=JSoupUtil.getData("https://www.kbzbank.com/en/");

                Element et=doc.getElementById("kt-blocks_9a6c6f-55-inline-css");
                Elements ets=doc.select("link[type$=text/css]");

                if(ets!=null){
                    Log.i("elementText",ets.size()+" Size");
                }

                //Log.i("elementText",et.text()+" \n");
                //Log.i("elementText",et.html()+" \n");

            }
        }).start();


      /*  new KBZExchangeRateParser(new KBZExchangeRateParser.ParserResponse() {
            public void onLoading() {
                //KBZBankExchangeRateFragment.this.swipeContainer.setRefreshing(true);
            }

            public void onSuccess(ExchangeRateResponseModel exchangeRateResponseModel) {
                //KBZBankExchangeRateFragment.this.stopLoading();
                if (exchangeRateResponseModel.updatedDate != null) {
                    Log.i("updateData", exchangeRateResponseModel.updatedDate);
                    List<String> list = exchangeRateResponseModel.rawRate;
                    for (String parse : list) {
                        ExchangeRateModel parse2 = RawRatesParser.parse(parse);
                        Log.i("parse", parse2.currency + " " + parse2.buyRate + " " + parse2.sellRate+"\n");
                    }
                    //KBZBankExchangeRateFragment.this.showOnUI(exchangeRateResponseModel);
                    return;
                }
                //KBZBankExchangeRateFragment.this.rlMainLayout.setVisibility(8);
                //KBZBankExchangeRateFragment.this.btnTryAgain.setVisibility(0);
            }
        }).execute(new String[0]);*/

    }
}
