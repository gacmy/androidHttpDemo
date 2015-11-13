package com.example.administrator.volleydemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


class MainActivity extends AppCompatActivity {

    private ImageView iv_img;
    private VolleyInterface vif;
    private NetworkImageView niv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_img = (ImageView)findViewById(R.id.id_iv_img);
        niv = (NetworkImageView)findViewById(R.id.id_newWorkImageView);
        imgRequest();
       // imgRequest1();
        //volley_get();
    }

  private void netImageView(){
      String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
      ImageLoader loader = new ImageLoader(MyApplication.getHttpQueues(),new BitmapCache());
      niv.setDefaultImageResId(R.drawable.ic_launcher);//加载中 显示默认图片
      niv.setErrorImageResId(R.drawable.ic_launcher);//加载失败 显示的图片
      niv.setImageUrl(url,loader);
  }
    private void imgRequest1(){
        String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
        ImageLoader loader = new ImageLoader(MyApplication.getHttpQueues(),new BitmapCache());
        //第一个为图片控件 加载的图片 加载出错的图片
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv_img,R.drawable.ic_launcher,R.drawable.ic_launcher);
        loader.get(url,listener,100,100);//设置最大高度宽度进行压缩处理
    }

    //图片请求
    private void imgRequest(){
        String url="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                iv_img.setImageBitmap(bitmap);
            }
        },100,100, Bitmap.Config.RGB_565,new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                iv_img.setBackgroundResource(R.drawable.ic_launcher);
            }
        });

        MyApplication.getHttpQueues().add(request);

    }

    private void test_volleyget(){
        String url="";
        String tag="";
        VolleyRequest.requestGet(this, url, tag, new VolleyInterface(this,VolleyInterface.mListener,VolleyInterface.mErrorListener) {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
    }

    private void volley_post(){
       // String url = "";
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
//        request.setTag("post");
//        MyApplication.getHttpQueues().add(request);
        /*******************************************************
         *
         */
        String url = "";

        Map<String,String> hashMap =new HashMap<String,String>();
        hashMap.put("name","gac");
        hashMap.put("pwd","123");
        JSONObject jsonObject = new JSONObject(hashMap);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        request.setTag("get");
        MyApplication.getHttpQueues().add(request);
    }


    private void volley_get(){
        //StringRequest请求
        String url = "";
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//
//            }
//        },new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
//        request.setTag("get");
//        MyApplication.getHttpQueues().add(request);

        //jsonObjectRequest 请求
        //null jsonObject 传递给服务器的参数 post请求需要设置参数
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url, null ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

            }},new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> hashMap =new HashMap<String,String>();
                hashMap.put("name","gac");
                hashMap.put("pwd","123");
                return hashMap;
            }
        };
        jsonObjectRequest.setTag("get");
        MyApplication.getHttpQueues().add(jsonObjectRequest);

        //jsonArrayRequest 与上面类似

    }

    //activity 关闭取消请求
    @Override
    protected void onStop() {
        super.onStop();
        String tag = "get";
        MyApplication.getHttpQueues().cancelAll("get");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
