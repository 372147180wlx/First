package com.itheima.rbclient.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.AppDetailMsgResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

/**
 * Created by ocean on 2016/8/6.
 */
public class AppDetailMessageActivity extends Activity implements View.OnClickListener {

    private TextView tv_detailmsg_name,tv_detailmsg_des;
    private Button bt_detailmsg_back,bt_detailmsg_buy;
    private int getpId;
    private String productdesc;
    private String appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_message);
        //获取传过来的商品id
        getpId = getIntent().getIntExtra("pId",0);
        //获取传来的商品名
        appName = getIntent().getStringExtra("AppName");
        initView();
        requestData();
    }


    /**
     * 初始化参数
     */
    private void initView() {
        bt_detailmsg_back = (Button) findViewById(R.id.bt_detailmsg_back);
//        bt_detailmsg_buy = (Button) findViewById(R.id.bt_detailmsg_buy);
        tv_detailmsg_name = (TextView) findViewById(R.id.tv_detailmsg_name);
        tv_detailmsg_des = (TextView) findViewById(R.id.tv_detailmsg_des);
        //设置点击事件
        bt_detailmsg_back.setOnClickListener(this);
//        bt_detailmsg_buy.setOnClickListener(this);
    }

    /**
     * 获取网络数据
     */
    private void requestData() {
        HttpParams params = new HttpParams();
        params.put("pId", getpId+"");
        HttpLoader.getInstance(this).get(RBConstants.URL_APP_DETAIL_MSG, params, AppDetailMsgResponse.class,
            RBConstants.REQUEST_CODE_APP_DETAIL_MSG, new HttpLoader.HttpListener() {
                @Override
                public void onGetResponseSuccess(int requestCode, IResponse response) {
                    if (requestCode == RBConstants.REQUEST_CODE_APP_DETAIL_MSG && response instanceof AppDetailMsgResponse){
                        //请求成功以后获取商品详情描述信息
                        productdesc = ((AppDetailMsgResponse) response).productdesc;
                        tv_detailmsg_name.setText(appName);
                        if(productdesc != null){
                            tv_detailmsg_des.setText(productdesc);
                        }else{
                            tv_detailmsg_des.setText(appName+"真是太棒了");
                        }
                    }
                }
                @Override
                public void onGetResponseError(int requestCode, VolleyError error) {
                }
            });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_detailmsg_back:
                finish();
                break;
//            case R.id.bt_detailmsg_buy:
//                MyToast.show(this,"成功添加到购物车");
//                break;
        }
    }
}
