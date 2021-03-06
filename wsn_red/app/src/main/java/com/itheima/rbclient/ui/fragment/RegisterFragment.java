package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.RegisteResponse;
import com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments.LoginFragment;
import com.itheima.rbclient.utils.Preutils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by KCL on 2016/8/11.
 */
public class RegisterFragment extends BaseFragment implements HttpLoader.HttpListener {
    @InjectView(R.id.bt_acc_left)
    Button btAccgoback;
    @InjectView(R.id.register_userName)
    EditText registerUserName;
    @InjectView(R.id.register_password)
    EditText registerPassword;
    @InjectView(R.id.register_second)
    EditText registerSecond;
    @InjectView(R.id.register_commit)
    Button registerCommit;
    private String username;
    private String password;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.regist_account, null);
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
//    @InjectView(R.id.bt_acc_left)
//    Button btAccgoback;
//    @InjectView(R.id.register_userName)
//    EditText registerUserName;
//    @InjectView(R.id.register_password)
//    EditText registerPassword;
//    @InjectView(R.id.register_second)
//    EditText registerSecond;
//    @InjectView(R.id.register_commit)
//    Button registerCommit;
    @OnClick({R.id.bt_acc_left, R.id.register_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_acc_left:
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(LoginFragment.class), false);
                onDestroyView();
                break;
            case  R.id.register_commit:
                username = registerUserName.getText().toString().trim();
                password = registerPassword.getText().toString().trim();
                String passwordConfirm =registerSecond.getText().toString().trim();
                if (!password.equals(passwordConfirm)){
                    Toast.makeText(App.context,"密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length()<6){
                    Toast.makeText(App.context,"密码不能少于六位",Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpParams params = new HttpParams();
                params.put("username", username).put("password", password);
                HttpLoader.getInstance(App.context).get(RBConstants.URL_REGISTER, params, RegisteResponse.class, RBConstants.REQUEST_CODE_REGISTER, this);
        }
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_REGISTER && response instanceof RegisteResponse) {
            RegisteResponse.UserInfoBean userInfo = ((RegisteResponse) response).getUserInfo();
            if (userInfo != null) {
                Preutils.putString(App.context, "username", username);
                Preutils.putString(App.context, "password", password);
                Toast.makeText(App.context, "注册成功", Toast.LENGTH_SHORT).show();
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(LoginFragment.class), false);
            } else {
                Toast.makeText(App.context, "用户名已被注册", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(App.context,"网络有问题",Toast.LENGTH_SHORT).show();
    }
}
