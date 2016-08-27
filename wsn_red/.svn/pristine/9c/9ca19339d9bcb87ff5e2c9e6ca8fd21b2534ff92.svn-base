package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;

/**
 * Created by Administrator on 2016/8/6.
 */
public class HelpFragment extends BaseFragment  implements View.OnClickListener {

    private Button back;
    private RelativeLayout rl_guide;
    private RelativeLayout rl_severce;
    private RelativeLayout rl_function;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(App.context, R.layout.fragemnt_help,null);
        back = (Button) view.findViewById(R.id.back);
        rl_guide = (RelativeLayout) view.findViewById(R.id.rl_guide);
        rl_severce = (RelativeLayout) view.findViewById(R.id.rl_severce);
        rl_function = (RelativeLayout) view.findViewById(R.id.rl_function);
        rl_guide.setOnClickListener(this);
        rl_severce.setOnClickListener(this);
        rl_function.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.rl_guide:
               mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(OthersFragment.class));
               break;
           case R.id.rl_function:
               mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(FunctionFragment.class));
               break;
           case R.id.rl_severce:
               mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(SeverceFragment.class));
               break;
       }
    }
}
