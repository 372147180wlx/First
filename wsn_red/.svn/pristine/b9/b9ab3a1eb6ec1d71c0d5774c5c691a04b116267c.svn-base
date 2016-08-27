package com.itheima.rbclient.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.CheckoutResponse;
import com.itheima.rbclient.bean.SendTimeEvent;
import com.itheima.rbclient.protocol.Api;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import de.greenrobot.event.EventBus;

public class CheckTimeActivity extends AppCompatActivity implements HttpLoader.HttpListener,View.OnClickListener {

    private Button btn_back_check;
    private TextView tv_checkout_commontitle;
    private TextView tv_checkcommon_text1;
    private TextView tv_checkcommon_text2;
    private TextView tv_checkcommon_text3;
    private ImageView iv_paymode_iv1;
    private ImageView iv_paymode_iv2;
    private ImageView iv_paymode_iv3;
    /**
     * 记录送货方式的选择状态，1,2,3分别对应3中送货方式,默认为1
     */
    private int modeState = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_common);
        tv_checkout_commontitle = (TextView) findViewById(R.id.tv_checkout_commontitle);
        tv_checkcommon_text1 = (TextView) findViewById(R.id.tv_checkcommon_text1);
        tv_checkcommon_text2 = (TextView) findViewById(R.id.tv_checkcommon_text2);
        tv_checkcommon_text3 = (TextView) findViewById(R.id.tv_checkcommon_text3);
        iv_paymode_iv1 = (ImageView) findViewById(R.id.iv_paymode_iv1);
        iv_paymode_iv2 = (ImageView) findViewById(R.id.iv_paymode_iv2);
        iv_paymode_iv3 = (ImageView) findViewById(R.id.iv_paymode_iv3);
        btn_back_check = (Button) findViewById(R.id.btn_back_check);
        btn_back_check.setOnClickListener(this);
        tv_checkcommon_text1.setOnClickListener(this);
        tv_checkcommon_text2.setOnClickListener(this);
        tv_checkcommon_text3.setOnClickListener(this);
        iniData();
    }

    /**
     * 初始化数据
     */
    private void iniData() {
        requestNetData();
    }

    protected void requestNetData() {

        //请求数据
        Api.getCheckoutData(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_CHECKOUT && response instanceof CheckoutResponse) {
            CheckoutResponse resp = (CheckoutResponse) response;
            tv_checkout_commontitle.setText("送货时间");
            tv_checkcommon_text1.setText(resp.deliveryList.get(0).getDes());
           tv_checkcommon_text2.setText(resp.deliveryList.get(1).getDes());
            tv_checkcommon_text3.setText(resp.deliveryList.get(2).getDes());
        }
    }
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_back_check:   //返回

                break;
            case R.id.tv_checkcommon_text1:  //送货方式1
                modeState = 1;
                iv_paymode_iv1.setVisibility(View.VISIBLE);
                iv_paymode_iv2.setVisibility(View.INVISIBLE);
                iv_paymode_iv3.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_checkcommon_text2:  //送货方式2
                modeState = 2;
                iv_paymode_iv1.setVisibility(View.INVISIBLE);
                iv_paymode_iv2.setVisibility(View.VISIBLE);
                iv_paymode_iv3.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_checkcommon_text3:  //送货方式3
                modeState = 3;
                iv_paymode_iv1.setVisibility(View.INVISIBLE);
                iv_paymode_iv2.setVisibility(View.INVISIBLE);
                iv_paymode_iv3.setVisibility(View.VISIBLE);
                break;
        }
        EventBus.getDefault().postSticky(new SendTimeEvent(modeState)); //返回选择的支付方式
        finish();
    }
}
