package com.itheima.rbclient.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.RegisteResponse;
import com.itheima.rbclient.utils.Preutils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements HttpLoader.HttpListener {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_account);
        ButterKnife.inject(this);
    }



    /**
     * 初始化数据
     */
    private void iniData() {
    }

    protected void requestNetData() {

    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_REGISTER && response instanceof RegisteResponse) {
            RegisteResponse.UserInfoBean userInfo = ((RegisteResponse) response).getUserInfo();
            if (userInfo != null) {
                Preutils.putString(App.context, "username", username);
                Preutils.putString(App.context, "password", password);
                Toast.makeText(App.context, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(App.context, "用户名已被注册", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(App.context,"网络有问题",Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.bt_acc_left, R.id.register_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_acc_left:
                finish();
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
                HttpLoader.getInstance(App.context).get(RBConstants.URL_REGISTER, params, RegisteResponse.class, RBConstants.REQUEST_CODE_REGISTER, this,false);
        }
    }

}
