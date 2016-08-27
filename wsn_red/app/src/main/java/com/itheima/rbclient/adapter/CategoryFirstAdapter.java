package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.CategoryResponse;
import com.itheima.rbclient.holder.CategoryFirstHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by zhaoqiang on 2016/8/6 0006.
 */
public class CategoryFirstAdapter extends AbsBaseAdapter<CategoryResponse.CategoryBean> {

    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public CategoryFirstAdapter(List<CategoryResponse.CategoryBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryFirstHolder(App.context);
    }
}
