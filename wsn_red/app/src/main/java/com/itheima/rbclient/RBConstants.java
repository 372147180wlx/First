package com.itheima.rbclient;

/**
 * 常量类
 * 用来保存一些几乎从不轻易改动的变量
 * <p/>
 * Created by xiongmc on 2016/4/28.
 */
public class RBConstants {

    public static final String URL_SERVER = "http://192.168.15.46:80/RedBabyServer/";

    /**
     * 注销登录
     */
    public static final String URL_LOGOUT = URL_SERVER + "logout";
    public static final int REQUEST_CODE_LOGOUT = 205;


    /**
     * 促销快报
     */
    public static final String URL_TOPIC = URL_SERVER + "topic";
    public static final int REQUEST_CODE_TOPIC = 1;


    /**
     * 促销快报列表
     */
    public static final String URL_TOPIC_PLIST = URL_SERVER + "topic/plist";
    public static final int REQUEST_CODE_TOPIC_PLIST = 55;


    /**
     *
     */
    public static final String URL_AccountCenter_LIST = URL_SERVER + "userinfo";
    public static final int REQUEST_CODE_AccountCenter_LIST = 33;
    /**
     * 推荐品牌
     */
    public static final String URL_BRAND = URL_SERVER + "brand";
    public static final int REQUEST_CODE_BRAND = 51;
    /**
     * 限时抢购
     */
    public static final String URL_LIMITBUY = URL_SERVER + "limitbuy";
    public static final int REQUEST_CODE_LIMITBUY = 52;
    /**
     * 新品上架
     */
    public static final String URL_NEWPRODUCT = URL_SERVER + "newproduct";
    public static final int REQUEST_CODE_NEWPRODUCT = 53;

    /**
     * 热门单品
     */
    public static final String URL_HOTPRODUCT = URL_SERVER + "hotproduct";
    public static final int REQUEST_CODE_HOTPRODUCT = 54;

    /**
     * 购物车
     */
    public static final String URL_CART = URL_SERVER + "cart";
    public static final int REQUEST_CODE_CART = 2;

    /**
     * 结算中心
     */
    public static final String URL_CHECKOUT = URL_SERVER + "checkout";
    public static final int REQUEST_CODE_CHECKOUT = 3;

    /**
     * 登录
     */
    public static final String URL_LOGIN = URL_SERVER + "login";
    public static final int REQUEST_CODE_LOGIN = 4;


    /**
     * 登录
     */
    public static final String URL_ADDRESS_LIST = URL_SERVER + "addresslist";
    public static final int REQUEST_CODE_ADDRESS_LIST = 5;

    /**
     * 分类
     */
    public static final String URL_CATEGORY = URL_SERVER + "category";
    public static final int REQUEST_CODE_CATEGORY = 6;

    /**
     * 商品列表
     */
    public static final String URL_CATEGORY_PRODUCT = URL_SERVER + "productlist";
    public static final int REQUEST_CODE_CATEGORY_PRODUCT = 7;

    /**
     * homeViewPager
     */
    public static final String URL_HOME_LIST = URL_SERVER + "home";
    public static final int REQUEST_CODE_HOME_LIST = 110;


    /**
     * 收藏夹
     */
    public static final String URL_FAVORITES_LIST = URL_SERVER + "favorites";
    public static final int REQUEST_CODE_FAVORITES_LIST = 202;
    /**
     * 帮助中心
     */
    public static final String URL_HELP_CENTER = URL_SERVER + "help";
    public static final int REQUEST_CODE_HELP_CENTER = 100;
    /**
     * 帮助中心详情
     */
    public static final String URL_HELP_CENTER_DETAIL = URL_SERVER + "helpDetail";
    public static final int REQUEST_CODE_HELP_CENTER_DETAIL = 99;
    /**
     * 帮助中心详情
     */
    public static final String URL_SEARCH_RCOMMOND = URL_SERVER + "search/recommend";
    public static final int REQUEST_CODE_SEARCH_RCOMMOND = 199;

    /**
     * 搜索
     */
    public static final String URL_SEARCH = URL_SERVER + "search";
    public static final int REQUEST_CODE_SEARCH = 200;


    /**
     * 商品详情
     */
    public static final String URL_APP_DETAIL = URL_SERVER + "product";
    public static final int REQUEST_CODE_APP_DETAIL = 44;

    /**
     * 商品详情描述
     */
    public static final String URL_APP_DETAIL_MSG = URL_SERVER + "product/description";
    public static final int REQUEST_CODE_APP_DETAIL_MSG = 133;

    /**
     * 商品评论
     */
    public static final String URL_APP_DETAIL_COMM = URL_SERVER + "product/comment";
    public static final int REQUEST_CODE_APP_DETAIL_COMM = 233;
    /**
     * 帐户中心
     */
    public static final String URL_ACCOUNTCENTER_LIST = URL_SERVER + "userinfo";
    public static final int REQUEST_CODE_ACCOUNTCENTER_LIST = 111;

    /**
     * 结算界面添加地址
     */
    public static final String URL_CHECK_ADDRESS = URL_SERVER + "addresslist";
    public static final int REQUEST_CODE_APP_CHECKADDRESS = 222;
    /**
     * 结算提交订单
     */
    public static final String URL_CHECK_COMMIT = URL_SERVER + "ordersumbit";
    public static final int REQUEST_CODE_APP_COMMIT = 223;


    /**
     * 订单详情
     */
    public static final int REQUEST_CODE_ORDER_DETAIL = 101010;
    public static final String URL_ORDER_DETAIL = URL_SERVER + "orderdetail";
    /**
     * 取消订单
     */
    public static final int REQUEST_CODE_ORDER_CANCLE = 101011;
    public static final String URL_ORDER_CANCLE = URL_SERVER + "ordercancel";
    /**
     * 我的订单列表
     */
    public static final String URL_APP_MYORDER_LIST = URL_SERVER + "orderlist";
    public static final int REQUEST_CODE_APP_MYORDER_LIST = 10000;
    /**
     * 注册
     */
    public static final String URL_REGISTER = URL_SERVER + "register";
    public static final int REQUEST_CODE_REGISTER = 273;
    /**
     * 我的订单列表
     */
    public static final String URL_APP_TEST = URL_SERVER + "orderlist";
    public static final int REQUEST_CODE_TEST = 100010;


}

