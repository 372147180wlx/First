package com.itheima.rbclient.ui.fragment.threelevelcategory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.CategoryProductAdapter;
import com.itheima.rbclient.bean.CategoryProductResponse;
import com.itheima.rbclient.ui.activity.AppDetailActivity;
import com.itheima.rbclient.ui.fragment.BaseFragment;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by zhaoqiang on 2016/8/6 0006.
 */
public class CategoryProduct extends BaseFragment implements HttpLoader.HttpListener, RadioGroup.OnCheckedChangeListener {

    @InjectView(R.id.lv_category_product)
    ListView lv_category_product;
    @InjectView(R.id.rg_category)
    RadioGroup rg_category;
    private CategoryProductAdapter adapter;
    private List<CategoryProductResponse.ListFilter.ValueList> categoryProduct;
    private List<CategoryProductResponse.ListFilter.ValueList> listBrand;
    private List<CategoryProductResponse.ListFilter.ValueList> listPrice;
    private List<CategoryProductResponse.ListFilter.ValueList> listColor;
    private List<CategoryProductResponse.ListFilter> listFilter;
    private FragmentManager mFragmentManager;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.category_fragment_product, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mFragmentManager = getFragmentManager();
        rg_category.setOnCheckedChangeListener(this);
        rg_category.check(R.id.rb_category_brand);
    }

    @Override
    protected void initData() {


        listFilter = new ArrayList<>();
        categoryProduct = new ArrayList<>();
        listBrand = new ArrayList<>();
        listPrice = new ArrayList<>();
        listColor = new ArrayList<>();
        adapter = new CategoryProductAdapter(categoryProduct);
        lv_category_product.setAdapter(adapter);
    }

    //切换到导航的Fragment
    public void switchNavigationFragment(int checkedId) {
        rg_category.check(checkedId);
    }

    @Override
    protected void handleUserInput() {

    }

    @Override
    protected void requestNetData() {
        HttpParams params = new HttpParams();
        params.put("page","1").put("pageNum","10").put("cId","22").put("orderby","saleDown").put("filter","t1");
        HttpLoader.getInstance(App.context).get(RBConstants.URL_CATEGORY_PRODUCT,params, CategoryProductResponse.class,RBConstants.REQUEST_CODE_CATEGORY_PRODUCT,this).setTag(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_CATEGORY_PRODUCT && response instanceof CategoryProductResponse){
            listFilter = ((CategoryProductResponse) response).listFilter;
            for (CategoryProductResponse.ListFilter filter : listFilter) {
                categoryProduct.addAll(filter.valueList);
                if(filter.key.equals("品牌")) {
                    listBrand.addAll(filter.valueList);
                }
                if(filter.key.equals("价格")) {
                    listPrice.addAll(filter.valueList);
                }
                if(filter.key.equals("颜色")) {
                    listColor.addAll(filter.valueList);
                }
            }

            //设置条目点击事件，开启商品详情界面
            lv_category_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(App.context, AppDetailActivity.class);
                    intent.putExtra("sendpId",categoryProduct.get(position).id);
                    startActivity(intent);
                }
            });
        }

//        adapter.notifyDataSetChanged(categoryProduct);
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.rb_category_brand:
                adapter.notifyDataSetChanged(listBrand);
//                switchFragment(FragmentInstanceManager.getInstance().getFragment(CategoryBrandFragment.class));
                break;
            case R.id.rb_category_price:
                adapter.notifyDataSetChanged(listPrice);
//                switchFragment(FragmentInstanceManager.getInstance().getFragment(SearchFragment.class));
                break;
            case R.id.rb_category_color:
                adapter.notifyDataSetChanged(listColor);
//                switchFragment(FragmentInstanceManager.getInstance().getFragment(BrandFragment.class));
                break;
            case R.id.rb_category_all:
                adapter.notifyDataSetChanged(categoryProduct);
                break;
        }
    }

    //提供方法切换Fragment，点击RadioButton的时候需要清空回退栈
    public void switchFragment(Fragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //循环的的pop回退栈
        int backStackEntryCount = mFragmentManager.getBackStackEntryCount();
        while (backStackEntryCount > 0) {
            mFragmentManager.popBackStack();
            backStackEntryCount--;
        }

        transaction.replace(R.id.fl_container, fragment);
        transaction.commit();
    }
}
