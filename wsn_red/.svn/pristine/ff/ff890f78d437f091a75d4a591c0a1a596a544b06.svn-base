package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class RecordResponse  implements IResponse {


    /**
     * available : true
     * bigPic : []
     * buyLimit : 10
     * commentCount : 10
     * id : 25
     * inventoryArea : 全国
     * leftTime : 14000
     * limitPrice : 120
     * marketPrice : 150
     * name : 新款秋装
     * pics : ["/images/product/detail/q23.jpg"]
     * price : 160
     * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}]
     * score : 5
     */

    public ProductBean product;
    /**
     * product : {"available":true,"bigPic":[],"buyLimit":10,"commentCount":10,"id":25,"inventoryArea":"全国","leftTime":14000,"limitPrice":120,"marketPrice":150,"name":"新款秋装","pics":["/images/product/detail/q23.jpg"],"price":160,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}],"score":5}
     * response : product
     */

    public String response;

    public static class ProductBean {
        public boolean available;
        public int buyLimit;
        public int commentCount;
        public int id;
        public String inventoryArea;
        public int leftTime;
        public int limitPrice;
        public int marketPrice;
        public String name;
        public int price;
        public int score;
        public List<?> bigPic;
        public List<String> pics;
        /**
         * id : 1
         * k : 颜色
         * v : 红色
         */

        public List<ProductPropertyBean> productProperty;

        public static class ProductPropertyBean {
            public int id;
            public String k;
            public String v;
        }
    }
}
