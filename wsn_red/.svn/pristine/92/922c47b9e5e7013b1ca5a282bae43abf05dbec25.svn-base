package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.RecordResponse;
import com.itheima.rbclient.holder.RecordHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class RecordAdapter extends AbsBaseAdapter<RecordResponse.ProductBean> {


    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public RecordAdapter(List<RecordResponse.ProductBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("浏览记录adapter");
        return new RecordHolder(App.context);
    }


}
