package com.itheima.rbclient.bean;

import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

/**
 * 发票的event类
 */
public class AddCartEvent implements IEvent {

    public String url;
    public HttpParams params;
    public Class<? extends IResponse> clazz;
    public int requestcode;

    public AddCartEvent(String url, HttpParams params,Class<? extends IResponse> clazz,int requestcode) {
        this.url = url;
        this.params = params;
        this.clazz = clazz;
        this.requestcode = requestcode;
    }
}
