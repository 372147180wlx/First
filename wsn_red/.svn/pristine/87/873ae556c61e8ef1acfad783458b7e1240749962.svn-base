package com.itheima.rbclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lingxin on 2016/8/6.
 */
public class HomeAdapter extends BaseAdapter {
    private int[] iv_list = new int[]{
            R.drawable.home_classify_01,
            R.drawable.home_classify_02,
            R.drawable.home_classify_03,
            R.drawable.home_classify_04,
            R.drawable.home_classify_05,
            R.drawable.home_classify_05,
    };
    private String[] tv_list = new String[]{
            "限时抢购", "促销快报", "新品上架", "热门单品", "推荐品牌", "分类列表"
    };

    @Override
    public int getCount() {
        return tv_list.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(App.context, R.layout.home_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.ivHomeLeft.setImageResource(iv_list[position]);
        holder.tvHomeCenter.setText(tv_list[position]);
        holder.ivHomeRight.setImageResource(R.drawable.arrow);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_home_left)
        ImageView ivHomeLeft;
        @InjectView(R.id.tv_home_center)
        TextView tvHomeCenter;
        @InjectView(R.id.iv_home_right)
        ImageView ivHomeRight;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
