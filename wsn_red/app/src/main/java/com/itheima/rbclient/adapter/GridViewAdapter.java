package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.holder.GridViewHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class GridViewAdapter extends AbsBaseAdapter<Object> {

    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public GridViewAdapter(List<Object> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GridViewHolder(App.context);
    }
}
