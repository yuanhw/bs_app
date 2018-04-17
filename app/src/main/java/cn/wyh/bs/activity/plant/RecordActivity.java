package cn.wyh.bs.activity.plant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.adapter.RecordAdapter;
import cn.wyh.bs.bean.TillageDto;

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
        Log.i("mms_s", plantId + " 123 ");
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
        List<String> img = new ArrayList<>();
        img.add("/tillageImg/1523977965726Chrysanthemum.jpg");
        img.add("/tillageImg/1523977965649Jellyfish.jpg");
        img.add("/tillageImg/1523977965302Hydrangeas.jpg");

        for (int i = 6; i < 10; i++) {
            TillageDto dto = new TillageDto();
            dto.setCreateTime(new Date());
            dto.setOperate("播种");
            dto.setStatus("良好");
            dto.setVideo("");
            dto.setImgList(img);
            data.add(dto);
        }

        adapter.notifyDataSetChanged();
    }
}
