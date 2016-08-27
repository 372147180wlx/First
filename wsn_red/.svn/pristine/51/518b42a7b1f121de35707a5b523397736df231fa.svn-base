package com.itheima.rbclient.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;

/**
 * Created by Administrator on 2016/8/6.
 */
public class FeedBackFragment extends BaseFragment{

    private EditText rt_comment;
    private EditText et_tel;
    private Button back;
    private Button commit;
    private String telephone;
    private View view;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(App.context, R.layout.feedback, null);
        rt_comment = (EditText) view.findViewById(R.id.et_comment);
        et_tel = (EditText) view.findViewById(R.id.et_tel);
        back = (Button) view.findViewById(R.id.back_feedback);
        commit = (Button) view.findViewById(R.id.commit_feedback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {

            private String comment;

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setTitle("温馨提示")
                        .setMessage("感谢您的意见，祝您生活愉快！")
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mMainActivity.switchFragment(FragmentInstanceManager.getInstance().getFragment(MoreFragment.class));
                            }
                        }).create().show();
                comment = rt_comment.getText().toString();
                telephone = et_tel.getText().toString();
                System.out.println(comment + "   " + telephone);
            }
        });
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {

    }
}
