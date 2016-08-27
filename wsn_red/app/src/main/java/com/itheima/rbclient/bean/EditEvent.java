package com.itheima.rbclient.bean;

/**
 * 发票的event类
 */
public class EditEvent implements IEvent {

    public int click;

    public EditEvent(int click) {
        this.click = click;
    }
}
