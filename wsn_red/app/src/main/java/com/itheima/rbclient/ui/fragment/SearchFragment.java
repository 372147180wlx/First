package com.itheima.rbclient.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.SearchRecommendAdapter;
import com.itheima.rbclient.bean.KeyWordEvent;
import com.itheima.rbclient.bean.SearchRecommondResponse;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewPropertyAnimator;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class SearchFragment extends BaseFragment implements HttpLoader.HttpListener {

    private static final String TAG = "SearchFragment";
    @InjectView(R.id.lv_search_recommond)
    ListView lvSearchRecommond;
    @InjectView(R.id.ev_keyWord)
    EditText evKeyWord;
    @InjectView(R.id.bt_search)
    Button btSearch;
    @InjectView(R.id.tv_hot_search)
    TextView tvHotSearch;
    @InjectView(R.id.hotsearch_arrow)
    ImageView hotsearchArrow;
    @InjectView(R.id.tv_history_search)
    TextView tvHistorySearch;
    @InjectView(R.id.historysearch_arrow)
    ImageView historysearchArrow;
    @InjectView(R.id.lv_search_history)
    ListView lvSearchHistory;
    private List<String> searchKeywords;
    private SearchRecommendAdapter adapter;
    private SearchRecommendAdapter adapterHistorySearch;
    private ArrayList<String> list;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, null);


    }

    @Override
    protected void initData() {
        searchKeywords = new ArrayList<>();
        adapter = new SearchRecommendAdapter(searchKeywords);
        lvSearchRecommond.setAdapter(adapter);
        list = getHistotySearch();
        adapterHistorySearch = new SearchRecommendAdapter(list);
        lvSearchHistory.setAdapter(adapterHistorySearch);
        lvSearchRecommond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String recommondKeyWord = searchKeywords.get(position);
                setHistoryCache(recommondKeyWord);
                list = getHistotySearch();
                adapterHistorySearch.notifyDataSetChanged(list);
                swichToChildFragment(new SearchReslutFragment(), false);//跳到另一个界面
                KeyWordEvent keyWordEvent = new KeyWordEvent(recommondKeyWord);
                EventBus.getDefault().postSticky(keyWordEvent);

            }
        });
        lvSearchHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String recommondKeyWord = list.get(position);
                swichToChildFragment(new SearchReslutFragment(), false);//跳到另一个界面
                KeyWordEvent keyWordEvent = new KeyWordEvent(recommondKeyWord);
                EventBus.getDefault().postSticky(keyWordEvent);

            }
        });
    }

    private ArrayList<String> getHistotySearch() {
        list = new ArrayList<>();
        SharedPreferences sp = App.context.getSharedPreferences("searchhistory", Context.MODE_PRIVATE);
        String cache = sp.getString("cache", "");
        String[] historyCache = cache.split("&");
        for (int i = 0; i < historyCache.length; i++) {
            list.add(historyCache[i]);
        }
        return list;
    }

    @Override
    protected void handleUserInput() {

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = evKeyWord.getText().toString().trim();
                if (keyWord == null || "".equals(keyWord)) {
                    Toast.makeText(App.context, "请输入搜索关键字", Toast.LENGTH_SHORT).show();
                } else {//判断有没有这个商品，如果有就显示具体的商品列表，如果没有就显示没有数据的页面，用loaddatelayout
                    setHistoryCache(keyWord);
                    list = getHistotySearch();
                    adapterHistorySearch.notifyDataSetChanged(list);
                    swichToChildFragment(new SearchReslutFragment(), false);//跳到另一个界面
                    KeyWordEvent keyWordEvent = new KeyWordEvent(keyWord);
                    EventBus.getDefault().postSticky(keyWordEvent);

                }
            }
        });
    }

    /**
     * 设置历史搜索记录，存到sp中
     *
     * @param keyWord
     */
    public void setHistoryCache(String keyWord) {
        SharedPreferences sp = App.context.getSharedPreferences("searchhistory", Context.MODE_PRIVATE);
        String oldcache = sp.getString("cache", "");
        String newcache = keyWord +"&"+ oldcache  ;
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("cache", newcache);
        edit.commit();
    }

    @Override
    protected void requestNetData() {
        HttpParams params = new HttpParams();
        HttpLoader.getInstance(App.context).get(RBConstants.URL_SEARCH_RCOMMOND, params, SearchRecommondResponse.class, RBConstants.REQUEST_CODE_SEARCH_RCOMMOND, this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_SEARCH_RCOMMOND && response instanceof SearchRecommondResponse) {
            searchKeywords = ((SearchRecommondResponse) response).getSearchKeywords();
            System.out.println("拿到数据了" + searchKeywords.toString());
            adapter.notifyDataSetChanged(searchKeywords);
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        System.out.println("没有返回数据");
    }


    @Override
    public void onDestroyView() {
        App.HL.cancelRequest(this);
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);

        //注册
        EventBus.getDefault().registerSticky(this);
        return rootView;
    }
    //处理
    public void onEvent(String i) {
        String a = i;
        Toast.makeText(getActivity(), a, Toast.LENGTH_SHORT).show();
        Log.e(TAG, "---------------------------------------------------------------------------------onEvent: " + a, null);
    }

    private boolean isExtend = false;//是否展开了
    private boolean isAniming = false;//是否正在执行动画

    @OnClick({R.id.tv_hot_search, R.id.tv_history_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hot_search:
                if (isAniming) {
                    return;
                }
                if (isExtend) {
                    lvSearchRecommond.setVisibility(View.GONE);
                } else {
                    lvSearchRecommond.setVisibility(View.VISIBLE);
                }
                ViewPropertyAnimator.animate(hotsearchArrow).rotationBy(180)
                        .setDuration(200)
                        .setListener(new MyListener())
                        .start();
                isExtend = !isExtend;
                break;
            case R.id.tv_history_search:
                if (isAniming) {
                    return;
                }
                if (isExtend) {
                    lvSearchHistory.setVisibility(View.GONE);
                } else {
                    lvSearchHistory.setVisibility(View.VISIBLE);
                }
                ViewPropertyAnimator.animate(historysearchArrow).rotationBy(180)
                        .setDuration(200)
                        .setListener(new MyListener())
                        .start();
                isExtend = !isExtend;
                break;
        }
    }


    class MyListener implements Animator.AnimatorListener {


        @Override
        public void onAnimationStart(Animator animator) {
            isAniming = true;
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            isAniming = false;
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    }
}
