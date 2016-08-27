package com.itheima.rbclient.bean;

/**
 * Created by ocean on 2016/8/9.
 */
public class AppDetailCollectEvent implements IEvent {
    private int sendpId;
    public AppDetailCollectEvent(int sendpId) {
        this.sendpId = sendpId;
    }
}
