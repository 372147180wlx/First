package com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.MyOrderAdapter;
import com.itheima.rbclient.bean.OrderListResponse;
import com.itheima.rbclient.bean.orderinfo;
import com.itheima.rbclient.ui.fragment.AccountCenterFragment;
import com.itheima.rbclient.ui.fragment.BaseFragment;
import com.itheima.rbclient.ui.fragment.FragmentInstanceManager;
import com.itheima.rbclient.ui.fragment.MyOrderDetailFragment;
import com.itheima.rbclient.utils.Preutils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.ALog;
import org.senydevpkg.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by lingxin on 2016/8/7.
 */
public class MyOrderFragment extends BaseFragment implements HttpLoader.HttpListener {
    private static final String TAG = "MyOrderFragment";
    @InjectView(R.id.radio)
    RadioGroup radio;

    @InjectView(R.id.rb_1)
    RadioButton rb_1;
    @InjectView(R.id.rb_2)
    RadioButton rb_2;
    @InjectView(R.id.rb_3)
    RadioButton rb_3;
    @InjectView(R.id.back)
    Button back;
    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.iv)
    ImageView iv;
    private MyOrderAdapter adapter;
    private List<OrderListResponse.OrderListBean> list;
    private HttpParams params;
    private String orderId;
    private String userid;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.more_myorder, null);

    }

    @Override
    protected void initData() {
        userid = Preutils.getString(App.context, "userid", "20428");
        list = new ArrayList<>();
        adapter = new MyOrderAdapter(list);
        lv.setAdapter(adapter);
        params = new HttpParams();
        params.addHeader("userid", userid);
        params.put("type", "1").put("page", "0").put("pageNum", "1000");
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        ALog.e("id" + radio.getCheckedRadioButtonId());
                        params.put("type", "1").put("page", "0").put("pageNum", "1000");
                        requestNetData();
                        break;
                    case R.id.rb_2:
                        ALog.e("id" + radio.getCheckedRadioButtonId());
                        params.put("type", "2").put("page", "0").put("pageNum", "1000");
                        getconnection();
                        break;
                    case R.id.rb_3:
                        ALog.e("id" + radio.getCheckedRadioButtonId());
                        params.put("type", "3").put("page", "0").put("pageNum", "1000");
                        getconnection();
                        break;
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(AccountCenterFragment.class));
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                requestNetData();
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MyOrderDetailFragment.class));
                orderId = list.get(position).getOrderId();

                EventBus.getDefault().postSticky(new orderinfo(orderId, position));
            }
        });
    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {
        //   Api.getMyOrderData(this).setTag(this);
        getconnection();
    }

    private Request getconnection() {
        String url = RBConstants.URL_APP_TEST;
        Class<? extends IResponse> clazz = OrderListResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_TEST;
        return App.HL.get(url, params, clazz, requestCode, this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_TEST && response instanceof OrderListResponse) {
            // OrderListResponse bean = ((OrderListResponse) response);
            list = ((OrderListResponse) response).getOrderList();
            ALog.e("---8765432100000000000" + list);
            if (userid == null) {
                MyToast.show(App.context, "请先登录！");
                return;
            }
            if (list.size() == 0) {
                iv.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged(list);
                ALog.e("---876543211111111111111111" + list);
                return;
            } else {
                iv.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged(list);
                return;
            }

        }
           adapter.notifyDataSetChanged(list);
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        //   ALog.e("---12345678");
    }

    @Override
    public void onStart() {
        super.onStart();
        requestNetData();
    }

}
