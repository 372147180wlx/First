package com.itheima.rbclient.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.AccountCenteResponse;
import com.itheima.rbclient.bean.LogoutResponse;
import com.itheima.rbclient.protocol.Api;
import com.itheima.rbclient.ui.activity.LoginActivity;
import com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments.AddressFragment;
import com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments.CouponFragment;
import com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments.FavoriteFragment;
import com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments.MyOrderFragment;
import com.itheima.rbclient.utils.Preutils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.ALog;
import org.senydevpkg.utils.MyToast;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/6.
 */
public class AccountCenterFragment extends BaseFragment implements View.OnClickListener, HttpLoader.HttpListener {
    private static final String TAG = "AccountCenterFragment";
    @InjectView(R.id.bt_more_left)
    Button btMoreLeft;
    @InjectView(R.id.bt_more_right)
    Button btMoreRight;
    @InjectView(R.id.tv_accountcenter_user)
    TextView tvAccountcenterUser;
    @InjectView(R.id.tv_accountcenter_huiy)
    TextView tvAccountcenterHuiy;
    @InjectView(R.id.tv_accountcenter_jif)
    TextView tvAccountcenterJif;
    @InjectView(R.id.rl_item_one)
    LinearLayout rlItemOne;
    @InjectView(R.id.rl_item_two)
    RelativeLayout rlItemTwo;
    @InjectView(R.id.rl_item_three)
    RelativeLayout rlItemThree;
    @InjectView(R.id.rl_item_four)
    RelativeLayout rlItemFour;
    @InjectView(R.id.tv_dd)
    TextView tvDd;
    @InjectView(R.id.tv_shou)
    TextView tvShou;
    @InjectView(R.id.tv_coupon)
    TextView tvCoupon;
    private AccountCenteResponse.UserInfoBean userInfo;
    private String userid;
    private String username;
    private String id;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(App.context, R.layout.fragment_account, null);
        return view;
    }

    @Override
    protected void initData() {
        username = Preutils.getString(App.context, "username", "");
        userid = Preutils.getString(App.context, "userid", "");
        Log.e(TAG, "2_initData: =====================================" + userid);
        if (!TextUtils.isEmpty(userid)) {
            requestNetDat();
            tvAccountcenterUser.setText("用户名 : " + username);
            requestNetDat();
            btMoreRight.setText("退出登录");

        } else {
            tvAccountcenterUser.setText("用户名 : ");
            tvAccountcenterHuiy.setText("会员等级 :");
            tvAccountcenterJif.setText("会员积分 :");
            tvDd.setText("我的订单");
            tvShou.setText("收藏夹");
            tvCoupon.setText("优惠券/礼品卡");
            btMoreRight.setText("登录");
        }
    }

    @Override
    protected void handleUserInput() {
        btMoreLeft.setOnClickListener(this);
        btMoreRight.setOnClickListener(this);
        rlItemOne.setOnClickListener(this);
        rlItemTwo.setOnClickListener(this);
        rlItemThree.setOnClickListener(this);
        rlItemFour.setOnClickListener(this);
    }

    @Override
    protected void requestNetData() {

    }
    protected void requestNetDat() {
        //从网络上请求数据
        Api.getAccountCenterData(this).setTag(this);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }

    @Override
    public void onClick(View v) {
        id = Preutils.getString(App.context, "userid", "");
        switch (v.getId()) {
            case R.id.bt_more_left://更多
                swichToChildFragment(new MoreFragment(), false);
                break;
            case R.id.bt_more_right://登录界面
                String btRight = btMoreRight.getText().toString().trim();
                if ("退出登录".equals(btRight)) {
                    Preutils.putString(App.context, "userid", "");
                    tvAccountcenterUser.setText("用户名 : ");
                    tvAccountcenterHuiy.setText("会员等级 :");
                    tvAccountcenterJif.setText("会员积分 :");
                    tvDd.setText("我的订单");
                    tvShou.setText("收藏夹");
                    tvCoupon.setText("优惠券/礼品卡");
                    btMoreRight.setText("登录");
                    HttpParams params = new HttpParams();
                    params.addHeader("userid", userid);//logout
                    HttpLoader.getInstance(App.context).post(RBConstants.URL_LOGOUT, params, LogoutResponse.class, RBConstants.REQUEST_CODE_LOGOUT, this);
                } else {
                    //swichToChildFragment(new LoginFragment(), true);
                    Intent intent=new Intent(App.context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                break;
            case R.id.rl_item_one://切换我的订单
                ALog.e("          id          "+id);
                if (id != "") {
                    swichToChildFragment(new MyOrderFragment(), false);
                } else {
                    ALog.e("未登录");
                    MyToast.show(App.context, "请先登录！");
                    return;
                }
                swichToChildFragment(new MyOrderFragment(), false);
                break;
            case R.id.rl_item_two://切换地址管理
                if (id != "") {
                    swichToChildFragment(new AddressFragment(), false);
                } else {
                    MyToast.show(App.context, "请先登录！");
                    return;
                }
                swichToChildFragment(new AddressFragment(), false);
                break;
            case R.id.rl_item_three://切换优惠券
                if (id != "") {
                    swichToChildFragment(new CouponFragment(), false);
                } else {
                    MyToast.show(App.context, "请先登录！");
                    return;
                }
                swichToChildFragment(new CouponFragment(), false);
                break;
            case R.id.rl_item_four://切换收藏夹
                if (id != "") {
                    swichToChildFragment(new FavoriteFragment(), false);
                } else {
                    MyToast.show(App.context, "请先登录！");
                    return;
                }
                swichToChildFragment(new FavoriteFragment(), false);
                break;
        }
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_ACCOUNTCENTER_LIST && response instanceof AccountCenteResponse) {
            userInfo = ((AccountCenteResponse) response).getUserInfo();

            if (userInfo == null) {
                //       tvAccountcenterUser.setText("用户名 : ");
                tvAccountcenterHuiy.setText("会员等级 :");
                tvAccountcenterJif.setText("会员积分 :");
                tvDd.setText("我的订单");
                tvShou.setText("收藏夹");
                tvCoupon.setText("优惠券/礼品卡");
            } else {

                //  tvAccountcenterUser.setText("用户名 : " + username);
                tvAccountcenterHuiy.setText("会员等级 : " + userInfo.getLevel());
                tvAccountcenterJif.setText("会员积分 : " + userInfo.getBonus());
                tvDd.setText("我的订单( " + userInfo.getOrderCount() + " )");
                tvShou.setText("收藏夹( " + userInfo.getFavoritesCount() + " )");
                System.out.println("========================");
            }
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
    }

    @Override
    public void onResume() {
        initData();
       super.onResume();
    }
}
