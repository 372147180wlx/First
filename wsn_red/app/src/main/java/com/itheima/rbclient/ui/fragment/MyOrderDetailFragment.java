package com.itheima.rbclient.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.OrderDetailResponse;
import com.itheima.rbclient.bean.orderinfo;
import com.itheima.rbclient.ui.fragment.morefragments.accountcenterfragments.MyOrderFragment;
import com.itheima.rbclient.utils.Preutils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.ALog;
import org.senydevpkg.utils.MyToast;

import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/8/10.
 */
public class MyOrderDetailFragment extends BaseFragment implements HttpLoader.HttpListener {
    @InjectView(R.id.order_id)
    TextView order_id;  //订单号
    @InjectView(R.id.order_status)
    TextView order_status;//订单状态
    @InjectView(R.id.order_send)
    TextView order_send;//送货方式
    @InjectView(R.id.order_pay)
    TextView order_pay;//支付方式
    @InjectView(R.id.order_time)
    TextView order_time;//订单生成时间
    @InjectView(R.id.order_sendtime)
    TextView order_sendtime;//发货时间
    @InjectView(R.id.order_invoice)
    TextView order_invoice;//是否开具发票
    @InjectView(R.id.order_sendrequest)
    TextView order_sendrequest;//送货要求
    @InjectView(R.id.tv_1)
    TextView tv_1;//商品名称
    @InjectView(R.id.color)
    TextView color;//颜色
    @InjectView(R.id.size)
    TextView size;//尺寸
    @InjectView(R.id.count)
    TextView count;//数量
    @InjectView(R.id.order_invoice_name)
    TextView order_invoice_name;//发票抬头
    @InjectView(R.id.price)
    TextView price;//价格
    @InjectView(R.id.sumcount)
    TextView sumcount;//商品总计
    @InjectView(R.id.oldprice)
    TextView oldprice;//原始金额
    @InjectView(R.id.sendprice)
    TextView sendprice;//运费
    @InjectView(R.id.discount)
    TextView discount;//促销优惠金额
    @InjectView(R.id.payment)
    TextView payment;//应支付金额
    @InjectView(R.id.name)
    TextView name;//金主姓名
    @InjectView(R.id.phone)
    TextView phone;//金主电话
    @InjectView(R.id.detail)
    TextView detail;//金主地址
    @InjectView(R.id.btn_back)
    Button btn_back;//
    @InjectView(R.id.btn_cancle)
    Button btn_cancle;

