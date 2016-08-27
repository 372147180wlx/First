package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.OrderListResponse;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.utils.ALog;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/10.
 */
public class MyOrderHorder extends BaseHolder<OrderListResponse.OrderListBean> {
    @InjectView(R.id.order_code)
    TextView order_code;
    @InjectView(R.id.money)
    TextView money;
    @InjectView(R.id.status)
    TextView status;
    @InjectView(R.id.time)
    TextView time;
    public MyOrderHorder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_order_list_detail, null);
        ButterKnife.inject(this, view);
        ALog.e("++++++++++++++++++++++++++++++++++");
        return view;
    }

    @Override
    public void bindData(OrderListResponse.OrderListBean data) {
        ALog.e(data+"-------------------------------------");
        order_code.setText("订单编号："+data.getOrderId());
        money.setText(""+data.getPrice());
        time.setText(data.getTime());
        status.setText(data.getStatus());
        super.bindData(data);
    }
}
