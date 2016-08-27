package com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.ui.fragment.AccountCenterFragment;
import com.itheima.rbclient.ui.fragment.BaseFragment;
import com.itheima.rbclient.ui.fragment.FragmentInstanceManager;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lingxin on 2016/8/12.
 */
public class LoginSuccessFragment extends BaseFragment {
    @InjectView(R.id.button)
    Button button;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(App.context, R.layout.loginsuccess, null);
        return view;
    }

    @Override
    protected void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(AccountCenterFragment.class));
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
}
