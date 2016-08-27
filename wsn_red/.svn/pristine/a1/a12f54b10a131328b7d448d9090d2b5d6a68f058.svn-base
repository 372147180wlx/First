package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.HelpCenterDetailResponse;
import com.itheima.rbclient.holder.HelpCenterDetailHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/8/7.
 */
public class HelpCenterDetailAdapter extends AbsBaseAdapter<HelpCenterDetailResponse.HelpDetailListBean> {
    public HelpCenterDetailAdapter(List<HelpCenterDetailResponse.HelpDetailListBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HelpCenterDetailHolder(App.context);
    }
}
