package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by YF on 2016/8/7.
 */
public class OrderListResponse implements IResponse {

        /**
         * orderList : [{"flag":2,"orderId":"171835","price":208,"status":"未处理","time":"1439529171852"},{"flag":3,"orderId":"286342","price":208,"status":"未处理","time":"1439529286360"},{"flag":1,"orderId":"657072","price":208,"status":"未处理","time":"1439529657077"},{"flag":1,"orderId":"818792","price":208,"status":"未处理","time":"1439529818827"},{"flag":1,"orderId":"432294","price":208,"status":"未处理","time":"1439531432317"},{"flag":2,"orderId":"490503","price":208,"status":"未处理","time":"1439531490519"},{"flag":1,"orderId":"624490","price":208,"status":"未处理","time":"1439533624526"},{"flag":3,"orderId":"873768","price":450,"status":"未处理","time":"1449037873786"},{"flag":2,"orderId":"879495","price":450,"status":"未处理","time":"1449037879509"},{"flag":2,"orderId":"983263","price":450,"status":"未处理","time":"1449107002398"}]
         * response : orderList
         */

        private String response;
        /**
         * flag : 2
         * orderId : 171835
         * price : 208
         * status : 未处理
         * time : 1439529171852
         */

        private List<OrderListBean> orderList;

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean {
            private int flag;
            private String orderId;
            private int price;
            private String status;
            private String time;

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
