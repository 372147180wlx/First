package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.BrandResponse;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class GridViewHolder extends BaseHolder<Object> {
    @InjectView(R.id.iv_brand_image1)
    ImageView ivBrandImage1;

    public GridViewHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_info_image, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(Object data) {

        BrandResponse.BrandBean.ValueBean valueBean= (BrandResponse.BrandBean.ValueBean) data;
        HttpLoader.getInstance(App.context).getImageLoader()
                .get(RBConstants.URL_SERVER + valueBean.pic, ImageLoader.getImageListener(ivBrandImage1, R.drawable.image1, R.drawable.image1));


    }
}
