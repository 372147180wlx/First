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
 * Created by KCL on 2016/8/6.
 */
public class SearchRecommondHolder extends BaseHolder<String> {


    @InjectView(R.id.tv_search_commond)
    TextView tvSearchCommond;

    public SearchRecommondHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_search_commond, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(String data) {
        tvSearchCommond.setText(data);
    }
}
