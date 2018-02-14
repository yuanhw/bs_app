package cn.wyh.bs.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.adapter.FarmAdapter;
import cn.wyh.bs.activity.fragment.show.TabHomeAdvert;
import cn.wyh.bs.activity.fragment.show.TabHomeTable;
import cn.wyh.bs.bean.LateLySimplyFarm;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.storage.KeyValueTable;

/**
 *  首页tab
 */
public class TabHomeFragment extends Fragment {

    private final int itemHeight = 363; //列表子项大小
    private List<LateLySimplyFarm> farms = new ArrayList<>(); //列表数据
    private RecyclerView rv;
    private FarmAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.i("mms_v", "1234552145");
        View view = inflater.inflate(R.layout.tab_home_fragment, container, false);

        //广告栏
        TabHomeAdvert advertImg = new TabHomeAdvert(this.getContext(), view.findViewById(R.id.tab_home_ad));
        advertImg.exec();

        //快捷菜单栏
        TabHomeTable gv = new TabHomeTable(this.getContext(), (GridView) view.findViewById(R.id.tab_home_gv));
        gv.exec();

        //设置recyclerView
        rv = (RecyclerView) view.findViewById(R.id.home_rv);
        rv.removeAllViews();
        rv.removeAllViewsInLayout();
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(manager);
        //不可滚动
        rv.setNestedScrollingEnabled(false);
        //设置长度
        //rv.setMinimumHeight(this.itemHeight * this.farms.size());

        adapter = new FarmAdapter(this.getContext(),farms);
        rv.setAdapter(adapter);

        //初始化列表数据
        initFarms();
        return view;
    }

    /**
     *  加载附近农场信息
     */
    private void initFarms() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject param = KeyValueTable.getObject("pos", JSONObject.class);
                Log.i("mms_pa", param + " 666");
                if (param == null) {
                    param = new JSONObject();
                    param.put("lat", "39.916485");
                    param.put("lng", "116.403694");
                }
                JSONObject jsonObject = Global.httpPost("/farm/loadLateLyFarm.do", param.toJSONString());
                if (jsonObject.getInteger("code") == 1) {
                    String respStr = jsonObject.getString("respStr");
                    JSONObject resp = (JSONObject) JSON.parse(respStr);
                    //adapter.notifyItemRangeRemoved(0, farms.size());
                    List<LateLySimplyFarm> farmss = JSONArray.parseArray(resp.getString("data"), LateLySimplyFarm.class);
                    farms.clear();
                    farms.addAll(farmss);
                    //Log.i("farms_mms", farms.toString());
                    TabHomeFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            /*
                            adapter.notifyItemRangeInserted(0, farms.size());
                            */
                            rv.setMinimumHeight(itemHeight * farms.size());
                            adapter = new FarmAdapter(getContext(),farms);
                            rv.setAdapter(adapter);
                        }
                    });
                }
            }
        }).start();
    }
}