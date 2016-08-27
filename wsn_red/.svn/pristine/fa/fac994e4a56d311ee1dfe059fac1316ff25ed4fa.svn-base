package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.CategoryResponse;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhaoqiang on 2016/8/6 0006.
 */
public class CategoryFirstHolder extends BaseHolder<CategoryResponse.CategoryBean> {

    @InjectView(R.id.tv_category)
    TextView tv_category;
    @InjectView(R.id.tv_tag)
    TextView tv_tag;
    @InjectView(R.id.iv_pic)
    ImageView iv_pic;
    @InjectView(R.id.rl)
    RelativeLayout rl;
    private int parentId;
    private View view;

    public CategoryFirstHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        view = View.inflate(App.context, R.layout.item_category, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(CategoryResponse.CategoryBean data) {
        parentId = data.parentId;
        tv_category.setText(data.name);
        String tag = data.tag;
        if(tag == null || tag.equals("0") || tag.equals("")) {
            tv_tag.setVisibility(View.GONE);
        }else {
            tv_tag.setVisibility(View.VISIBLE);
            tv_tag.setText(tag);
        }
        HttpLoader.getInstance(App.context).getImageLoader().get(RBConstants.URL_SERVER + data.pic, ImageLoader.getImageListener(iv_pic, R.drawable.icon, R.drawable.icon));
    }
}
