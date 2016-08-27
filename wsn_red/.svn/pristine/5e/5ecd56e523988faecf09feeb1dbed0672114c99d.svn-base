package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * 购物车界面的数据bean
 * Created by Administrator on 2016/8/6.
 */
public class CartResponse  implements IResponse{
    /**
     * cart : [{"prodNum":20,"product":{"buyLimit":10,"id":1,"name":"韩版时尚蕾丝裙","number":"100","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":3,"k":"尺码","v":"M"}]}},{"prodNum":30,"product":{"buyLimit":10,"id":2,"name":"粉色毛衣","number":"13","pic":"/images/product/detail/q1.jpg","price":100,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":5,"k":"尺码","v":"XXXL"}]}}]
     * prom : ["促销信息一","促销信息二"]
     * response : cart
     * totalCount : 50
     * totalPoint : 100
     * totalPrice : 10000
     */

    public String response;
    public int totalCount;
    public int totalPoint;
    public int totalPrice;
    /**
     * prodNum : 20
     * product : {"buyLimit":10,"id":1,"name":"韩版时尚蕾丝裙","number":"100","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":3,"k":"尺码","v":"M"}]}
     */

    public List<CartBean> cart;
    public List<String> prom;

    public static class CartBean {
        public int prodNum;
        /**
         * buyLimit : 10
         * id : 1
         * name : 韩版时尚蕾丝裙
         * number : 100
         * pic : /images/product/detail/c3.jpg
         * price : 350
         * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":3,"k":"尺码","v":"M"}]
         */

        public ProductBean product;

        public static class ProductBean {
            public int buyLimit;
            public int id;
            public String name;
            /**
             * 库存数量
             */
            public String number;
            public String pic;
            public int price;
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
}
