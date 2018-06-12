package cn.wyh.bs.activity.order;

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
import cn.wyh.bs.activity.order.fragment.TabOrderFragment5;
import cn.wyh.bs.bean.Tab;

/**
 * Created by WYH on 2018/4/8.
 */

public class OrderInfo extends FragmentActivity {
    //private FragmentTabHost mTabHost; //顶部tab控件

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        setContentView(R.layout.order_info);
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

    /**
     * 顶部tab
     */
    private void initTab() {
        Tab tab_home = new Tab("all", "已完成", 0, TabOrderFragment1.class);
        Tab tab_zc= new Tab("zc", "正常", 0, TabOrderFragment2.class);
        Tab tab_info = new Tab("dhx", "待核销", 0, TabOrderFragment3.class);
        Tab tab_share = new Tab("dpj", "待评价", 0, TabOrderFragment4.class);
        Tab tab_person = new Tab("tk", "退款", 0, TabOrderFragment5.class);

        List<Tab> list = new ArrayList<>();
        list.add(tab_home);
        list.add(tab_zc);
        list.add(tab_info);
        list.add(tab_share);
        list.add(tab_person);

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
        mTabHost.setCurrentTabByTag(tab_zc.getTag());
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
