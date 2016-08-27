package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.TopicResponse;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yanjingpan on 2016/8/5.
 */
public class TopicHolder extends BaseHolder<TopicResponse.TopicBean> {
    @InjectView(R.id.tv_des)
    TextView tvDes;
    @InjectView(R.id.iv_topic_image)
    ImageView ivTopicImage;

    public TopicHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_topic, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(TopicResponse.TopicBean data) {
        tvDes.setText(data.name);
        HttpLoader.getInstance(App.context).getImageLoader()
                .get(RBConstants.URL_SERVER + data.pic, ImageLoader.getImageListener(ivTopicImage, R.drawable.image1, R.drawable.image1));
    }
}
