package com.itheima.rbclient.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.LoadStateLayout;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.CartAdapter;
import com.itheima.rbclient.bean.AddCartEvent;
import com.itheima.rbclient.bean.CartResponse;
import com.itheima.rbclient.bean.EditEvent;
import com.itheima.rbclient.bean.IEvent;
import com.itheima.rbclient.bean.ParamEvent;
import com.itheima.rbclient.ui.activity.AppDetailActivity;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class CartFragment extends BaseFragment implements HttpLoader.HttpListener{

	protected LoadStateLayout cartLoadPager;
//	@InjectView(R.id.lv_cart)
//	ListView lv_cart;
	private ListView lv_cart;
	private List<CartResponse.CartBean> cartList;
	private CartAdapter adapter;
	private View emptyView;
//头布局声明
	@InjectView(R.id.tv_cartheader_count)
	 TextView tvCartHeaderCount;
	@InjectView(R.id.tv_cartheader_jf)
	TextView tvCartHeaderJF;
	@InjectView(R.id.tv_cartheader_total)
	TextView tvCartHeaderTotal;
//脚布局声明
	@InjectView(R.id.et_cart_fotter)
	TextView etCartFotter;

	private SharedPreferences sp;
	private SharedPreferences.Editor edit;
	private String para = "";
	private String para2 = "";
	private String para3 = "";

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		EventBus.getDefault().registerSticky(this);
		sp = App.context.getSharedPreferences("memory",0);
		edit = sp.edit();
		getParams();
		View view = iniView();
		return view;//inflater.inflate(R.layout.fragment_cart, null);

	}

	/**
	 *获得传递的sku参数
     */
	public void getParams() {
		String sendShop = sp.getString("sendShop", "");
		if(sendShop.length() > 0) {
			//以下的逻辑合并相同的商品，商品id和属性id 一致就合并，把商品id和属性id作为key，数量作为value
			Map<String, Integer> map = new HashMap<>();
			String[] arr = sendShop.split("#");
			//遍历数组拼出key
			for (int i = 1; i < arr.length; i++) {
				String key = arr[i].split(":")[0]+"_"+ arr[i].split(":")[2];
				int value = Integer.parseInt(arr[i].split(":")[1]);
				if(!map.containsKey(key)) {
					map.put(key,value);
				}else {
					//重复商品，合并数量
					map.put(key,value+map.get(key));
				}
			}
			//遍历map集合
			Set<String> keySet = map.keySet();  //map.keySet()是所有键的集合
			for(String key : keySet) {
				para = para+"|"+key.split("_")[0]+":"+map.get(key)+":"+ key.split("_")[1];
				para2 = para2+"#"+key.split("_")[0]+":"+map.get(key)+":"+ key.split("_")[1];
			}
			//MyToast.show(App.context, "zzzzzzzzzzzzz"+ arr[2]);
			para = para.substring(1);
			//合并过的数据从新放回sp中
			edit.putString("sendShop",para2);
			edit.commit();
		}
		//MyToast.show(App.context, "zzzzzzzzzzzzz"+ para);
		reqData();
		para3 = para;
		para = "";
		para2 = "";
	}
	/**
	 * 初始化界面
	 */
	private View iniView() {
		View view = View.inflate(App.context,R.layout.fragment_cart,null);
		cartLoadPager = (LoadStateLayout) view.findViewById(R.id.lp_result);
		cartLoadPager.setEmptyView(R.layout.fragment_cart_empty);
		cartLoadPager.setLoadingView(R.layout.fragment_cart_loading);
		cartLoadPager.setState(LoadStateLayout.STATE_LOADING);
		lv_cart = (ListView) view.findViewById(R.id.lv_cart);
		lv_cart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//MyToast.show(App.context, "dddddddddddddddddddddddddd " );
				Intent intent=new Intent(App.context, AppDetailActivity.class);
				intent.putExtra("sendpId",cartList.get(position).product.id);
				//Log.e("asd",cartList.get(position).product.id+"wwww"+position);
				startActivity(intent);
			}
		});
		return view;
		}


