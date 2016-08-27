package com.itheima.rbclient.ui.fragment;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.CheckOutAdapter;
import com.itheima.rbclient.bean.AddressEvent;
import com.itheima.rbclient.bean.CheckoutResponse;
import com.itheima.rbclient.bean.CommitResponse;
import com.itheima.rbclient.bean.CommitSuccessEvent;
import com.itheima.rbclient.bean.IEvent;
import com.itheima.rbclient.bean.InvoiceEvent;
import com.itheima.rbclient.bean.LoginEvent;
import com.itheima.rbclient.bean.ParamEvent;
import com.itheima.rbclient.bean.PayModeEvent;
import com.itheima.rbclient.bean.SendTimeEvent;
import com.itheima.rbclient.ui.HeightAnim;
import com.itheima.rbclient.ui.activity.CheckAddressActivity;
import com.itheima.rbclient.ui.activity.CheckInvoiceActivity;
import com.itheima.rbclient.ui.activity.CheckModeActivity;
import com.itheima.rbclient.ui.activity.CheckTimeActivity;
import com.itheima.rbclient.ui.activity.LoginActivity;
import com.itheima.rbclient.utils.Preutils;
import com.nineoldandroids.view.ViewPropertyAnimator;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class CheckOutFragment extends BaseFragment implements HttpLoader.HttpListener{
    private TextView tv_check_mode_sub;    //支付方式
    private TextView tv_check_time_sub;   //送货时间
    private TextView tv_check_invoice_sub;  //发票
    private TextView tv_check_address_sub1; //地址
    private TextView tv_check_address_sub2;
    private TextView tv_check_address_sub3;
    private String[] sArr = {"周一至周五送货","双休日及公共假期送货","时间不限，工作日双休日及公共假期均可送货"};
    private String[] conArr = {"图书","音响","游戏","软件","资料"};

    private ListView lv_cart;
    private List<CheckoutResponse.ProductListBean> productList;
    private CheckOutAdapter adapter;

    private LinearLayout ll_five;
    private ScrollView sv_five;
    private ImageView iv_arrow1;

    private TextView tv_cartheader_count;  //数量总计
    private TextView tv_cartheader_yf; //运费
    private TextView tv_cartheader_yhj; //积分
    private TextView tv_cartheader_total; //应支付
    private TextView tv_yh; //优惠信息

    private Button btn_commit;
    private CheckoutResponse resp;
    private String para;
    private AddressEvent addEvent;
    private PayModeEvent pevent;
    private SendTimeEvent sendEvent;
    private InvoiceEvent inEvent;
    private int state = 0;  //记录是否点击提交按钮
    private String log = "";

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //登陆
/*        String username = "test";
        String pwd = "test";
        HttpParams p = new HttpParams().put("username", username).put("password", pwd);
        App.HL.post(RBConstants.URL_LOGIN,p,LoginResponse.class, RBConstants.REQUEST_CODE_LOGIN, this);*/
        //
       EventBus.getDefault().registerSticky(this);
        View view = iniView();
        return view;
    }

    /**
     * 初始化界面
     */
    private View iniView() {
        View view = View.inflate(App.context,R.layout.fragment_check_out,null);
        btn_commit = (Button) view.findViewById(R.id.btn_commit);
        lv_cart = (ListView) view.findViewById(R.id.lv_cart);
        ll_five = (LinearLayout) view.findViewById(R.id.ll_five);
        sv_five = (ScrollView) view.findViewById(R.id.sv_five);
        iv_arrow1 = (ImageView) view.findViewById(R.id.iv_arrow1);
        tv_cartheader_count = (TextView) view.findViewById(R.id.tv_cartheader_count);
        tv_cartheader_yf = (TextView) view.findViewById(R.id.tv_cartheader_yf);
        tv_cartheader_yhj = (TextView) view.findViewById(R.id.tv_cartheader_yhj);
        tv_cartheader_total = (TextView) view.findViewById(R.id.tv_cartheader_total);
        tv_yh = (TextView) view.findViewById(R.id.tv_yh);
        tv_check_mode_sub = (TextView) view.findViewById(R.id.tv_check_mode_sub);
        tv_check_time_sub = (TextView) view.findViewById(R.id.tv_check_time_sub);
        tv_check_invoice_sub = (TextView) view.findViewById(R.id.tv_check_invoice_sub);
        tv_check_address_sub1 = (TextView) view.findViewById(R.id.tv_check_address_sub1);
        tv_check_address_sub2 = (TextView) view.findViewById(R.id.tv_check_address_sub2);
        tv_check_address_sub3 = (TextView) view.findViewById(R.id.tv_check_address_sub3);
        return view;
    }
    @Override
    protected void initData() {
        productList = new ArrayList<>();
        adapter = new CheckOutAdapter(productList);
        lv_cart.setAdapter(adapter);

        sv_five.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                sv_five.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                maxHeight = sv_five.getHeight();
            }
        });
    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {

    }

    public void reqData() {
        String url = RBConstants.URL_CHECKOUT;
        HttpParams params = new HttpParams();
        params.put("sku", para);
        params.addHeader("userid", Preutils.getString(App.context, "userid",""));
        Class<? extends IResponse> clazz = CheckoutResponse.class;
        int requestcode = RBConstants.REQUEST_CODE_CHECKOUT;
        App.HL.get(url, params, clazz, requestcode, this,false);
    }
    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {

        //未登录
        if(requestCode == RBConstants.REQUEST_CODE_CHECKOUT && response instanceof CheckoutResponse){
            if("error".equals(((CheckoutResponse) response).response) &&((CheckoutResponse) response).error_code.equals("1533")){
                    Intent intent=new Intent(App.context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                //MyToast.show(App.context, "未登录，请先登录");
               // mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(CartFragment.class));
                return;
            }
        }
        //提交订单
        if(requestCode == RBConstants.REQUEST_CODE_APP_COMMIT && response instanceof CommitResponse){
            if("error".equals(((CommitResponse) response).response)){
                    Intent intent=new Intent(App.context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                //MyToast.show(App.context, "未登录，请先登录");
                // mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(CartFragment.class));
                return;
            }
            CommitResponse resp2 = (CommitResponse) response;
            EventBus.getDefault().postSticky(new CommitSuccessEvent(resp2.orderInfo.orderId,resp2.orderInfo.paymentType,resp2.orderInfo.price));
        }
        //结算界面
        if (requestCode == RBConstants.REQUEST_CODE_CHECKOUT && response instanceof CheckoutResponse){
            resp = (CheckoutResponse) response;
            productList = ((CheckoutResponse) response).productList;
            tv_cartheader_count.setText(resp.checkoutAddup.totalCount+"");
            tv_cartheader_yf.setText(resp.checkoutAddup.freight+"");
            tv_cartheader_yhj.setText(resp.checkoutAddup.totalPoint+"");
            tv_cartheader_total.setText(resp.checkoutAddup.totalPrice+"");
            tv_yh.setText(resp.checkoutProm.toString());
        }
        adapter.notifyDataSetChanged(productList);

    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }
    private int minHeight = 0;//5行文本的高度
    private int maxHeight;//全部文本的高度
    private boolean isExtend = true;//是否展开了
    private boolean isAniming = false;//是否正在执行动画
    @OnClick({R.id.btn_commitcheck,R.id.btn_commit,R.id.tv_ddinfo,R.id.ll_check_address,R.id.btn_back_cart,R.id.tv_check_mode,R.id.tv_check_time,R.id.tv_check_invoice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_commitcheck:
            case R.id.btn_commit:  //提交订单
                boolean retu = Commit();
                if(!retu) {
                    return;
                }
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(CommitSuccessFragment.class));
                break;
            case R.id.btn_back_cart:  //返回购物车
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(CartFragment.class));
                //switchNavigationFragment(R.id.rb_content_fragment_shopping);
                //switchNavigationFragment(R.id.rb_content_fragment_home);
                break;
            case R.id.tv_check_mode:  //支付方式
                Intent intent = new Intent(App.context, CheckModeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tv_check_time:  //送货时间
                Intent intent2 = new Intent(App.context, CheckTimeActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
            case R.id.tv_check_invoice:  //发票
                Intent intent3 = new Intent(App.context, CheckInvoiceActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent3);
                break;
            case R.id.ll_check_address:  //收货地址
                Intent intent4 = new Intent(App.context, CheckAddressActivity.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent4);
                break;
            case R.id.tv_ddinfo:  //点击展开订单信息
                if(isAniming){
                    return;
                }
                HeightAnim anim = null;
                if(isExtend){
                    //执行收缩
                    anim = new HeightAnim(sv_five, maxHeight, minHeight);
                }else {
                    //执行展开动画
                    anim = new HeightAnim(sv_five, minHeight, maxHeight);
                }
                anim.start(350);

                //设置监听器
                anim.setOnHeightChangeListener(new HeightAnim.OnHeightChangeListener() {
                    @Override
                    public void onHeightChange(int animatedValue) {
                        //正值其实是向上滑动,
//					scrollView.scrollBy(0,1000);
                        sv_five.scrollBy(0,maxHeight-minHeight);
                    }
                });
                        //更改标记
                        isExtend = !isExtend;

                //让箭头旋转
                ViewPropertyAnimator.animate(iv_arrow1)
                        .rotationBy(180)
                        .setDuration(350)
                        .setListener(new MyListener())
                        .start();
                break;
        }
    }



    public void onEvent(IEvent event) {
        if(event instanceof PayModeEvent) {
            pevent = (PayModeEvent)event;
            switch (pevent.mode) {
                case 1:
                    tv_check_mode_sub.setText("到付-现金");
                    break;
                case 2:
                    tv_check_mode_sub.setText("到付-POS机");
                    break;
                case 3:
                    tv_check_mode_sub.setText("支付宝");
                    break;
            }
        }else if(event instanceof SendTimeEvent) {
            tv_check_time_sub.setVisibility(View.VISIBLE);
            sendEvent = (SendTimeEvent) event;
                    tv_check_time_sub.setText(sArr[sendEvent.time - 1]);
        }else if(event instanceof InvoiceEvent) {
            inEvent = (InvoiceEvent) event;
            tv_check_invoice_sub.setText(inEvent.ttDes+"/"+conArr[inEvent.content - 1]);
        }else if(event instanceof AddressEvent) {
            addEvent = (AddressEvent) event;
            tv_check_address_sub1.setVisibility(View.VISIBLE);
            tv_check_address_sub2.setVisibility(View.VISIBLE);
            tv_check_address_sub3.setVisibility(View.VISIBLE);
            tv_check_address_sub1.setText(addEvent.data.name);
            tv_check_address_sub2.setText(addEvent.data.phoneNumber);
            tv_check_address_sub3.setText(addEvent.data.province+ addEvent.data.city+ addEvent.data.addressArea+ addEvent.data.addressDetail);
        }else if(event instanceof LoginEvent){
            LoginEvent logEvent = (LoginEvent) event;
            log = logEvent.log;
        }
    }
    class MyListener implements Animator.AnimatorListener, com.nineoldandroids.animation.Animator.AnimatorListener {
        @Override
        public void onAnimationCancel(Animator arg0) {
        }
        @Override
        public void onAnimationEnd(Animator arg0) {
            isAniming = false;
        }
        @Override
        public void onAnimationRepeat(Animator arg0) {
        }
        @Override
        public void onAnimationStart(Animator arg0) {
            isAniming = true;
        }

        @Override
        public void onAnimationStart(com.nineoldandroids.animation.Animator animator) {
            isAniming = true;
        }

        @Override
        public void onAnimationEnd(com.nineoldandroids.animation.Animator animator) {
            isAniming = false;
        }

        @Override
        public void onAnimationCancel(com.nineoldandroids.animation.Animator animator) {

        }

        @Override
        public void onAnimationRepeat(com.nineoldandroids.animation.Animator animator) {

        }
    }

    /**
     * 提交订单
     */
    public boolean Commit() {
        if(addEvent == null || pevent == null || sendEvent == null ||inEvent == null) {
            MyToast.show(App.context, "请完善订单信息");
            return false;
        }
        String url = RBConstants.URL_CHECK_COMMIT;
        HttpParams params = new HttpParams();
        params.put("sku", para);
        params.addHeader("userid", Preutils.getString(App.context, "userid",""));
        params.put("addressId",addEvent.data.id+"");  //地址簿ID
        params.put("paymentType",pevent.mode+""); //支付方式
        params.put("deliveryType",sendEvent.time+""); //送货时间
        if(inEvent.ttDes.equals("个人")) {
            params.put("invoiceType","1");  //发票类型
        }else {
            params.put("invoiceType","2");  //发票类型
        }
        params.put("invoiceTitle"	,"传智慧播客教育科技有限公司");  //发票抬头
        params.put("invoiceContent",inEvent.content+"");   //发票内容
        Class<? extends IResponse> clazz = CommitResponse.class;
        int requestcode = RBConstants.REQUEST_CODE_APP_COMMIT;
        App.HL.get(url, params, clazz, requestcode, this,false);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        ParamEvent event = EventBus.getDefault().getStickyEvent(ParamEvent.class);
        //商品参数
        para = event.para;
        reqData();
    }
}

