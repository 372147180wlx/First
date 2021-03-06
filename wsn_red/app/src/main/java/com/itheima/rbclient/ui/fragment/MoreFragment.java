package com.itheima.rbclient.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.rbclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MoreFragment extends BaseFragment implements OnClickListener {

    @InjectView(R.id.tel)
    TextView tel;
    private View rl_center;
    private View rl_record;
    private View rl_help;
    private View rl_feedback;
    private View rl_about;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, null);
        rl_center = view.findViewById(R.id.rl_center);
        rl_record = view.findViewById(R.id.rl_record);
        rl_help = view.findViewById(R.id.rl_help);
        rl_feedback = view.findViewById(R.id.rl_feedback);
        rl_about = view.findViewById(R.id.rl_about);

        rl_center.setOnClickListener(this);
        rl_record.setOnClickListener(this);
        rl_help.setOnClickListener(this);
        rl_feedback.setOnClickListener(this);
        rl_about.setOnClickListener(this);

        return view;
    }

    @Override
    protected void initData() {
        tel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();//创建意图
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel://400-12345678"));
                startActivity(intent);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_center:
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(AccountCenterFragment.class));
                break;
            case R.id.rl_record:
                requestNetData();
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(RecordFragment.class));
                break;
            case R.id.rl_help:
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(HelpCenterFragment.class));
                break;
            case R.id.rl_feedback:
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(FeedBackFragment.class));
                break;
            case R.id.rl_about:
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(AboutFragment.class));
                break;
        }
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
