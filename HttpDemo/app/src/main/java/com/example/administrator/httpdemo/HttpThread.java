package com.example.administrator.httpdemo;

import android.os.Handler;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2015/11/12.
 */
public class HttpThread extends Thread{
    private String url;
    private WebView webView;
    private Handler handler;

    public HttpThread(String url, WebView webView, Handler handler) {
        this.url = url;
        this.webView = webView;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            final StringBuffer sb = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String str;
            while((str = reader.readLine())!= null){
                sb.append(str);
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadData(sb.toString(),"text/html",null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
