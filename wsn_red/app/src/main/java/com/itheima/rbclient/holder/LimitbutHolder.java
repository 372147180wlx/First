package com.itheima.rbclient.holder;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.LimitResponse;
import com.itheima.rbclient.ui.activity.AppDetailActivity;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class LimitbutHolder extends BaseHolder<LimitResponse.ProductListBean> {
    @InjectView(R.id.iv_limit)
    ImageView ivLimit;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.tv_limitPrice)
    TextView tvLimitPrice;
    @InjectView(R.id.tv_leftTime)
    TextView tvLeftTime;
    @InjectView(R.id.bt_limit)
    Button btLimit;


    private Handler handler = new Handler();

    public LimitbutHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_limitbut, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(final LimitResponse.ProductListBean data) {

        tvName.setText(data.name);
        tvPrice.setText("会员价:  " + data.price);
        tvLimitPrice.setText("￥" + data.limitPrice);
        long v = data.leftTime - System.currentTimeMillis();
        Date date = new Date(v);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd 天  HH:mm:ss");
        tvLeftTime.setText("剩余时间:  " + simpleDateFormat.format(date));

        HttpLoader.getInstance(App.context).getImageLoader()
                .get(RBConstants.URL_SERVER + data.pic, ImageLoader.getImageListener(ivLimit, R.drawable.image1, R.drawable.image1));
        btLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(App.context, AppDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("sendpId",data.id);
                App.context.startActivity(intent);
            }
        });
    }

    @OnClick(R.id.bt_limit)
    public void onClick() {
        //点击立即抢购跳转界面

    }
}