@Override
	protected void initData() {
		cartList = new ArrayList<>();
		adapter = new CartAdapter(cartList);
		lv_cart.setAdapter(adapter);

	}

	@Override
	protected void handleUserInput() {

	}

	@Override
	protected void requestNetData() {
	}

	public void reqData() {
		//Api.getCartData(this); //请求服务器数据
		String url = RBConstants.URL_CART;
		HttpParams params = new HttpParams();
		params.put("sku",para);
//        params.put("sku", "1:0:1,3|2:0:1,5");
		Class<? extends IResponse> clazz = CartResponse.class;
		int requestcode = RBConstants.REQUEST_CODE_CART;
		App.HL.post(url, params, clazz, requestcode, this);
	}
	@Override
	public void onGetResponseSuccess(int requestCode, IResponse response) {

		if (requestCode == RBConstants.REQUEST_CODE_CART && response instanceof CartResponse){
			CartResponse resp = (CartResponse) response;
			if (resp.totalCount == 0) {  //返回的商品数量为0 ，则购物车显示空页面
				cartLoadPager.setState(LoadStateLayout.STATE_EMPTY);
			}else {
				cartLoadPager.setState(LoadStateLayout.STATE_SUCCESS);
				//填充头布局数据
					tvCartHeaderCount.setText(resp.totalCount+"");
					tvCartHeaderJF.setText(resp.totalPoint+"");
					tvCartHeaderTotal.setText(resp.totalPrice+"");
				//填充脚布局数据
				etCartFotter.setText(resp.prom.toString());
				cartList = ((CartResponse) response).cart;
			}
		}
		adapter.notifyDataSetChanged(cartList);
	}

	@Override
	public void onGetResponseError(int requestCode, VolleyError error) {
		//MyToast.show(App.context, "Request " + requestCode + " error , msg : " + error.getMessage());
		cartLoadPager.setState(LoadStateLayout.STATE_EMPTY);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		App.HL.cancelRequest(this);
	}

	@OnClick({R.id.btn_clearcart,R.id.btn_edit,R.id.btn_goback,R.id.btn_gohome,R.id.btn_cart_gocheckout})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_clearcart: //清空购物车
				clearCart();
				break;
			case R.id.btn_edit:  //编辑
				EventBus.getDefault().postSticky(new EditEvent(1)); //把点击按钮的事件传给CartHolder
				break;
			case R.id.btn_cart_gocheckout:  //去结算
				EventBus.getDefault().postSticky(new ParamEvent(para3)); //把结算参数传递给结算界面
				mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(CheckOutFragment.class));
				//Intent intent = new Intent(App.context, CheckOutActivity.class);
				//startActivity(intent);
				break;
			case R.id.btn_goback:  //返回
			case R.id.btn_gohome:  //随便逛逛
				//mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(HomeFragment.class));
				switchNavigationFragment(R.id.rb_content_fragment_home);
				//swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(HomeFragment.class), false);
				break;
		}
	}


	/**
	 * 清空购物车
	 */
	private void clearCart() {
		edit.putString("sendShop","");
		edit.commit();
		String url = RBConstants.URL_CART;
		HttpParams params = new HttpParams();
//				params.put("sku", "1:2:1,3|2:3:1,5");
		params.put("sku", "1:0:1,3|2:0:1,5");
		Class<? extends IResponse> clazz = CartResponse.class;
		int requestcode = RBConstants.REQUEST_CODE_CART;
		App.HL.post(url, params, clazz, requestcode,this);
	}

	public void onEvent(IEvent event) {
		//编辑增加数量
		if(event instanceof AddCartEvent) {
			AddCartEvent addEvent = (AddCartEvent) event;
			App.HL.post(addEvent.url, addEvent.params, addEvent.clazz, addEvent.requestcode,this);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		cartLoadPager.setState(LoadStateLayout.STATE_LOADING);
		getParams();
	}
}
