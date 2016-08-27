package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.CategoryProductResponse;

import org.senydevpkg.base.BaseHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yanjingpan on 2016/8/5.
 */
public class CategoryProductHolder extends BaseHolder<CategoryProductResponse.ListFilter.ValueList> {
    @InjectView(R.id.tv_name)
    TextView tv_name;

    public CategoryProductHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_category_product, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(CategoryProductResponse.ListFilter.ValueList data) {
        tv_name.setText(data.name);
//        tv_name.setText(data.valueList);
    }
}
