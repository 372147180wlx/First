package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.BrandAdapter;
import com.itheima.rbclient.bean.BrandResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 品牌界面
 */
public class BrandFragment extends BaseFragment implements HttpLoader.HttpListener {


    ListView lvBrandList;
    private List<BrandResponse.BrandBean> brand;
    private BrandAdapter adapter;

    private ArrayList<Object> list = new ArrayList<Object>();

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(App.context, R.layout.brand_fragment, null);
        lvBrandList = (ListView) view.findViewById(R.id.lv_brand_list);
        return view;
    }

    @Override
    protected void initData() {
        brand = new ArrayList<>();
        adapter = new BrandAdapter(list);
        lvBrandList.setDividerHeight(0);//去掉分割线
        lvBrandList.setSelector(android.R.color.transparent);//将listveiw的selector设置为透明
        lvBrandList.setAdapter(adapter);

    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {
        HttpParams params = new HttpParams();
        HttpLoader.getInstance(App.context).get(RBConstants.URL_BRAND, params, BrandResponse.class, RBConstants.REQUEST_CODE_BRAND, this).setTag(this);

    }


    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_BRAND && response instanceof BrandResponse) {
            brand = (((BrandResponse) response).brand);
            for (BrandResponse.BrandBean brandBean : brand) {
                list.add(brandBean.key);

                ArrayList<Object> valueBeans = new ArrayList<>();
                for (BrandResponse.BrandBean.ValueBean valueBean : brandBean.value) {
                    valueBeans.add(valueBean);
                }
                list.add(valueBeans);
            }
            adapter.notifyDataSetChanged(list);

        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
        ;
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

    @OnClick(R.id.bt_brand_back)
    public void onClick() {
        mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(HomeFragment.class));
    }
}
