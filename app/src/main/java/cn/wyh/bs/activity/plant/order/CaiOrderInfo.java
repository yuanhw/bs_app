package cn.wyh.bs.activity.plant.order;

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
import cn.wyh.bs.activity.order.fragment.TabOrderFragment1;
import cn.wyh.bs.activity.order.fragment.TabOrderFragment2;
import cn.wyh.bs.activity.order.fragment.TabOrderFragment3;
import cn.wyh.bs.activity.order.fragment.TabOrderFragment4;
import cn.wyh.bs.bean.Tab;

/**
 * Created by WYH on 2018/4/20.
 */

public class CaiOrderInfo extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        setContentView(R.layout.cai_order);
        ImageView back = (ImageView) findViewById(R.id.o_info_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initTab();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    private void initTab() {
        Tab a = new Tab("a", "已完成", 0, TabOrderFragment1.class);
        Tab b = new Tab("b", "待受理", 0, TabOrderFragment2.class);
        Tab c = new Tab("c", "已受理", 0, TabOrderFragment3.class);
        Tab d = new Tab("d", "已发货", 0, TabOrderFragment4.class);

        List<Tab> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);

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
        mTabHost.setCurrentTabByTag(b.getTag());
    }

    /**
     * 创建视图
     * @param tab
     * @return
     */
    private View buildView(Tab tab) {
        View view = LayoutInflater.from(this).inflate(R.layout.order_inflate, null);
        TextView text = (TextView) view.findViewById(R.id.o_tab);
        text.setText(tab.getText());
        return view;
    }
}
