package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.CategoryProductResponse;
import com.itheima.rbclient.holder.CategoryProductHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by yanjingpan on 2016/8/5.
 */
public class CategoryProductAdapter extends AbsBaseAdapter<CategoryProductResponse.ListFilter.ValueList> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public CategoryProductAdapter(List<CategoryProductResponse.ListFilter.ValueList> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryProductHolder(App.context);
    }
}
