package com.itheima.rbclient.bean;

/**
 * 发票的event类
 */
public class CommitSuccessEvent implements IEvent {
    /**
     * 订单号
     */
    public String orderId;
    /**
     * 支付方式
     */
    public int paymentType;

    /**
     *总价
     */
    public int price;
    public CommitSuccessEvent(String orderId, int paymentType,int price) {
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.price = price;
    }
}
