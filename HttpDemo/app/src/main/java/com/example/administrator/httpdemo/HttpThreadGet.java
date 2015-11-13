package com.example.administrator.httpdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2015/11/12.
 */
public class HttpThreadGet extends Thread {
    String url;
    String name;
    String age;

    public HttpThreadGet(String name, String age, String url) {
        this.name = name;
        this.age = age;
        this.url = url;
    }
    private void doGet(){
        try {
            url =url+"?name="+ URLEncoder.encode(name,"utf-8")+"&age="+age;
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str =reader.readLine())!=null){
                sb.append(str);
            }
            System.out.println("result:"+sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doPost(){
        try {
            //默认已utf-8发送 不需要转码
            url =url+"?name="+name+"&age="+age;
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            OutputStream out = conn.getOutputStream();
            String content="name="+name+"&age="+age;
            out.write(content.getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str =reader.readLine())!=null){
                sb.append(str);
            }
            System.out.println("result:"+sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        doGet();
        doPost();
    }
}
