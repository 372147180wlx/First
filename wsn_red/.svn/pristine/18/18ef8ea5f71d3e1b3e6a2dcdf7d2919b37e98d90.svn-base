package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;

import org.senydevpkg.base.BaseHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class BrandTitleHolder extends BaseHolder<Object> {
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    public BrandTitleHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_title_brand, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(Object data) {
        tvTitle.setText((String) data);
    }
}
