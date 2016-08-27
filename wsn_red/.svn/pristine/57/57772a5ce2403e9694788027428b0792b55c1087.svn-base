package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.HelpCenterAdapter;
import com.itheima.rbclient.bean.HelpCenterResponse;
import com.itheima.rbclient.bean.StringIDBean;
import com.itheima.rbclient.protocol.Api;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


public class HelpCenterFragment extends BaseFragment  implements HttpLoader.HttpListener {

    protected static final String TAG = "BaseFragment";
    private ListView lv_help_center;
    private HelpCenterAdapter adapter;
    private List<HelpCenterResponse.HelpListBean> help;
    private Button back;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = View.inflate(App.context, R.layout.fragment_help_center, null);
        lv_help_center = (ListView) view.findViewById(R.id.lv_help_center);
        back = (Button) view.findViewById(R.id.back);
        lv_help_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id = help.get(position).getId();
                EventBus.getDefault().postSticky(new StringIDBean(id));
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(OthersFragment.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        help = new ArrayList<>();
        adapter = new HelpCenterAdapter(help);
        lv_help_center.setAdapter(adapter);
    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {

        Api.getHelpCenterData(this).setTag(this);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //App.HL.cancelRequest(this);
    }


    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_HELP_CENTER && response instanceof HelpCenterResponse) {
            help = ((HelpCenterResponse) response).getHelpList();
            System.out.println(help);
        }
        adapter.notifyDataSetChanged(help);
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

}
