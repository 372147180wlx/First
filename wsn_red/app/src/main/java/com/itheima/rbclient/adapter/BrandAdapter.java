package com.itheima.rbclient.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.holder.BrandInfoHolder;
import com.itheima.rbclient.holder.BrandTitleHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class BrandAdapter extends AbsBaseAdapter<Object> {



/*    *//**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */

    private BaseHolder holder;
    private Object object;

    private ArrayList<Object> list=new ArrayList<>();

    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */

    public BrandAdapter(List<Object> data) {
        super(data);
    }

    //1.定义条目的类型
    public final int ITEM_TITLE = 0;//title类型的条目
    public final int ITEM_INFO = 1;//info类型的条目

/**
     * 返回条目类型的总数
     */

    @Override
    public int getViewTypeCount() {
        return 2;
    }



    /**
     * 返回指定的position的条目是什么类型的
     */

    @Override
    public int getItemViewType(int position) {
        //通过指定position的数据类型来判断条目的类型
        object = getData().get(position);

        if (object instanceof String )  {
            //说明当前条目是title类型的
            return ITEM_TITLE;
        } else {
            //说明当前条目是info类型的
            return ITEM_INFO;
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            holder = (BaseHolder) convertView.getTag();
        } else {
            holder = onCreateViewHolder(parent, getItemViewType(position));
        }
        Assert.notNull(holder);
        holder.bindData(getItem(position));
        return holder.rootView;
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TITLE:
                //加载title类型的布局
                //绑定数据到title布局
                //但是由于holder封装了加载布局和绑定数据的操作，所以现在只需要根据不同的条目类型
                //返回不同的holder
                holder = new BrandTitleHolder(App.context);

                break;
            case ITEM_INFO:
                //加载info类型的布局
                //绑定数据到info布局
                holder=new BrandInfoHolder(object, App.context);
                break;
        }
        return holder;
    }


}

