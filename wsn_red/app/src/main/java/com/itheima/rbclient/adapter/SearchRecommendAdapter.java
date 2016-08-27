package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.holder.SearchRecommondHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by KCL on 2016/8/6.
 */
public class SearchRecommendAdapter extends AbsBaseAdapter {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public SearchRecommendAdapter(List data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchRecommondHolder(App.context);
        //ddddd
    }
}
