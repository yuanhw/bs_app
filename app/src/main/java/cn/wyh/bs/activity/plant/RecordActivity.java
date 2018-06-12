package cn.wyh.bs.activity.plant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.adapter.RecordAdapter;
import cn.wyh.bs.bean.TillageDto;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;

/**
 * Created by WYH on 2018/4/17.
 */

public class RecordActivity extends BaseActivity {
    List<TillageDto> data = new ArrayList<>();
    private RecordAdapter adapter;
    private String plantId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_list);
        plantId = getIntent().getStringExtra("plantId");
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        adapter = new RecordAdapter(this, data);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resp = Global.httpPost3("/plant/app/loadRecordList.do", "plantId=" + plantId);
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                String data_1 = obj.getString("data");
                List<TillageDto> list = JSONArray.parseArray(data_1, TillageDto.class);
                data.clear();
                data.addAll(list);
                RecordActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
