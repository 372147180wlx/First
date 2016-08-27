package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.CheckoutResponse;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *
 */
public class CheckOutHolder extends BaseHolder<CheckoutResponse.ProductListBean> {
    @InjectView(R.id.tv_cart_des)
    TextView tvCartDes;
    @InjectView(R.id.iv_cart_image)
    ImageView ivCartImage;
    @InjectView(R.id.tv_cart_count)
    TextView tvCartCount;
    @InjectView(R.id.tv_cart_size)
    TextView tvCartSize;
    @InjectView(R.id.tv_cart_color)
    TextView tvCartColor;
    @InjectView(R.id.tv_cart_price)
    TextView tvCartPrice;
    @InjectView(R.id.tv_cart_total)
    TextView tvCartTotal;

    public CheckOutHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_cart, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(CheckoutResponse.ProductListBean data) {
        tvCartDes.setText(data.product.name);
        tvCartCount.setText(""+data.prodNum);
        tvCartSize.setText("尺寸："+data.product.productProperty.get(1).v);
        tvCartColor.setText("颜色："+data.product.productProperty.get(0).v);
        tvCartPrice.setText("价格：￥"+data.product.price);
        tvCartTotal.setText("小计：￥"+data.product.price * data.prodNum);
        HttpLoader.getInstance(App.context).getImageLoader().get(RBConstants.URL_SERVER + data.product.pic, ImageLoader.getImageListener(ivCartImage, R.drawable.product_loading, R.drawable.product_loading));
    }

}
