package com.itheima.rbclient.protocol;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.rbclient.bean.MsgIdBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 存放需要传递信息数据库的dao
 * 增删改查
 * Created by ocean on 2016/8/10.
 */
public class MessageDao {
    private MessageDBOpenHelper helper;
    /**
     * 只有一个有参的构造方法,要求必须传入上下文
     * @param context
     */
    public MessageDao(Context context){
        helper = new MessageDBOpenHelper(context);
    }

    /**
     * 添加一个id
     */
    public void add(int pid){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into message (pid) values (?)", new Object[]{pid});
        db.close();//释放资源
    }

    /**
     * 获取全部的id
     * @return
     */
    public List<MsgIdBean> findAll(){
        List<MsgIdBean> message =new ArrayList<MsgIdBean>();
        SQLiteDatabase  db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select pid from MsgIdBean", null);
        while(cursor.moveToNext()){
            int pid = cursor.getInt(0);
            MsgIdBean msg = new MsgIdBean();
            msg.setPid(pid);
            message.add(msg);
        }
        cursor.close();
        db.close();
        return message;
    }
}
