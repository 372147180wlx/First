package com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.itheima.rbclient.R;
import com.itheima.rbclient.ui.fragment.AccountCenterFragment;
import com.itheima.rbclient.ui.fragment.BaseFragment;
import com.itheima.rbclient.ui.fragment.FragmentInstanceManager;
import com.itheima.rbclient.ui.fragment.addCodeFragment;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import butterknife.InjectView;

/**
 * Created by lingxin on 2016/8/7.
 */
public class CouponFragment extends BaseFragment implements HttpLoader.HttpListener {
    @InjectView(R.id.back)
    Button back;
    @InjectView(R.id.add)
    Button add;
/*    @InjectView(R.id.lv_code)
    ListView lv_code;*/
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.more_code, null);
    }

    @Override
    protected void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(AccountCenterFragment.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(addCodeFragment.class));
            }
        });
    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {

    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
/*    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }*/
}
