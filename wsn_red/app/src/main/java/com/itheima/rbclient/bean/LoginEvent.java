package com.itheima.rbclient.bean;

/**
 * Created by KCL on 2016/8/10.
 */
public class LoginEvent implements IEvent {
    public String log ;

    public LoginEvent(String log) {
        this.log = log;
    }
}
