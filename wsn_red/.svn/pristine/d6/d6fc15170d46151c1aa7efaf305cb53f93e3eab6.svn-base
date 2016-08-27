package com.itheima.rbclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.LoginEvent;
import com.itheima.rbclient.bean.LoginResponse;
import com.itheima.rbclient.utils.Preutils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.ALog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class LoginActivity extends AppCompatActivity implements HttpLoader.HttpListener,View.OnClickListener {

    @InjectView(R.id.bt_acc_right)
    Button btAccRight;
    @InjectView(R.id.login_register)
    Button loginRegister;
    @InjectView(R.id.login_commit)
    Button loginCommit;
    @InjectView(R.id.bt_acc_goback)
    Button btAccGoback;
    @InjectView(R.id.login_username)
    EditText loginUsername;
    @InjectView(R.id.login_password)
    EditText loginPassword;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_account);
        ButterKnife.inject(this);
        btAccGoback.setOnClickListener(this);
        btAccRight.setOnClickListener(this);
        loginCommit.setOnClickListener(this);
        loginRegister.setOnClickListener(this);
        iniData();
    }



    /**
     * 初始化数据
     */
    private void iniData() {
        String username = Preutils.getString(App.context, "username", "");
        loginUsername.setText(username);
        requestNetData();
    }

    protected void requestNetData() {

    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_LOGIN && response instanceof LoginResponse) {
            LoginResponse.UserInfoBean userInfo = ((LoginResponse) response).getUserInfo();
            if (userInfo != null) {
                Toast.makeText(App.context, "登录成功", Toast.LENGTH_SHORT).show();
                Preutils.putString(App.context, "userid", userInfo.getUserid() + "");
                ALog.e("userid==================================="+userInfo.getUserid() );
                Preutils.putString(App.context, "username",username);
                EventBus.getDefault().postSticky(new LoginEvent("success"));
               finish();
            } else {
                Toast.makeText(App.context, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(App.context, "网络不对", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_acc_goback:
               finish();
                break;
            case R.id.bt_acc_right:
                finish();
                break;
            case R.id.login_commit:
                username = loginUsername.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    Toast.makeText(App.context, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpParams params = new HttpParams();
                params.put("username", username).put("password", password);
                HttpLoader.getInstance(App.context).get(RBConstants.URL_LOGIN, params, LoginResponse.class, RBConstants.REQUEST_CODE_LOGIN, this,false);
                break;
            case R.id.login_register:
                //swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(RegisterFragment.class), false);
                Intent intent=new Intent(App.context, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

}
