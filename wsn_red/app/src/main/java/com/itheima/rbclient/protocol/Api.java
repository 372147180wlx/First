package com.itheima.rbclient.protocol;

import com.android.volley.Request;
import com.itheima.rbclient.App;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.AccountCenteResponse;
import com.itheima.rbclient.bean.CartResponse;
import com.itheima.rbclient.bean.CategoryResponse;
import com.itheima.rbclient.bean.CheckoutResponse;
import com.itheima.rbclient.bean.FavoriteResponse;
import com.itheima.rbclient.bean.HelpCenterDetailResponse;
import com.itheima.rbclient.bean.HelpCenterResponse;
import com.itheima.rbclient.bean.HomeVPResponse;
import com.itheima.rbclient.bean.TopicResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

/**
 * Created by yanjingpan on 2016/8/5.
 */
public class Api {

    public static Request getTopicData(HttpLoader.HttpListener listener) {
        String url = RBConstants.URL_TOPIC;
        HttpParams params = new HttpParams();
        params.put("page", "1").put("pageNum", "8");
        Class<? extends IResponse> clazz = TopicResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_TOPIC;
        return App.HL.get(url, params, clazz, requestCode, listener);
    }

    public static Request getHelpCenterData(HttpLoader.HttpListener listener) {
        String url = RBConstants.URL_HELP_CENTER;
        Class<? extends IResponse> clazz = HelpCenterResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_HELP_CENTER;
        return App.HL.get(url, null, clazz, requestCode, listener);

    }

 /*   *//**
     * 订单详情
     * @param listener
     * @return
     *//*
    public static Request getOrderDetailData(HttpLoader.HttpListener listener) {
        String url = RBConstants.URL_ORDER_DETAIL;
        Class<? extends IResponse> clazz = OrderDetailResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_ORDER_DETAIL;
        return App.HL.get(url, null, clazz, requestCode, listener);
    }*/

    public static Request getHelpCenterDetailData(HttpLoader.HttpListener listener) {
        String url = RBConstants.URL_HELP_CENTER_DETAIL;
       /* HttpParams params = new HttpParams();
        params.put("id", "1");*/
        Class<? extends IResponse> clazz = HelpCenterDetailResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_HELP_CENTER_DETAIL;
        System.out.println("数据"+ App.HL.get(url, null, clazz, requestCode, listener));
        return App.HL.get(url, null, clazz, requestCode, listener);
    }

    public static Request getCheckoutData(HttpLoader.HttpListener listener) {
        String url = RBConstants.URL_CHECKOUT;
        HttpParams params = new HttpParams();
        params.put("sku", "1:20:1,3|2:30:1,5");
        params.addHeader("userid","20428");
        Class<? extends IResponse> clazz = CheckoutResponse.class;
        int requestcode = RBConstants.REQUEST_CODE_TOPIC;
        return App.HL.get(url, params, clazz, requestcode, listener);
    }

    public static Request getCartData(HttpLoader.HttpListener listener) {
        String url = RBConstants.URL_CART;
        HttpParams params = new HttpParams();
        params.put("sku", "1:2:1,3|2:3:1,5");
//        params.put("sku", "1:0:1,3|2:0:1,5");
//        params.addHeader("userid","20428");
        Class<? extends IResponse> clazz = CartResponse.class;
        int requestcode = RBConstants.REQUEST_CODE_CART;
        return App.HL.post(url, params, clazz, requestcode, listener);
    }

    /**
     * HOME
     * @param listener
     * @return
     */
    public static Request getHomeData(HttpLoader.HttpListener listener) {
        String url = RBConstants.URL_HOME_LIST;
        HttpParams params = new HttpParams();
        Class<? extends IResponse> clazz = HomeVPResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_HOME_LIST;
        return App.HL.get(url, params, clazz, requestCode, listener);
    }

    /**
     * 帐户中心
     * @param listener
     * @return
     */
    public static Request getAccountCenterData(HttpLoader.HttpListener listener) {
        String url = RBConstants.URL_ACCOUNTCENTER_LIST;
        HttpParams params = new HttpParams();
        params.addHeader("userid","20428");
        Class<? extends IResponse> clazz = AccountCenteResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_ACCOUNTCENTER_LIST;
        return App.HL.get(url, params, clazz, requestCode, listener);
    }
    /**
     * 收藏夹
     * @param listener
     * @return
     */
    public static Request getFavoriteData(HttpLoader.HttpListener listener) {
        String url = RBConstants.URL_FAVORITES_LIST;
        HttpParams params = new HttpParams();
        params.put("page","0").put("pageNum","10");
        params.addHeader("userid","20428");
        Class<? extends IResponse> clazz = FavoriteResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_FAVORITES_LIST;
        return App.HL.get(url, params, clazz, requestCode, listener);
    }

    /**
     * 分类浏览
     * @return
     */
    public static Request getCategoryData(HttpLoader.HttpListener listener) {
        String url = RBConstants.URL_CATEGORY;
        Class<? extends IResponse> clazz = CategoryResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_CATEGORY;
        return App.HL.get(url, null, clazz, requestCode, listener);
    }

}
