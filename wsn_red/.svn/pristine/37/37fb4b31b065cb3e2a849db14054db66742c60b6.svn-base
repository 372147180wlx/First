package com.itheima.rbclient.ui.fragment.threelevelcategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.CategoryFirstAdapter;
import com.itheima.rbclient.bean.CategoryResponse;
import com.itheima.rbclient.protocol.Api;
import com.itheima.rbclient.ui.fragment.BaseFragment;
import com.itheima.rbclient.ui.fragment.FragmentInstanceManager;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by zhaoqiang on 2016/8/9 0009.
 */
public class CategorySecond extends BaseFragment implements HttpLoader.HttpListener {

    @InjectView(R.id.lv_category_list)
    ListView lv_category_list;
    @InjectView(R.id.tv_title)
    TextView tv_title;
    @InjectView(R.id.tv_category_emp)
    TextView tv_category_emp;
    private List<CategoryResponse.CategoryBean> category;
    private CategoryFirstAdapter adapter;
    private String title;
    private List list;
    private int parentId = 1;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);//登记EventBus
        return inflater.inflate(R.layout.category_fragment, null);
    }

    @Override
    public void onResume() {//在fragment可见时再设置标题，保证此时已获取到传递过来的数据
        super.onResume();
        if (title != null) {
            tv_title.setText(title);
        }

        list = new ArrayList();
        for (CategoryResponse.CategoryBean categorybean : category) {
//                if (categorybean.parentId > 0 && categorybean.parentId < 10) {//获取二级分类的数据
            if (categorybean.parentId == parentId) {//获取二级分类的数据
                list.add(categorybean);
            }
        }

        //设置条目点击事件，开启三级分类界面
        lv_category_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(list.get(position));//把点击条目对象传递过去
                swichToChildFragment(FragmentInstanceManager.getInstance().getFragment(CategoryThird.class), true);
            }
        });

        if(list.size() == 0) {
            tv_category_emp.setVisibility(View.VISIBLE);
            lv_category_list.setVisibility(View.GONE);
        }else {
            tv_category_emp.setVisibility(View.GONE);
            lv_category_list.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged(list);
        }
    }

    public void onEvent(String name) {//接收传递过来的对象数据
        title = name;
    }

    public void onEvent(Integer id) {
        parentId = id;
    }

    @Override
    protected void initData() {
        category = new ArrayList<>();
        adapter = new CategoryFirstAdapter(category);
        lv_category_list.setAdapter(adapter);
    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {
        Api.getCategoryData(this).setTag(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_CATEGORY && response instanceof CategoryResponse) {
            category = ((CategoryResponse) response).category;

        }

    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }
}
