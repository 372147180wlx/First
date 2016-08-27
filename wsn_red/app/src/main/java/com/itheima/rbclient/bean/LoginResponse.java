package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by xiongmc on 2016/6/8.
 */
public class LoginResponse implements IResponse {


    /**
     * error : 用户名不存在或密码错误
     * error_code : 1530
     * response : error
     * userInfo : {"userid":"221120"}
     */

    private String error;
    private String error_code;
    private String response;
    /**
     * userid : 221120
     */

    private UserInfoBean userInfo;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        private String userid;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
