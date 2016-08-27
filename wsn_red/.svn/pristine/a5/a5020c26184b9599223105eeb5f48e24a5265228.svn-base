package com.itheima.rbclient.holder;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.AddCartEvent;
import com.itheima.rbclient.bean.CartResponse;
import com.itheima.rbclient.bean.EditEvent;
import com.itheima.rbclient.bean.IEvent;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 *
 */
public class CartHolder extends BaseHolder<CartResponse.CartBean> implements View.OnClickListener {
    @InjectView(R.id.tv_cart_des)
    TextView tvCartDes;
    @InjectView(R.id.iv_cart_image)
    ImageView ivCartImage;
    @InjectView(R.id.tv_cart_count)
    TextView tvCartCount;
    @InjectView(R.id.tv_cart_size)
    TextView tvCartSize;
    @InjectView(R.id.tv_cart_color)
    TextView tvCartColor;
    @InjectView(R.id.tv_cart_price)
    TextView tvCartPrice;
    @InjectView(R.id.tv_cart_total)
    TextView tvCartTotal;
    @InjectView(R.id.ib_next)
    TextView ibNext;
    @InjectView(R.id.ib_previous)
    TextView ibPrevious;
    private int id;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private String para = "";
    private String para2 = "";

    public CartHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        EventBus.getDefault().registerSticky(this);
        View view = View.inflate(App.context, R.layout.item_cart, null);
        sp = App.context.getSharedPreferences("memory", 0);
        edit = sp.edit();
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(CartResponse.CartBean data) {
        ibNext.setOnClickListener(this);
        ibPrevious.setOnClickListener(this);
        //ID
        id = data.product.id;
        tvCartDes.setText(data.product.name);
        tvCartCount.setText("" + data.prodNum);
        tvCartSize.setText("尺寸：" + data.product.productProperty.get(1).v);
        tvCartColor.setText("颜色：" + data.product.productProperty.get(0).v);
        tvCartPrice.setText("价格：￥" + data.product.price);
        tvCartTotal.setText("小计：￥" + data.product.price * data.prodNum);
        HttpLoader.getInstance(App.context).getImageLoader().get(RBConstants.URL_SERVER + data.product.pic, ImageLoader.getImageListener(ivCartImage, R.drawable.product_loading, R.drawable.product_loading));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_next:
                subCart(id, Integer.parseInt(tvCartCount.getText().toString()));
                //MyToast.show(App.context, );
                break;
            case R.id.ib_previous:
                addCart(id, Integer.parseInt(tvCartCount.getText().toString()));
                // MyToast.show(App.context, "-------------hehe-----------------" );
                break;
        }

    }

    /*
    * 购物车增加商品数量
    */
    private void addCart(int id, int num) {
        initParam(id, num, 1);
        String url = RBConstants.URL_CART;
        HttpParams params = new HttpParams();
        params.put("sku", para);
//		params.put("sku", "1:0:1,3|2:0:1,5");
        Class<? extends IResponse> clazz = CartResponse.class;
        int requestcode = RBConstants.REQUEST_CODE_CART;
        EventBus.getDefault().postSticky(new AddCartEvent(url, params, clazz, requestcode));
        //App.HL.post(url, params, clazz, requestcode,this);
        para = "";
        para2 = "";
    }

    /**
     * 购物车减少商品数量
     */
    private void subCart(int id, int num) {
        initParam(id, num, 0);
        String url = RBConstants.URL_CART;
        HttpParams params = new HttpParams();
        params.put("sku", para);
//		params.put("sku", "1:0:1,3|2:0:1,5");
        Class<? extends IResponse> clazz = CartResponse.class;
        int requestcode = RBConstants.REQUEST_CODE_CART;
        EventBus.getDefault().postSticky(new AddCartEvent(url, params, clazz, requestcode));
        para = "";
        para2 = "";
    }


    public void onEvent(IEvent event) {
        //隐藏，显示增加减少的按钮
        if (event instanceof EditEvent) {
            EditEvent editEvent = (EditEvent) event;
            ibNext.setVisibility(View.VISIBLE);
            ibPrevious.setVisibility(View.VISIBLE);
        }
    }

    public void initParam(int id, int num, int mode) {
        String sendShop = sp.getString("sendShop", "");
        if (sendShop.length() > 0) {
            //以下的逻辑合并相同的商品，商品id和属性id 一致就合并，把商品id和属性id作为key，数量作为value
            Map<String, Integer> map = new HashMap<>();
            String[] arr = sendShop.split("#");
            //遍历数组拼出key
            for (int i = 1; i < arr.length; i++) {
                String key = arr[i].split(":")[0] + "_" + arr[i].split(":")[2];
                int value = Integer.parseInt(arr[i].split(":")[1]);
                if (mode == 1 && id == Integer.parseInt(arr[i].split(":")[0])) {
                    value = num + 1;
                } else if (mode == 0 && id == Integer.parseInt(arr[i].split(":")[0])) {
                    value = num - 1;
                    if (value < 0) {
                        value = 0;
                    }
                }
                if (!map.containsKey(key)) {
                    map.put(key, value);
                } else {
                    //重复商品，合并数量
                    map.put(key, value + map.get(key));
                }
            }
            //遍历map集合
            Set<String> keySet = map.keySet();  //map.keySet()是所有键的集合
            for (String key : keySet) {
                para = para + "|" + key.split("_")[0] + ":" + map.get(key) + ":" + key.split("_")[1];
                para2 = para2 + "#" + key.split("_")[0] + ":" + map.get(key) + ":" + key.split("_")[1];
            }
            //MyToast.show(App.context, "zzzzzzzzzzzzz"+ arr[2]);
            para = para.substring(1);
            //合并过的数据从新放回sp中
            edit.putString("sendShop", para2);
            edit.commit();
        }
    }
}