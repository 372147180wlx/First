package com.itheima.rbclient.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.AppDetailCommResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;

import java.util.List;

/**
 * Created by ocean on 2016/8/6.
 */
public class AppDetailCommentActivity extends Activity {

    private ListView iv_detail_comment;
    private MyDetailCommentAdapter adapter;
    private int pId;
    private int commentNum;
    private List<AppDetailCommResponse.CommentBean> comment;
    private Button bt_comment_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_comment);
        //获取传递过来的商品id
        pId = getIntent().getIntExtra("pId",0);
        bt_comment_back = (Button) findViewById(R.id.bt_comment_back);

        //获取传递过来的评论数量
//        commentNum = getIntent().getIntExtra("commentNum", 0);

        bt_comment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestData();
    }

    /**
     * 请求数据
     */
    private void requestData() {
        HttpParams params = new HttpParams();
        params.put("pId", pId+"").put("page","1").put("pageNum","10");
        HttpLoader.getInstance(this).get(RBConstants.URL_APP_DETAIL_COMM, params, AppDetailCommResponse.class,
            RBConstants.REQUEST_CODE_APP_DETAIL_COMM, new HttpLoader.HttpListener() {
                @Override
                public void onGetResponseSuccess(int requestCode, IResponse response) {
                    if (requestCode == RBConstants.REQUEST_CODE_APP_DETAIL_COMM && response instanceof AppDetailCommResponse){
                        //请求成功以后获取当前商品所有评论详情集合
                        comment = ((AppDetailCommResponse) response).comment;

                        if(comment.size() == 0){
                            MyToast.show(AppDetailCommentActivity.this,"暂无评论");
                        }else{
                            //请求网络成功后，获取参数并设置参数
                            iv_detail_comment = (ListView) findViewById(R.id.iv_detail_comment);
                            adapter = new MyDetailCommentAdapter(comment);
                            iv_detail_comment.setAdapter(adapter);
                        }
                    }
                }
                @Override
                public void onGetResponseError(int requestCode, VolleyError error) {
                }
            });
    }

    private class MyDetailCommentAdapter extends BaseAdapter{
        private  List<AppDetailCommResponse.CommentBean> comment;


        public MyDetailCommentAdapter(List<AppDetailCommResponse.CommentBean> comment) {
            this.comment = comment;

        }

        @Override
        public int getCount() {
            return comment.size();
        }

        @Override
        public Object getItem(int position) {
            return comment.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                convertView = View.inflate(App.context,R.layout.item_comment,null);
                viewHolder = new ViewHolder();
                viewHolder.tv_comment_user = (TextView) convertView.findViewById(R.id.tv_comment_user);
                viewHolder.tv_comment_time = (TextView) convertView.findViewById(R.id.tv_comment_time);
                viewHolder.tv_detailmsg_buyname = (TextView) convertView.findViewById(R.id.tv_detailmsg_buyname);
                viewHolder.tv_comment_msg = (TextView) convertView.findViewById(R.id.tv_comment_msg);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_comment_user.setText(comment.get(position).username);
            viewHolder.tv_comment_time.setText(comment.get(position).time+"");
            viewHolder.tv_detailmsg_buyname.setText(comment.get(position).title);
            viewHolder.tv_comment_msg.setText(comment.get(position).content);
            return convertView;
        }
        class ViewHolder{
            TextView tv_comment_user;//用户名
            TextView tv_comment_time;//评价时间
            TextView tv_detailmsg_buyname;//商品名
            TextView tv_comment_msg;//评价信息
        }
    }
}
