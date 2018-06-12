package cn.wyh.bs.activity.plant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.Login;
import cn.wyh.bs.adapter.TabOtherAdapter;
import cn.wyh.bs.adapter.TabSelfAdapter;
import cn.wyh.bs.bean.SelfBDetail;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

/**
 * Created by WYH on 2018/4/13.
 */

public class TabPlantFragment2 extends Fragment {
    private List<SelfBDetail> data = new ArrayList<>();
    private TabOtherAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.block_self, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.p_rv_1);
        adapter = new TabOtherAdapter(this.getActivity(), data);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        return view;
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TableParam param = new TableParam();
                int id = KeyValueTable.getObject("user", User.class).getId();
                param.add("userId", id + "");
                param.add("type", "1");
                String resp = Global.httpPost3("/block/app/loadBLock.do", param.toString());
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                String data_1 = obj.getString("data");
                List<SelfBDetail> datas = JSONArray.parseArray(data_1, SelfBDetail.class);
                data.clear();
                data.addAll(datas);
                TabPlantFragment2.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }
}
