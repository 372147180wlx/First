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
import com.itheima.rbclient.adapter.TopicAdapter;
import com.itheima.rbclient.bean.TopicResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 促销快报
 */
public class TopicFragment extends BaseFragment implements HttpLoader.HttpListener {


    ListView lvTopicList;

    private List<TopicResponse.TopicBean> topic;
    private TopicAdapter adapter;

    protected PullToRefreshListView refreshListView;// 下拉刷新ListView

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getActivity(),
                R.layout.topic_fragment, null);

        refreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_topic_list);
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
        lvTopicList = refreshListView.getRefreshableView();
        setListView();

        return view;
    }

    protected void setRefreshMode() {
        refreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);// 设置既可以上拉
    }

    /**
     * 设置ListView的divider和selector
     */
    protected void setListView() {
        lvTopicList.setDividerHeight(0);//去掉分割线
        lvTopicList.setSelector(android.R.color.transparent);//将listveiw的selector设置为透明
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
        HttpLoader.getInstance(App.context).get(RBConstants.URL_TOPIC, params, TopicResponse.class, RBConstants.REQUEST_CODE_TOPIC, this).setTag(this);
    }


    @Override
    protected void initData() {
        topic = new ArrayList<>();
        adapter = new TopicAdapter(topic);
        lvTopicList.setAdapter(adapter);
        lvTopicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(TopicPlistFragment.class), true);
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
        HttpLoader.getInstance(App.context).get(RBConstants.URL_TOPIC, params, TopicResponse.class, RBConstants.REQUEST_CODE_TOPIC, this).setTag(this);

    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_TOPIC && response instanceof TopicResponse) {

            topic.addAll(((TopicResponse) response).topic);
            adapter.notifyDataSetChanged(topic);

            //完成刷新
            refreshListView.onRefreshComplete();

        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.bt_topic_back)
    public void onClick() {
        mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(HomeFragment.class));
    }
}
