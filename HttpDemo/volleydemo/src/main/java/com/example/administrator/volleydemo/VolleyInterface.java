package com.example.administrator.volleydemo;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Objects;

/**
 * Created by Administrator on 2015/11/13.
 */
public abstract class VolleyInterface {
    public Context mContext;
    public static Response.Listener<String> mListener;
    public static Response.ErrorListener mErrorListener;
    public VolleyInterface(Context context,Response.Listener<String> listener,Response.ErrorListener errorListener){
        this.mContext = context;
        this.mListener= listener;
        this.mErrorListener = errorListener;
    }

    //成功的回调方法
    public abstract void onSuccess(String result);
    //失败的回调方法
    public abstract void onError(VolleyError volleyError);

    public Response.Listener<String> lodingSuccessListener(){
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String res) {

                    onSuccess(res);
            }
        };
        return mListener;
    }

    public Response.ErrorListener lodingErrorListener(){
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //进行失败的统一处理
                onError(volleyError);
            }
        };
        return mErrorListener;
    }

}
