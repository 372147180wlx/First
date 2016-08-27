package com.itheima.rbclient.ui.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.CommitSuccessEvent;
import com.itheima.rbclient.bean.IEvent;
import com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments.MyOrderFragment;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class CommitSuccessFragment extends BaseFragment implements HttpLoader.HttpListener{

	private TextView tv_commitsucc_tv1;
	private TextView tv_commitsucc_tv2;
	private TextView tv_commitsucc_tv3;
	private String[] modeArr = {"到付-现金","到付-POS机","支付宝"};

	private SharedPreferences sp;
	private SharedPreferences.Editor edit;
	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
							  Bundle savedInstanceState) {
		EventBus.getDefault().registerSticky(this);
		return iniView();
	}

	/**
	 * 初始化界面
	 */
	private View iniView() {
		View view = View.inflate(App.context,R.layout.commit_success,null);
		//ButterKnife.inject(view);
		tv_commitsucc_tv1 = (TextView) view.findViewById(R.id.tv_commitsucc_tv1);
		tv_commitsucc_tv2 = (TextView) view.findViewById(R.id.tv_commitsucc_tv2);
		tv_commitsucc_tv3 = (TextView) view.findViewById(R.id.tv_commitsucc_tv3);
		return view ;
		}


@Override
	protected void initData() {


	}

	@Override
	protected void handleUserInput() {

	}

	@Override
	protected void requestNetData() {
		//Api.getCartData(this); //请求服务器数据
	}

	@Override
	public void onGetResponseSuccess(int requestCode, IResponse response) {

	}

	@Override
	public void onGetResponseError(int requestCode, VolleyError error) {

	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		App.HL.cancelRequest(this);
	}

	@OnClick({R.id.btn_continue,R.id.btn_lookpback})
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_continue:
				switchNavigationFragment(R.id.rb_content_fragment_home);
			break;
			case R.id.btn_lookpback:
				mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MyOrderFragment.class));
			break;
		}

	}

	public void onEvent(IEvent event) {
		if(event instanceof CommitSuccessEvent) {
			CommitSuccessEvent commitEvent = (CommitSuccessEvent) event;
			tv_commitsucc_tv1.setText("您的订单号： "+commitEvent.orderId);
			tv_commitsucc_tv2.setText("应付金额： ￥"+commitEvent.price);
			tv_commitsucc_tv3.setText("支付方式： "+modeArr[commitEvent.paymentType - 1]);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		sp = App.context.getSharedPreferences("memory",0);
		edit = sp.edit();
		edit.putString("sendShop","");
		edit.commit();
	}
}
