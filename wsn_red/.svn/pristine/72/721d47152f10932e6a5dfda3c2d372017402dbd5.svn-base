package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.TopicPlistResponse;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yanjingpan on 2016/8/5.
 */
public class TopicPlistHolder extends BaseHolder<TopicPlistResponse.ProductListBean> {


    @InjectView(R.id.iv_image1)
    ImageView ivImage1;
    @InjectView(R.id.tv_plist_desc)
    TextView tvPlistDesc;
    @InjectView(R.id.tv_plist_price)
    TextView tvPlistPrice;

    public TopicPlistHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_topic_plist, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(TopicPlistResponse.ProductListBean data) {
        tvPlistDesc.setText(data.name);
        tvPlistPrice.setText("￥"+data.price);
        HttpLoader.getInstance(App.context).getImageLoader()
                .get(RBConstants.URL_SERVER + data.pic, ImageLoader.getImageListener(ivImage1, R.drawable.image1, R.drawable.image1));
    }
}
