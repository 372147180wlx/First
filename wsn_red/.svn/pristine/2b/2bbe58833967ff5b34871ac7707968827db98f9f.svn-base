package com.itheima.rbclient.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.InvoiceEvent;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class CheckInvoiceActivity extends AppCompatActivity implements HttpLoader.HttpListener,View.OnClickListener {

    @InjectView(R.id.btn_back_check)
    Button btn_back_check;
    @InjectView(R.id.rl_checkinvoice_rl1)
    RelativeLayout rl_checkinvoice_rl1;
    @InjectView(R.id.rl_checkinvoice_rl2)
    RelativeLayout rl_checkinvoice_rl2;
    @InjectView(R.id.rl_checkinvoice_rl3)
    RelativeLayout rl_checkinvoice_rl3;
    @InjectView(R.id.rl_checkinvoice_rl4)
    RelativeLayout rl_checkinvoice_rl4;
    @InjectView(R.id.rl_checkinvoice_rl5)
    RelativeLayout rl_checkinvoice_rl5;
    @InjectView(R.id.rl_checkinvoice_rl6)
    RelativeLayout rl_checkinvoice_rl6;
    @InjectView(R.id.rl_checkinvoice_rl7)
    RelativeLayout rl_checkinvoice_rl7;

    @InjectView(R.id.iv_checkinvoice_iv1)
    ImageView iv_checkinvoice_iv1;
    @InjectView(R.id.iv_checkinvoice_iv2)
    ImageView iv_checkinvoice_iv2;
    @InjectView(R.id.iv_checkinvoice_iv3)
    ImageView iv_checkinvoice_iv3;
    @InjectView(R.id.iv_checkinvoice_iv4)
    ImageView iv_checkinvoice_iv4;
    @InjectView(R.id.iv_checkinvoice_iv5)
    ImageView iv_checkinvoice_iv5;
    @InjectView(R.id.iv_checkinvoice_iv6)
    ImageView iv_checkinvoice_iv6;
    @InjectView(R.id.iv_checkinvoice_iv7)
    ImageView iv_checkinvoice_iv7;

    @InjectView(R.id.tv_checkinvoice_text1)
    TextView tv_checkinvoice_text1;
    @InjectView(R.id.tv_checkinvoice_text2)
    TextView tv_checkinvoice_text2;
    /**
     * 记录点击的选择状态，1,2,3，4,5分别对应内容
     */
    private int contentState = 1;
    private int ttdesState = 1;
    private String ttdes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_invoice);
        ButterKnife.inject(this);
        iniData();

        btn_back_check.setOnClickListener(this);
        rl_checkinvoice_rl1.setOnClickListener(this);
        rl_checkinvoice_rl2.setOnClickListener(this);
        rl_checkinvoice_rl3.setOnClickListener(this);
        rl_checkinvoice_rl4.setOnClickListener(this);
        rl_checkinvoice_rl5.setOnClickListener(this);
        rl_checkinvoice_rl6.setOnClickListener(this);
        rl_checkinvoice_rl7.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void iniData() {
        requestNetData();
    }

    protected void requestNetData() {

        //请求数据
        //Api.getCheckoutData(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {

    }
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onClick(View v) {

        iv_checkinvoice_iv3.setVisibility(View.INVISIBLE);
        iv_checkinvoice_iv4.setVisibility(View.INVISIBLE);
        iv_checkinvoice_iv5.setVisibility(View.INVISIBLE);
        iv_checkinvoice_iv6.setVisibility(View.INVISIBLE);
        iv_checkinvoice_iv7.setVisibility(View.INVISIBLE);
        switch (v.getId()) {
            case R.id.btn_back_check:
                if(ttdesState == 1) {
                    ttdes = (String) tv_checkinvoice_text1.getText();
                }else {
                    ttdes = (String) tv_checkinvoice_text2.getText();
                }
                EventBus.getDefault().postSticky(new InvoiceEvent(ttdes,contentState)); //返回选择的支付方式
                finish();
                break;
            case R.id.rl_checkinvoice_rl1:
                ttdesState = 1;
                iv_checkinvoice_iv1.setVisibility(View.VISIBLE);
                iv_checkinvoice_iv2.setVisibility(View.INVISIBLE);
                break;
            case R.id.rl_checkinvoice_rl2:
                ttdesState = 2;
                iv_checkinvoice_iv1.setVisibility(View.INVISIBLE);
                iv_checkinvoice_iv2.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_checkinvoice_rl3:
                contentState = 1;
                iv_checkinvoice_iv3.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_checkinvoice_rl4:
                contentState = 2;
                iv_checkinvoice_iv4.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_checkinvoice_rl5:
                contentState = 3;
                iv_checkinvoice_iv5.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_checkinvoice_rl6:
                contentState = 4;
                iv_checkinvoice_iv6.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_checkinvoice_rl7:
                contentState = 5;
                iv_checkinvoice_iv7.setVisibility(View.VISIBLE);
                break;

        }
    }
}
