package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by zhaoqiang on 2016/8/6 0006.
 */
public class CategoryResponse implements IResponse {

    public String response;

    /**
     "id":"1",
     "isLeafNode":false,
     "name":"奶粉,
     "parentId":"0",
     "pic":"",
     "tag":"孕妇奶粉   幼儿奶粉"
     */
    public List<CategoryBean> category;

    public static class CategoryBean {
        public int id;
        public boolean isLeafNode;
        public String name;
        public int parentId;
        public String pic;
        public String tag;
    }
}
