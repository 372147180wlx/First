package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by zhaoqiang on 2016/8/6 0006.
 */
public class CategoryProductResponse implements IResponse {

    public String response;
    public int listCount;

    public List<ListFilter> listFilter;
        public static class ListFilter {
            public String key;

            public List<ValueList> valueList;
                public static class ValueList {
                    public String id;
                    public String name;
                }
        }

    public List<ProductList> productList;
        public static class ProductList {

        }

}
