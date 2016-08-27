package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * 结算时的地址
 * Created by Administrator on 2016/8/6.
 */
public class CheckAddressResponse implements IResponse{

    /**
     * response :  addressList
     * addressList : [{"addressArea":"洪山区","addressDetail":"文华路文华学院","city":"武汉市","id":134,"isDefault":0,"name":"张瑞丽","phoneNumber":"18986104910","province":"湖北","zipCode":"1008611"},{"id":139,"province":"湖北","city":"武汉市","addressArea":"洪山区","addressDetail":"街道口地铁c口","isDefault":1,"name":"itcast","phoneNumber":"027-81970008","zipCode":"430070"}]
     */

    public String response;
    /**
     * addressArea : 洪山区
     * addressDetail : 文华路文华学院
     * city : 武汉市
     * id : 134
     * isDefault : 0
     * name : 张瑞丽
     * phoneNumber : 18986104910
     * province : 湖北
     * zipCode : 1008611
     */

    public List<AddressListBean> addressList;

    public static class AddressListBean {
        public String addressArea;
        public String addressDetail;
        public String city;
        public int id;
        public int isDefault;
        public String name;
        public String phoneNumber;
        public String province;
        public String zipCode;
    }
}
