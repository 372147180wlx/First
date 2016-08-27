package com.itheima.rbclient.bean;

/**
 * Created by Administrator on 2016/8/9.
 */
public class PayModeEvent implements IEvent {
    public int mode;

    public PayModeEvent(int mode) {
        this.mode = mode;
    }
}
