package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.LimitResponse;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class NewproductHolder extends BaseHolder<LimitResponse.ProductListBean> {


    @InjectView(R.id.iv_newproduct)
    ImageView ivNewproduct;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_marketPrice)
    TextView tvMarketPrice;
    @InjectView(R.id.tv_Price)
    TextView tvPrice;

    public NewproductHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_newproduct, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(LimitResponse.ProductListBean data) {
        tvName.setText(data.name);
        tvMarketPrice.setText( " "+ data.marketPrice);
        tvPrice.setText(""+ data.price);
        HttpLoader.getInstance(App.context).getImageLoader()
                .get(RBConstants.URL_SERVER + data.pic, ImageLoader.getImageListener(ivNewproduct, R.drawable.image1, R.drawable.image1));
    }

}
