package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.HelpCenterDetailAdapter;
import com.itheima.rbclient.bean.HelpCenterDetailResponse;
import com.itheima.rbclient.bean.StringIDBean;
import com.itheima.rbclient.protocol.Api;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/8/6.
 */
public class OthersFragment extends BaseFragment  implements HttpLoader.HttpListener{

    private ListView listview;
   private List<HelpCenterDetailResponse.HelpDetailListBean> detail;
   private HelpCenterDetailAdapter adapter;
    private Long id;
    private Button back;


    private TextView tv_guide_content;
    private TextView tv_guide_title;
    private View view;
    private TextView tab_title;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view = View.inflate(App.context, R.layout.item_help_center_detail,null);
        //listview = (ListView) view.findViewById(R.id.listview);
        tv_guide_title = (TextView) view.findViewById(R.id.tv_guide_title);
        tab_title = (TextView) view.findViewById(R.id.tab_title);
        tv_guide_content = (TextView) view.findViewById(R.id.tv_guide_content);
        back = (Button) view.findViewById(R.id.back);
        EventBus.getDefault().registerSticky(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(HelpCenterFragment.class));
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        detail = new ArrayList<>();
        adapter = new HelpCenterDetailAdapter(detail);
       // listview.setAdapter(adapter);
    }
    @Override
    protected void handleUserInput() {

    }
    public void onEvent(StringIDBean bean){
        id = bean.id;
        requestNetData();
        System.out.println("   "+id);
        if (id==1){
            tab_title.setText("购物指南");
        }
        if (id==2){
            tab_title.setText("配送方式");
        }
        if (id==3){
            tab_title.setText("售后服务");
        }
        if (id==4){
            tab_title.setText("产品更新");
        }
    }

    @Override
    protected void requestNetData() {
        HttpParams params = new HttpParams();
        params.put("id", String.valueOf(id));
        Api.getHelpCenterDetailData(this).setTag(this);
      //  System.out.println("hjksklfnl;mdvg"+Api.getHelpCenterDetailData(this).setTag(this));
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
       // App.HL.cancelRequest(this);

    }
    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        HelpCenterDetailResponse resp = (HelpCenterDetailResponse) response;
        if (requestCode == RBConstants.REQUEST_CODE_HELP_CENTER_DETAIL&& response instanceof HelpCenterDetailResponse) {
            detail = ((HelpCenterDetailResponse) response).getHelpDetailList();
            System.out.println("nihaohaohaohao"+resp.getHelpDetailList().get((int) (id-1)).getTitle());
            tv_guide_title.setText(resp.getHelpDetailList().get((int) (id-1)).getTitle());
            tv_guide_content.setText(resp.getHelpDetailList().get((int) (id-1)).getContent());
            System.out.println("详情"+id);
        }
        //adapter.notifyDataSetChanged(detail);
    }
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}
