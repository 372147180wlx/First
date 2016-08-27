package com.itheima.rbclient.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.ui.fragment.*;
import com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments.CouponFragment;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/9.
 */
public class addCodeFragment extends BaseFragment {
    @InjectView(R.id.back_feedback)
    Button back;
    @InjectView(R.id.addcode)
    Button addcode;
    private View view;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = View.inflate(App.context, R.layout.addcode, null);

        return view;
    }

    @Override
    protected void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(CouponFragment.class));
            }
        });
        addcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setTitle("温馨提示")
                        .setMessage("没有此优惠券码，请重新添加！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(CouponFragment.class));
                            }
                        }).create().show();
            }
        });
    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {

    }
}
