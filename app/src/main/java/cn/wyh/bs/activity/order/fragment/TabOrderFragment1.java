package cn.wyh.bs.activity.order.fragment;

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
import cn.wyh.bs.adapter.BlockAdapter;
import cn.wyh.bs.adapter.EndLessOnScrollListener;
import cn.wyh.bs.adapter.TabAllAdapter1;
import cn.wyh.bs.bean.TabAllOrder;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

/**
 * Created by WYH on 2018/4/8.
 */

public class TabOrderFragment1 extends Fragment {
    private RecyclerView rv;
    private List<TabAllOrder> data = new ArrayList<>();
    private TabAllAdapter1 adapter;
    private View show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.o_all_f, container, false);
        rv = (RecyclerView) view.findViewById(R.id.o_rv_1);
        show = view.findViewById(R.id.showTip);
        adapter = new TabAllAdapter1(this.getContext(), data);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        /*
        rv.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {

            }
        });
        */
        initRv();
        return view;
    }

    private void initRv() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TableParam param = new TableParam();
                param.add("userId", KeyValueTable.getObject("user", User.class).getId() + "");
                String resp = Global.httpPost3("/block/order/getAllOrderSInfo.do", param.toString());
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                String data_1 = obj.getString("data");
                List<TabAllOrder> list = JSONArray.parseArray(data_1, TabAllOrder.class);
                data.addAll(list);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
