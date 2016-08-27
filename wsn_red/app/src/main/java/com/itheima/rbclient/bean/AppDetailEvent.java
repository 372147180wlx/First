package com.itheima.rbclient.bean;

/**
 * Created by ocean on 2016/8/9.
 */
public class AppDetailEvent implements IEvent {
    public AppDetailResponse.ProductBean product;
    public AppDetailEvent(AppDetailResponse.ProductBean product) {
        this.product = product;
    }
}
