package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.CartResponse;
import com.itheima.rbclient.holder.CartHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 *
 */
public class CartAdapter extends AbsBaseAdapter<CartResponse.CartBean> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public CartAdapter(List<CartResponse.CartBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CartHolder(App.context) {
        };
    }
}
