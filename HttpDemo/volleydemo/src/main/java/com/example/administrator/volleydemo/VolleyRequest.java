package com.example.administrator.volleydemo;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by Administrator on 2015/11/13.
 */
public class VolleyRequest {
    public static StringRequest stringRequest;
    public static Context context;
    //get方式
    public static void requestGet(Context mContext,String url,String tag,VolleyInterface volleyInterface){
        //先取消 防止重复请求
        MyApplication.getHttpQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET,url,
                volleyInterface.lodingSuccessListener(),volleyInterface.lodingErrorListener());
        stringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(stringRequest);
        MyApplication.getHttpQueues().start();

    }


    public static void requestPost(Context mContext,String url,String tag, final Map<String,String> params,VolleyInterface vif){
        MyApplication.getHttpQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST,url,
                vif.lodingSuccessListener(),vif.lodingErrorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(stringRequest);
        MyApplication.getHttpQueues().start();
    }
}
