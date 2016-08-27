package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.SearchResult;
import com.itheima.rbclient.holder.SearchResultHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by KCL on 2016/8/7.
 */
public class SearchResultAdapter extends AbsBaseAdapter<SearchResult.ProductListBean>{


    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public SearchResultAdapter(List<SearchResult.ProductListBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new SearchResultHolder(App.context);
    }
}
