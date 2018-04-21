package cn.wyh.bs.activity.plant.order.fragment;

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
import cn.wyh.bs.adapter.CaiAdapter1;
import cn.wyh.bs.adapter.CaiAdapter2;
import cn.wyh.bs.bean.CaiOneDto;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

/**
 * Created by WYH on 2018/4/20.
 */

public class CaiFragment2 extends Fragment {
    private CaiAdapter2 adapter;
    private List<CaiOneDto> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cai_has, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        adapter = new CaiAdapter2(this.getActivity(), data);
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
                param.add("status", "1");
                String resp = Global.httpPost3("/cai/app/loadCaiOrderByStatus.do", param.toString());
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                String data_1 = obj.getString("data");
                List<CaiOneDto> datas = JSONArray.parseArray(data_1, CaiOneDto.class);
                data.clear();
                data.addAll(datas);
                CaiFragment2.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
