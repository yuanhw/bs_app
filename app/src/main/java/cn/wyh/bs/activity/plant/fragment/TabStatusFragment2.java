package cn.wyh.bs.activity.plant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.adapter.TabPlantOneAdapter;
import cn.wyh.bs.adapter.TabPlantTwoAdapter;
import cn.wyh.bs.bean.BlockPlantDto;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

/**
 * Created by WYH on 2018/4/13.
 */

public class TabStatusFragment2 extends Fragment {
    private List<BlockPlantDto> data = new ArrayList<>();
    private TabPlantTwoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.block_self, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.p_rv_1);
        adapter = new TabPlantTwoAdapter(this.getActivity(), data);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        loadData();
        return view;
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TableParam param = new TableParam();
                int id = KeyValueTable.getObject("user", User.class).getId();
                param.add("userId", id + "");
                param.add("status", "2");
                String resp = Global.httpPost3("/plant/app/loadPlantStatusList.do", param.toString());
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                String data_1 = obj.getString("data");
                List<BlockPlantDto> datas = JSONArray.parseArray(data_1, BlockPlantDto.class);
                data.clear();
                data.addAll(datas);
                TabStatusFragment2.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
