package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by ocean on 2016/8/7.
 */
public class AppDetailResponse implements IResponse {

    /**
     * available : true
     * bigPic : ["/images/product/detail/a1.jpg","/images/product/detail/a2.jpg","/images/product/detail/a3.jpg","/images/product/detail/a4.jpg"]
     * buyLimit : 10
     * commentCount : 10
     * id : 3
     * inventoryArea : 大武汉
     * leftTime : 16000
     * limitPrice : 90
     * marketPrice : 500
     * name : 女裙
     * pics : ["/images/product/detail/c1.jpg","/images/product/detail/c2.jpg","/images/product/detail/c3.jpg","/images/product/detail/c4.jpg"]
     * price : 300
     * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}]
     * score : 3
     */

    public ProductBean product;
    /**
     * product : {"available":true,"bigPic":["/images/product/detail/a1.jpg","/images/product/detail/a2.jpg","/images/product/detail/a3.jpg","/images/product/detail/a4.jpg"],"buyLimit":10,"commentCount":10,"id":3,"inventoryArea":"大武汉","leftTime":16000,"limitPrice":90,"marketPrice":500,"name":"女裙","pics":["/images/product/detail/c1.jpg","/images/product/detail/c2.jpg","/images/product/detail/c3.jpg","/images/product/detail/c4.jpg"],"price":300,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}],"score":3}
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
        public List<String> bigPic;
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
        public List<ProductRecordBean> productRecord;
        public static class ProductRecordBean {
            public List<String> pics;
            public int marketPrice;
            public String name;
            public int price;
            public int commentCount;
        }
}
