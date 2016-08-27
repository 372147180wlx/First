package com.itheima.rbclient.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.StringIDBean;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/8/6.
 */
public class SeverceFragment extends BaseFragment {

    private WebView webview;
    private ProgressBar bar;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(App.context, R.layout.fragment_severce,null);
        webview = (WebView) view.findViewById(R.id.webview);
        bar = (ProgressBar) view.findViewById(R.id.progressBar);
        EventBus.getDefault().registerSticky(this);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);//允许JS运行
      //  settings.setBuiltInZoomControls(true);//显示放大、缩小的按钮
        settings.setUseWideViewPort(true);//双击放大缩小
        //开始加载网页
        webview.loadUrl(RBConstants.URL_HELP_CENTER_DETAIL);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webview.loadUrl(url);//不跳转到浏览器加载，在自己页面加载
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        webview.setWebChromeClient(new WebChromeClient(){
            //页面标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
            //加载进度
            @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    bar.setVisibility(View.VISIBLE);
                    bar.setMax(100);
                    bar.setProgress(newProgress);
                    if (newProgress==100){
                        bar.setVisibility(View.GONE);
                    }
                    super.onProgressChanged(view, newProgress);
            }
        });
        return view;
    }
public void onEvent(StringIDBean bean){
    //StringIDBean stickyEvent = EventBus.getDefault().getStickyEvent(StringIDBean.class);
   // System.out.println("kashnfvis"+bean.id);
    Long id = bean.id;
    if (id==1){
        webview.loadUrl(RBConstants.URL_HELP_CENTER_DETAIL);
    }
}
    @Override
    protected void initData() {

    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {

    }
}
