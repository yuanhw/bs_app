package cn.wyh.bs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.inner.GeoPoint;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.home.CityActivity;
import cn.wyh.bs.bean.Tab;
import cn.wyh.bs.activity.fragment.*;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.LocationUtils;
import cn.wyh.bs.storage.KeyValueTable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{

    private FragmentTabHost mTabHost; //底部tab控件
    private final List<Tab> list = new ArrayList<>(4); //底部tab对象列表
    private final View[] views = new View[4]; //标题栏视图对象列表
    private ImageView imageView; //消息tab实心红点图片视图
    private Toolbar toolbar; //标题栏控件
    private View drop_down; //下拉视图
    private TextView city; //当前城市文本框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        setContentView(R.layout.activity_main);

        /* 获取标题栏控件 */
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        //获取位置
        location();
        initToolbarContext(); //实例化标题栏
        initTab(); //实例化底部菜单栏
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    private void initToolbarContext() {
        this.views[0] = View.inflate(this, R.layout.top_main, null);
        this.views[1] = View.inflate(this, R.layout.top_info, null);
        this.views[2] = View.inflate(this, R.layout.top_share, null);
        this.views[3] = View.inflate(this, R.layout.top_person, null);

        /* 设置toolbar */
        this.toolbar.removeAllViews();
        this.toolbar.addView(views[0]);

        /* 获取下拉选择城市视图 */
        this.drop_down = this.views[0].findViewById(R.id.top_main_place);
        this.city = (TextView) this.views[0].findViewById(R.id.top_main_city);

        /* 下拉框视图事件 */
        this.drop_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CityActivity.class);
                intent.putExtra("cityName", city.getText().toString());
                startActivityForResult(intent, Const.REQUEST_CITY_ACTIVITY_CODE);
            }
        });
    }

    /* 初始底部tab视图 */
    private void initTab() {
        Tab tab_home = new Tab("home", "首页", R.drawable.tab_home_selector, TabHomeFragment.class);
        Tab tab_info = new Tab("info", "消息", R.drawable.tab_info_selector, TabMessageFragment.class);
        Tab tab_share = new Tab("share", "地主圈", R.drawable.tab_share_selector, TabShareFragment.class);
        Tab tab_person = new Tab("person", "我的", R.drawable.tab_person_selector, TabPersonFragment.class);

        list.add(tab_home);
        list.add(tab_info);
        list.add(tab_share);
        list.add(tab_person);

        /* 获取tabHost控件 */
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        /* 设置tab页内容展示控件 */
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_tab_content);

        /* 填充mTabHost*/
        for (Tab tab : list) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tab.getTag());
            tabSpec.setIndicator(buildView(tab));
            mTabHost.addTab(tabSpec, tab.getMainFragment(), null);
        }

        /* 当前所展示tab */
        mTabHost.setCurrentTabByTag(tab_home.getTag());

        /* tab切换事件 */
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId) {
                    case "home":
                        //Log.i("mms_tab", "666");
                        location();
                        toolbar.removeAllViews();
                        toolbar.addView(views[0]);
                        break;
                    case "info":
                        toolbar.removeAllViews();
                        toolbar.addView(views[1]);
                        break;
                    case "share":
                        toolbar.removeAllViews();
                        toolbar.addView(views[2]);
                        break;
                    case "person":
                        toolbar.removeAllViews();
                        toolbar.addView(views[3]);
                        break;
                }
            }
        });
    }

    private View buildView(Tab tab) {
        int layout = R.layout.tab_indicator;
        if (tab.getTag().equals("info")) {
            layout = R.layout.tab_indicator_show;
        }
        View view = LayoutInflater.from(this).inflate(layout, null);
        ImageView tabImg = (ImageView) view.findViewById(R.id.tab_img);
        TextView text = (TextView) view.findViewById(R.id.tab_text);
        tabImg.setImageResource(tab.getIcon());
        text.setText(tab.getText());
        if (tab.getTag().equals("info")) {
            ImageView show = (ImageView) view.findViewById(R.id.tab_img_show);
            this.imageView = show;
            show.setImageResource(R.mipmap.dot);
        }
        return view;
    }

    /**
     * CityActivity返回处理
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Const.REQUEST_CITY_ACTIVITY_CODE) {
            if (resultCode == RESULT_OK) {
                this.city.setText(data.getStringExtra("cityName"));
                GeoPoint pos = LocationUtils.getGeoPointBystr(MainActivity.this, data.getStringExtra("cityName"));
                //Log.i("mms_poss", pos.toString());
                JSONObject pos_0 = new JSONObject();
                pos_0.put("lat", pos.getLatitudeE6());
                pos_0.put("lng", pos.getLongitudeE6());
                KeyValueTable.addObject("pos", pos_0);
            }
        }
    }

    /**
     *  获取位置
     */
    private void location() {
        LocationUtils.getLocationClient(getApplicationContext(), new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                city.setText(bdLocation.getCity());
                JSONObject pos = new JSONObject();
                pos.put("lat", bdLocation.getLatitude());
                pos.put("lng", bdLocation.getLongitude());
                KeyValueTable.addObject("pos", pos);
                Log.i("mms_l", bdLocation.getLatitude() + " - " + bdLocation.getLongitude());
            }
        });
    }
}
