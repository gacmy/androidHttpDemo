package com.example.administrator.httpdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2015/11/12.
 */
public class HttpimgThread extends Thread{
    private String url;
    private ImageView webView;
    private Handler handler;

    public HttpimgThread(String url, ImageView webView, Handler handler) {
        this.url = url;
        this.webView = webView;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            InputStream in = conn.getInputStream();
            FileOutputStream out = null;
            File downloadFile= null;
            String fileName = String.valueOf(System.currentTimeMillis());
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File parent = Environment.getExternalStorageDirectory();
                 downloadFile = new File(parent,fileName);
                out = new FileOutputStream(downloadFile);
            }
            byte[] b = new byte[2*1024];
            int len;
            if(out != null){
                while ((len=in.read(b))!=- 1){
                    out.write(b,0,len);
                }

            }

            final Bitmap bitmap = BitmapFactory.decodeFile(downloadFile.getAbsolutePath());
           handler.post(new Runnable() {
               @Override
               public void run() {
                    webView.setImageBitmap(bitmap);
               }
           });
        }catch (Exception e){

        }
    }
}
