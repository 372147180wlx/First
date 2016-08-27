package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.TopicPlistResponse;
import com.itheima.rbclient.holder.TopicPlistHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;


public class TopicPlistAdapter extends AbsBaseAdapter<TopicPlistResponse.ProductListBean> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public TopicPlistAdapter(List<TopicPlistResponse.ProductListBean> data) {
        super(data);

    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicPlistHolder(App.context);
    }
}
