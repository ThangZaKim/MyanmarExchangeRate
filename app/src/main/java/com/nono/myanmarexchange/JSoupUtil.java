package com.nono.myanmarexchange;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class JSoupUtil {
    private static final int TIME_OUT = 600000;
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36";

    public static Document getData(String str) {
        TrustManager[] trustManagerArr = {new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};
        try {
            SSLContext instance = SSLContext.getInstance("SSL");
            instance.init((KeyManager[]) null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
            try {
                return Jsoup.connect(str).userAgent(USER_AGENT).maxBodySize(0).timeout(TIME_OUT).ignoreHttpErrors(true).ignoreContentType(true).followRedirects(true).get();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
