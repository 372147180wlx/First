package com.itheima.rbclient.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;

import org.senydevpkg.net.HttpLoader;

import java.util.ArrayList;

/**
 * Created by ocean on 2016/8/7.
 */
public class ImageScaleActivity extends Activity {

    private ViewPager vp_image_scale;
    private ArrayList<String> urlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scale);
        vp_image_scale = (ViewPager) findViewById(R.id.vp_image_scale);
        //获取传递过来的大图集合
        urlList = getIntent().getStringArrayListExtra("bigPic");
        if(urlList.size()==0){
            urlList.add("/images/product/detail/y2.jpg");
            urlList.add("/images/product/detail/r2.jpg");
            urlList.add("/images/product/detail/w2.jpg");
        }
            //填充数据
            ImageScaleAdapter adapter = new ImageScaleAdapter();
            vp_image_scale.setAdapter(adapter);
    }

    private class ImageScaleAdapter extends PagerAdapter{
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(ImageScaleActivity.this);
            HttpLoader.getInstance(App.context).getImageLoader().get(RBConstants.URL_SERVER+urlList.get(position),
                    ImageLoader.getImageListener(imageView, R.drawable.product_loading,R.drawable.product_loading));
            //将ImageView加入到ViewPager中
            container.addView(imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return urlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View) object);
        }
    }
}
