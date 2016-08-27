package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.LimitbutAdapter;
import com.itheima.rbclient.bean.LimitResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class LimitbuyFragment extends BaseFragment implements HttpLoader.HttpListener {


    ListView lvLimitList;
    private List<LimitResponse.ProductListBean> productList;
    private LimitbutAdapter adapter;

    protected PullToRefreshListView refreshListView;// 下拉刷新ListView

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),
                R.layout.limitbuy_fragment, null);

        refreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_limit_list);
        // 1.设置刷新模式
        setRefreshMode();
        // 2.设置刷新监听器
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            /**
             * 下拉刷新和上拉加载更多都会执行该方法，
             * @param refreshView
             */
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                size++;
                getConnection(size, 8);
            }
        });
        //3.获取PullToRefreshListView内部包裹的ListView
        lvLimitList = refreshListView.getRefreshableView();

        return view;
    }

    protected void setRefreshMode() {
        refreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);// 设置既可以上拉
    }

    /**
     * 重新获取数据
     *
     * @param page
     * @param pageNum
     */
    int size = 0;

    public void getConnection(int page, int pageNum) {
        HttpParams params = new HttpParams();
        params.put("page", page + "").put("pageNum", pageNum + "");
        HttpLoader.getInstance(App.context).get(RBConstants.URL_LIMITBUY, params, LimitResponse.class, RBConstants.REQUEST_CODE_LIMITBUY, this).setTag(this);
    }

    @Override
    protected void initData() {
        productList = new ArrayList<>();
        adapter = new LimitbutAdapter(productList);
        lvLimitList.setAdapter(adapter);
        lvLimitList.setSelector(android.R.color.transparent);//将listveiw的selector设置为透明
        lvLimitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //显示抢购条目点击事件跳转
                EventBus.getDefault().postSticky(productList.get(position-1));//跳转获取浏览记录
            }
        });
    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {
        HttpParams params = new HttpParams();
        params.put("page", "0").put("pageNum", "8");
        HttpLoader.getInstance(App.context).get(RBConstants.URL_LIMITBUY, params, LimitResponse.class, RBConstants.REQUEST_CODE_LIMITBUY, this).setTag(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_LIMITBUY && response instanceof LimitResponse) {
            productList.addAll(((LimitResponse) response).productList);
            adapter.notifyDataSetChanged(productList);
            //完成刷新
            refreshListView.onRefreshComplete();
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }


    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.start();
    }


    public void onPause() {
        super.onPause();
        if (adapter != null)
            adapter.stop();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    //返回点击事件
    @OnClick(R.id.bt_limit_back)
    public void onClick() {
        mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(HomeFragment.class));
    }
}
