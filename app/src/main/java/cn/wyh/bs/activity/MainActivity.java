package cn.wyh.bs.activity;

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

import cn.wyh.bs.R;
import cn.wyh.bs.bean.Tab;
import cn.wyh.bs.fragment.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{

    private FragmentTabHost mTabHost;
    private final List<Tab> list = new ArrayList<>(4);
    private final View[] views = new View[4];
    private ImageView imageView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbarContext();
        initTab();
    }

    private void initToolbarContext() {
        this.views[0] = View.inflate(this, R.layout.top_main, null);
        this.views[1] = View.inflate(this, R.layout.top_info, null);
        this.views[2] = View.inflate(this, R.layout.top_share, null);
        this.views[3] = View.inflate(this, R.layout.top_person, null);
        this.toolbar.addView(this.views[0]);

        View place = this.views[0].findViewById(R.id.top_main_place);
        final TextView city = (TextView) this.views[0].findViewById(R.id.top_main_city);
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("place", city.getText().toString());
                city.setText("嘉兴市");
            }
        });
    }

    private void initTab() {
        Tab tab_home = new Tab("home", "首页", R.drawable.tab_home_selector, TabHomeFragment.class);
        Tab tab_info = new Tab("info", "消息", R.drawable.tab_info_selector, TabMessageFragment.class);
        Tab tab_share = new Tab("share", "地主圈", R.drawable.tab_share_selector, TabShareFragment.class);
        Tab tab_person = new Tab("person", "我的", R.drawable.tab_person_selector, TabPersonFragment.class);

        list.add(tab_home);
        list.add(tab_info);
        list.add(tab_share);
        list.add(tab_person);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_tab_content);

        for (Tab tab : list) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tab.getTag());
            tabSpec.setIndicator(buildView(tab));
            mTabHost.addTab(tabSpec, tab.getMainFragment(), null);
        }

        mTabHost.setCurrentTabByTag(tab_home.getTag());

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.i("mms_MainActivity_tab", tabId);
                switch (tabId) {
                    case "home":
                        toolbar.removeAllViews();;
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

}
