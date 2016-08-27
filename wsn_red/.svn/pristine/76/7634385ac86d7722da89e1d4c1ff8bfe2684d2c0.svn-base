package com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima.rbclient.R;
import com.itheima.rbclient.ui.fragment.AccountCenterFragment;
import com.itheima.rbclient.ui.fragment.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lingxin on 2016/8/7.
 */
public class AddressFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.bt_more_left)
    Button btMoreLeft;
    @InjectView(R.id.bt_more_right)
    Button btMoreRight;
    @InjectView(R.id.et_user)
    EditText etUser;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.et_area)
    EditText etArea;
    @InjectView(R.id.et_detailed)
    EditText etDetailed;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.more_address, null);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void handleUserInput() {
        btMoreLeft.setOnClickListener(this);
        btMoreRight.setOnClickListener(this);
    }

    @Override
    protected void requestNetData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_more_left:
                swichToChildFragment(new AccountCenterFragment(), true);
                break;
            case R.id.bt_more_right:
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
                etArea.setText("");
                etDetailed.setText("");
                etPhone.setText("");
                etUser.setText("");
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
