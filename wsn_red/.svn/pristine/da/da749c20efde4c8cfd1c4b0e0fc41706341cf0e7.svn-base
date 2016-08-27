package com.itheima.rbclient.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.RecordAdapter;
import com.itheima.rbclient.bean.RecordResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.ALog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/6.
 */
public class RecordFragment extends BaseFragment {
    private ListView lv_record;
    private Button back;
    public String name;
    public RecordAdapter adapter;
    private SharedPreferences sp;
    private String sendId;//获取详情界面保存的id
    private String[] split;
    private List<RecordResponse.ProductBean> list;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(App.context, R.layout.fragment_record, null);
       //        EventBus.getDefault().registerSticky(this);

        sp = getActivity().getSharedPreferences("memory", 0);
        sendId = sp.getString("sendMemory", "");
        Log.e("sendId", "----------------" + sendId);
        //裁剪字符串得到字符串数组
        split = sendId.split("&");
        lv_record = (ListView) view.findViewById(R.id.lv_record);
        back = (Button) view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
            }
        });
        requestNetData();
        return view;
    }

    @Override
    protected void initData() {
        list = new ArrayList();
        if (adapter == null){
        adapter = new RecordAdapter(list);}
        lv_record.setAdapter(adapter);
        System.out.println("去去去去去去");
    }

    /*public void onEvent(Object event) {
        System.out.println("ninnnnnnnnnnnnnnnnn");
        if (event instanceof AppDetailEvent) {
            AppDetailResponse.ProductBean Revent = (AppDetailResponse.ProductBean) event;
            record.add(Revent);
            EventBus.getDefault().postSticky(record);
            adapter.notifyDataSetChanged(record);
        }
    }*/

    @Override
    protected void handleUserInput() {
    }

    @Override
    protected void requestNetData() {
        if (split.length > 1) {
            for (int i = 1; i < split.length; i++) {
                //得到所有点击过的商品id
                String getId = split[i];
                Log.e("getOne", "=====================" + getId);
                HttpParams params = new HttpParams();
                params.put("pId", getId);
                HttpLoader.getInstance(getActivity()).get(RBConstants.URL_APP_DETAIL, params, RecordResponse.class,
                        RBConstants.REQUEST_CODE_APP_DETAIL, new HttpLoader.HttpListener() {
                            @Override
                            public void onGetResponseSuccess(int requestCode, IResponse response) {
                                if (requestCode == RBConstants.REQUEST_CODE_APP_DETAIL && response instanceof RecordResponse) {
                                    //请求成功以后获取商品详情的bean对象
                                    RecordResponse.ProductBean record = ((RecordResponse) response).product;
                                    list.add(record);
                                    //initData();
                                    adapter.notifyDataSetChanged(list);
                                    ALog.e("gggggggggggggggggggg" + record.name);
                                }
                            }
                            @Override
                            public void onGetResponseError(int requestCode, VolleyError error) {
                                ALog.e("llllllllllllllllllllllllllllllll" );
                            }
                        });
            }
        } else {
            ALog.e("暂无浏览信息");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        requestNetData();
    }
}
