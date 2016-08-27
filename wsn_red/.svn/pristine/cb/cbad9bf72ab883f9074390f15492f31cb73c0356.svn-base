package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.HelpCenterDetailResponse;

import org.senydevpkg.base.BaseHolder;

/**
 * Created by Administrator on 2016/8/7.
 */
public class HelpCenterDetailHolder extends BaseHolder<HelpCenterDetailResponse.HelpDetailListBean> {

    private TextView title;
    private TextView context;

    public HelpCenterDetailHolder(Context context) {
        super(context);
    }

    protected View initView() {
        View view = View.inflate(App.context, R.layout.item_title, null);
        title = (TextView) view.findViewById(R.id.title);
        context = (TextView) view.findViewById(R.id.context);
        return view;
    }

    @Override
    public void bindData(HelpCenterDetailResponse.HelpDetailListBean data) {
      //  List<HelpCenterDetailResponse.HelpDetailListBean> detailList = datas.getHelpDetailList();
        //title.setText(resp.getHelpDetailList().get(0).getTitle());
        //System.out.println("内容"+ data.getContent());*/
        //

        title.setText(data.getTitle());
        super.bindData(data);
    }

}
