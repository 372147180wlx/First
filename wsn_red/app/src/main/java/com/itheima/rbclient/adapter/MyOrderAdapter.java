package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.OrderListResponse;
import com.itheima.rbclient.holder.MyOrderHorder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
public class MyOrderAdapter extends AbsBaseAdapter<OrderListResponse.OrderListBean> {

    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public MyOrderAdapter(List<OrderListResponse.OrderListBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyOrderHorder(App.context);
    }
}