    private String id;
    private OrderDetailResponse.DeliveryInfoBean DeliveryInfo;
    private OrderDetailResponse.AddressInfoBean AddressInfo;
    private OrderDetailResponse.InvoiceInfoBean InvoiceInfo;
    private OrderDetailResponse.OrderInfoBean OrderInfo;
    private OrderDetailResponse.PaymentInfoBean PaymentInfo;
    private List<OrderDetailResponse.ProductListBean> ProductList;
    private int id1;
    private List<String> Prom;
    private OrderDetailResponse.CheckoutAddupBean CheckoutAddup;
    private boolean flag = true;
    private int position;
    private String userid;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().registerSticky(this);
        return inflater.inflate(R.layout.fragment_myorder, null);
    }

    public void onEvent(orderinfo info) {
        id = info.id;
        position = info.position;
        requestNetData();
    }

    @Override
    protected void initData() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MyOrderFragment.class));
                requestNetData();
            }
        });

    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {
        // Api.getOrderDetailData(this).setTag(this);
        getconnection();
    }

    private Request getconnection() {
        userid = Preutils.getString(App.context,"userid","20428");
        String url = RBConstants.URL_ORDER_DETAIL;
        HttpParams params = new HttpParams();
        params.put("orderId", id);
        params.addHeader("userid", userid);
        ALog.e("id" + id);
        Class<? extends IResponse> clazz = OrderDetailResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_ORDER_DETAIL;
        return App.HL.get(url, params, clazz, requestCode, this);
    }

    private Request getconnection2() {
        String url = RBConstants.URL_ORDER_CANCLE;
        HttpParams params = new HttpParams();
        params.put("orderId", id);
        params.addHeader("userid", Preutils.getString(App.context, "userid",""));
        ALog.e("id" + id);
        Class<? extends IResponse> clazz = OrderDetailResponse.class;
        int requestCode = RBConstants.REQUEST_CODE_ORDER_CANCLE;
        return App.HL.get(url, params, clazz, requestCode, this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_ORDER_DETAIL && response instanceof OrderDetailResponse) {
            ALog.e("response" + response);
            DeliveryInfo = ((OrderDetailResponse) response).getDeliveryInfo();//支付方式
            AddressInfo = ((OrderDetailResponse) response).getAddressInfo();//地址
            InvoiceInfo = ((OrderDetailResponse) response).getInvoiceInfo();//发票
            OrderInfo = ((OrderDetailResponse) response).getOrderInfo();//订单
            PaymentInfo = ((OrderDetailResponse) response).getPaymentInfo();
            Prom = ((OrderDetailResponse) response).getProm();
            ProductList = (((OrderDetailResponse) response)).getProductList();
            CheckoutAddup = ((OrderDetailResponse) response).getCheckoutAddup();
            ALog.e("DeliveryInfo" + DeliveryInfo + "OrderInfo" + OrderInfo);
            if (OrderInfo != null || AddressInfo != null || PaymentInfo != null || DeliveryInfo != null || InvoiceInfo != null || ProductList != null || CheckoutAddup != null) {
                if (OrderInfo != null) {
                    ALog.e("DeliveryInfo看看" + OrderInfo);
                    order_id.setText(OrderInfo.getOrderId());
                    order_status.setText(OrderInfo.getStatus());

                    order_time.setText(OrderInfo.getTime());
                    order_sendtime.setText(OrderInfo.getTime());
                    if (AddressInfo != null) {
                        //     address.setText(AddressInfo.getName() + "/t" + AddressInfo.getId() + "/t" + AddressInfo.getAddressArea() + "/t" + AddressInfo.getAddressDetail());
                        name.setText(AddressInfo.getName());
                        phone.setText("" + AddressInfo.getId());
                        detail.setText(AddressInfo.getAddressArea() + AddressInfo.getAddressDetail());
                    }
                    if (PaymentInfo != null) {
                        order_pay.setText("" + PaymentInfo.getType());
                    }
                    if (DeliveryInfo != null) {
                        order_send.setText(DeliveryInfo.getType());
                        order_sendrequest.setText("无");
                    }
                    if (InvoiceInfo != null) {
                        order_invoice.setText("是");//是否开具发票"
                        order_invoice_name.setText(InvoiceInfo.getInvoiceTitle());//发票抬头
                    }
                    if (ProductList != null) {
                        tv_1.setText("商品名称：" + ProductList.get(0).getProduct().getName());//商品名称
                        color.setText("颜色  ：" + ProductList.get(0).getProduct().getProductProperty().get(0).getK());//颜色
                        size.setText("尺寸  ：" + ProductList.get(0).getProduct().getProductProperty().get(0).getV());//尺寸
                        count.setText("数量  ：" + ProductList.get(0).getProdNum());//数量
                        price.setText("价格  ：" + ProductList.get(0).getProduct().getPrice());//价格
                    }
                    if (CheckoutAddup != null) {
                        sumcount.setText("" + CheckoutAddup.getTotalCount());//商品总计
                        oldprice.setText("" + CheckoutAddup.getTotalPrice());//原始金额
                        sendprice.setText("10");//运费
                        discount.setText("0");//促销优惠金额
                        payment.setText("" + (CheckoutAddup.getTotalPrice() + 10));//应支付金额
                    }
                    if (OrderInfo.getStatus().equals("未处理")) {
                        //if (clicknum==0){
                        btn_cancle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new AlertDialog.Builder(getActivity()).setTitle("温馨提示")
                                        .setMessage("亲，您真的要放弃我么~~(>_<)~~")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //  Toast.makeText(App.context, "挥泪处理中请耐心等待。。。", Toast.LENGTH_SHORT).show();
                                                getconnection2();
                                                MyToast.show(App.context, "订单取消成功！");
                                                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MyOrderFragment.class));
                                            }
                                        })
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(App.context, "我们会竭力让您满意！", Toast.LENGTH_SHORT).show();
                                            }
                                        }).create().show();
                            }
                        });
                    } else {
                        MyToast.show(App.context, "订单正在被处理，请耐心等待！");
                        //     Toast.makeText(App.context, "订单正在被处理，请耐心等待！", Toast.LENGTH_SHORT).show();
                    }
                }
                return;
            }
            order_id.setText("");
            order_status.setText("");
            order_time.setText("");
            order_sendtime.setText("");
            name.setText("");
            phone.setText("");
            detail.setText("");
            order_pay.setText("");
            order_send.setText("");
            order_sendrequest.setText("");
            order_invoice.setText("");//是否开具发票"
            order_invoice_name.setText("");//发票抬头
            tv_1.setText("商品名称：");//商品名称
            color.setText("颜色  ：");//颜色
            size.setText("尺寸  ：");//尺寸
            count.setText("数量  ：");//数量
            price.setText("价格  ：");//价格
            sumcount.setText("");//商品总计
            oldprice.setText("");//原始金额
            sendprice.setText("1");//运费
            discount.setText("");//促销优惠金额
            payment.setText("");//应支付金额
            MyToast.show(App.context, "该订单暂无信息");
            return;
        }
    }
    //else{
    // Toast.makeText(App.context, "该订单已被处理，请与商家协商！", Toast.LENGTH_SHORT).show();
    //  }


    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        requestNetData();
    }
}
