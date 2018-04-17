package cn.wyh.bs.activity.plant;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.ActivityManager;
import cn.wyh.bs.activity.plant.fragment.TabPlantFragment1;
import cn.wyh.bs.activity.plant.fragment.TabPlantFragment2;
import cn.wyh.bs.activity.plant.fragment.TabStatusFragment1;
import cn.wyh.bs.activity.plant.fragment.TabStatusFragment2;
import cn.wyh.bs.bean.Tab;

/**
 * Created by WYH on 2018/4/16.
 */

public class PlantOperateStatus extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        setContentView(R.layout.plant_status);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initTab();
    }

    private void initTab() {
        Tab tab_self = new Tab("one", "待受理", 0, TabStatusFragment1.class);
        Tab tab_other = new Tab("two", "已受理", 0, TabStatusFragment2.class);
        List<Tab> list = new ArrayList<>();
        list.add(tab_self);
        list.add(tab_other);


        FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        /**
         * 设置tab页内容展示控件
         */
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_tab_content2);

        for (Tab tab : list) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tab.getTag());
            tabSpec.setIndicator(buildView(tab));
            mTabHost.addTab(tabSpec, tab.getMainFragment(), null);
        }

        /**
         * 当前tab
         */
        mTabHost.setCurrentTabByTag(tab_self.getTag());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    private View buildView(Tab tab) {
        View view = LayoutInflater.from(this).inflate(R.layout.plant_inflate, null);
        TextView text = (TextView) view.findViewById(R.id.o_tab);
        text.setText(tab.getText());
        return view;
    }
}
