package com.itheima.rbclient.adapter;

import android.os.Handler;
import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.LimitResponse;
import com.itheima.rbclient.holder.LimitbutHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class LimitbutAdapter extends AbsBaseAdapter<LimitResponse.ProductListBean> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */

    private boolean isPlay;
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            if (!isPlay)
                return ;
            handler.postDelayed(this, 1000);
            notifyDataSetChanged();
        }
    };
    private Handler handler = new Handler();

    public LimitbutAdapter(List<LimitResponse.ProductListBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LimitbutHolder(App.context);
    }

    public void start() {
        isPlay = true;
        runnable.run();
    }

    public void stop(){
        isPlay = false;
    }

}
