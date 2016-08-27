package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.CheckoutResponse;
import com.itheima.rbclient.holder.CheckOutHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 *
 */
public class CheckOutAdapter extends AbsBaseAdapter<CheckoutResponse.ProductListBean> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public CheckOutAdapter(List<CheckoutResponse.ProductListBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CheckOutHolder(App.context) {
        };
    }
}
