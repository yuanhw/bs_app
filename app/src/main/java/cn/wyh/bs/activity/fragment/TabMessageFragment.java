package cn.wyh.bs.activity.fragment;

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
import cn.wyh.bs.adapter.InfoAdapter;
import cn.wyh.bs.bean.Info;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

/**
 *  消息tab
 */
public class TabMessageFragment extends Fragment {
    private List<Info> data = new ArrayList<>();
    private InfoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_info_fragment, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        adapter = new InfoAdapter(this.getActivity(), data);
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
                int id = KeyValueTable.getObject("user", User.class).getId();
                String resp = Global.httpPost3("/info/app/loadInfoList.do", "userId=" + id);
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                String data_1 = obj.getString("data");
                List<Info> datas = JSONArray.parseArray(data_1, Info.class);
                data.clear();
                data.addAll(datas);
                TabMessageFragment.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
