package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;

/**
 * Created by Administrator on 2016/8/6.
 */
public class AboutFragment extends BaseFragment {

    private Button back;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(App.context, R.layout.fragment_about,null);
        back = (Button) view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
            }
        });
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {

    }
}
