package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.SearchResult;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by KCL on 2016/8/7.
 */
public class SearchResultHolder extends BaseHolder<SearchResult.ProductListBean> {
    @InjectView(R.id.iv_searchresult_image)
    ImageView iv_searchresult_image;
    @InjectView(R.id.tv_searchresult_name)
    TextView  tv_searchresult_name;
    @InjectView(R.id.tv_new_price)
    TextView tv_new_price;
    @InjectView(R.id.tv_old_price)
    TextView tv_old_price;
    @InjectView(R.id.tv_talk)
    TextView tv_talk;
//    private TextView tv_new_price;
//    private TextView tv_searchresult_name;
//    private TextView tv_old_price;
//    private TextView tv_talk;
//    private ImageView iv_searchresult_image;

    public SearchResultHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_search_result, null);
        ButterKnife.inject(this,view);
//        tv_new_price =(TextView) view.findViewById(R.id.tv_new_price);
//        tv_old_price =(TextView) view.findViewById(R.id.tv_old_price);
//        tv_searchresult_name =(TextView) view.findViewById(R.id.tv_searchresult_name);
//        tv_talk =(TextView) view.findViewById(R.id.tv_talk);
//        iv_searchresult_image =(ImageView) view.findViewById(R.id.iv_searchresult_image);

        return view;
    }

    @Override
    public void bindData(SearchResult.ProductListBean data) {
        tv_searchresult_name.setText(data.getName());
        tv_new_price.setText(data.getMarketPrice()+"");
        tv_old_price.setText(data.getPrice()+"");
        tv_talk.setText("已经有998人评价");
        HttpLoader.getInstance(App.context).getImageLoader().get(RBConstants.URL_SERVER +data.getPic(), ImageLoader.getImageListener(iv_searchresult_image, R.drawable.product_loading, R.drawable.product_loading));
       // HttpLoader.getInstance(App.context).display( iv_searchresult_image, RBConstants.URL_SERVER+data.getPic());
    }
}
