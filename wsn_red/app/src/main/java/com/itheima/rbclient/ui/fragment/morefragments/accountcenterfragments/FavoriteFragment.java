package com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.FavoriteAdapter;
import com.itheima.rbclient.bean.FavoriteResponse;
import com.itheima.rbclient.protocol.Api;
import com.itheima.rbclient.ui.fragment.AccountCenterFragment;
import com.itheima.rbclient.ui.fragment.BaseFragment;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lingxin on 2016/8/7.
 */
public class FavoriteFragment extends BaseFragment implements HttpLoader.HttpListener, View.OnClickListener {
    private static final String TAG = "FavoriteFragment";
    @InjectView(R.id.bt_favorite_left)
    Button btFavoriteLeft;
    @InjectView(R.id.bt_favorite_right)
    Button btFavoriteRight;
    @InjectView(R.id.lv_favorite)
    ListView lvFavorite;
    private List<FavoriteResponse.ProductListBean> userInfos;
    private FavoriteAdapter adapter;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.more_favorite, null);
    }

    @Override
    protected void initData() {
        userInfos = new ArrayList<FavoriteResponse.ProductListBean>();

        //填充数据
        adapter = new FavoriteAdapter(userInfos);
        lvFavorite.setAdapter(adapter);
    }

    /**
     * 点击事件
     */
    @Override
    protected void handleUserInput() {
        btFavoriteLeft.setOnClickListener(this);
        btFavoriteRight.setOnClickListener(this);
    }

    @Override
    protected void requestNetData() {
        //从网络上请求数据
        Api.getFavoriteData(this).setTag(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_FAVORITES_LIST && response instanceof FavoriteResponse) {
            userInfos = ((FavoriteResponse) response).getProductList();
            Log.e(TAG, "拿到数据 啦++++=onGetResponseSuccess=====");
        }
        adapter.notifyDataSetChanged(userInfos);
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {//退回帐户中心
            case R.id.bt_favorite_left:
                swichToChildFragment(new AccountCenterFragment(), true);
                break;
            case R.id.bt_favorite_right://清空收藏列表
                userInfos.clear();
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
