package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.adapter.GridViewAdapter;
import com.itheima.rbclient.bean.BrandResponse;
import com.itheima.rbclient.ui.widget.NoScrollGridView;

import org.senydevpkg.base.BaseHolder;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class BrandInfoHolder extends BaseHolder<Object> {

    @InjectView(R.id.listview_item_gridview)
    NoScrollGridView listviewItemGridview;
    private BrandResponse.BrandBean data;

    private ArrayList<Object> objects=new ArrayList<>();
    private Object obj;
    public BrandInfoHolder(Object obj, Context context) {
        super(context);
        this.obj=obj;
        objects= (ArrayList<Object>) obj;

    }

    @Override
    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_info_brand, null);
        ButterKnife.inject(this, view);
        return view;
    }
    @Override
    public void bindData(Object data) {

        GridViewAdapter gridViewAdapter=new GridViewAdapter(objects);
        listviewItemGridview.setAdapter(gridViewAdapter);
        listviewItemGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BrandResponse.BrandBean.ValueBean valueBean= (BrandResponse.BrandBean.ValueBean)objects.get(position);
                Toast.makeText(App.context, valueBean.name, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
