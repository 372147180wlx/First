package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.HelpCenterResponse;

import org.senydevpkg.base.BaseHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/6.
 */
public class HelpCenterHolder extends BaseHolder<HelpCenterResponse.HelpListBean> {
    @InjectView(R.id.text)
    TextView text;


    public HelpCenterHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_help_center, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(HelpCenterResponse.HelpListBean data) {
        text.setText(data.getTitle());
        super.bindData(data);
    }
}
