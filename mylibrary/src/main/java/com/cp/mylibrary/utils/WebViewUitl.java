package com.cp.mylibrary.utils;

import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 *
 *  webview 工具类
 * Created by Jerry on 2016/12/1.
 */

public class WebViewUitl {


    /**
     * @param webView
     */

    public static void setWebViewSetting(WebView webView) {


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置 缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
// 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        //是否可以缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);


        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);



    }



}
