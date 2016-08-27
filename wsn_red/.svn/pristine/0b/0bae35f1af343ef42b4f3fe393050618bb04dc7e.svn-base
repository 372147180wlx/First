package com.itheima.rbclient.holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.RecordResponse;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.utils.ALog;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/9.
 */
public class RecordHolder extends BaseHolder<RecordResponse.ProductBean> {
    @InjectView(R.id.iv_limit)
    ImageView iv_limit;
    @InjectView(R.id.tv_name)
    TextView tv_name;
    @InjectView(R.id.tv_limitPrice)
    TextView tv_limitPrice;
    @InjectView(R.id.commentcount)
    TextView commentcount;
    @InjectView(R.id.tv_marketprice)
    TextView tv_marketprice;
    private String TAG = "record";

    public RecordHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_record, null);
        ButterKnife.inject(this, view);
        Log.d(TAG, "lllllllllll浏览");
        return view;
    }
/*
    public void onEvent(RecordResponse.RecordBean event) {
        tv_name.setText(event.name);
        tv_limitPrice.setText(event.limitPrice);
        commentcount.setText(event.commentCount);
        tv_marketprice.setText(event.marketPrice);
        HttpLoader.getInstance(App.context).getImageLoader()
                .get(RBConstants.URL_SERVER + event.pics, ImageLoader.getImageListener(iv_limit, R.drawable.image1, R.drawable.image1));
    }*/

    @Override
    public void bindData(RecordResponse.ProductBean data) {
        ALog.e("data                              " + data);
        tv_name.setText(data.name);
        tv_limitPrice.setText("" + data.limitPrice);
        commentcount.setText("" + data.commentCount);
        tv_marketprice.setText("" + data.marketPrice);
        HttpLoader.getInstance(App.context).getImageLoader()
                .get(RBConstants.URL_SERVER + data.pics.get(0), ImageLoader.getImageListener(iv_limit, R.drawable.image1, R.drawable.image1));
        super.bindData(data);
    }
}
