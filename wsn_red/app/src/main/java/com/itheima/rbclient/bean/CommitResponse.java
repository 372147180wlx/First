package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by Administrator on 2016/8/10.
 */
public class CommitResponse implements IResponse {
    /**
     * orderId : 717693
     * paymentType : 1
     * price : 450
     */

    public OrderInfoBean orderInfo;
    /**
     * orderInfo : {"orderId":"717693","paymentType":1,"price":450}
     * response : orderSubmit
     */

    public String response;

    public static class OrderInfoBean {
        public String orderId;
        public int paymentType;
        public int price;
    }
}
