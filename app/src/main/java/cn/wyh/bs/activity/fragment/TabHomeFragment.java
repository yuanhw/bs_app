package cn.wyh.bs.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.wyh.bs.R;
import cn.wyh.bs.adapter.FarmAdapter;
import cn.wyh.bs.entity.Farm;
import cn.wyh.bs.show.AdvertImg;
import cn.wyh.bs.show.Gv;

public class TabHomeFragment extends Fragment {

    private final int itemHeight = 363; //列表子项大小
    private List<Farm> farms = new ArrayList<>(); //列表数据

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home_fragment, container, false);

        //广告栏
        AdvertImg advertImg = new AdvertImg(this.getContext(), view.findViewById(R.id.tab_home_ad));
        advertImg.exec();

        //快捷菜单栏
        Gv gv = new Gv(this.getContext(), (GridView) view.findViewById(R.id.tab_home_gv));
        gv.exec();

        //初始化列表数据
        initFarms();

        //设置recyclerView
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.home_rv);
        rv.removeAllViews();
        rv.removeAllViewsInLayout();
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(manager);
        FarmAdapter adapter = new FarmAdapter(farms);
        rv.setAdapter(adapter);

        //不可滚动
        rv.setNestedScrollingEnabled(false);

        //设置长度
        rv.setMinimumHeight(this.itemHeight * this.farms.size());

        return view;
    }

    private void initFarms() {
        farms.clear();
        Random random = new Random(47);
        for (int i=1; i <= 20; i++) {
            Farm f = new Farm("天空农场"+i, "规格：20㎡、30㎡、1亩", "已有地主：" + (20 + i) + "人",
                    random.nextInt(1000) + "km", R.drawable.farm1);
            this.farms.add(f);
        }
    }
}