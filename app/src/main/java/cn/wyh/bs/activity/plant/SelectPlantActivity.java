package cn.wyh.bs.activity.plant;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.adapter.PlantAdapter;
import cn.wyh.bs.bean.Green;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;

/**
 * Created by WYH on 2018/4/12.
 */

public class SelectPlantActivity extends BaseActivity {
    private List<Green> data = new ArrayList<>();
    private ImageView currentImg;
    private TextView currentName;
    private  PlantAdapter adapter;
    private Green green;
    private String blockId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_plant);
        blockId = getIntent().getStringExtra("blockId");
        ImageView back = (ImageView) findViewById(R.id.back);
        currentImg = (ImageView) findViewById(R.id.sel_img);
        currentName = (TextView) findViewById(R.id.sel_name);
        TextView submit = (TextView) findViewById(R.id.plant_submit);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_sel);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        adapter = new PlantAdapter(this, data);
        rv.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setResult(RESULT_OK);
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SelectPlantActivity.this);
                dialog.setMessage("您即将种植 " + green.getTitle() + " ?");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                TableParam param = new TableParam();
                                param.add("blockId", blockId);
                                param.add("greenId", green.getId() + "");
                                String resp = Global.httpPost3("/plant/app/addPlantOrder.do", param.toString());
                                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                                int code = obj.getInteger("data");
                                if (code == 1) {
                                    setResult(RESULT_OK);
                                    finish();
                                } else {
                                    Toast.makeText(SelectPlantActivity.this, "操作失败", Toast.LENGTH_LONG);
                                }
                            }
                        }).start();
                    }
                });
                dialog.show();
            }
        });
        loadData();
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resp = Global.httpPost3("/plant/app/loadGreenList.do", "token=wyh");
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                List<Green> data1 = JSON.parseArray(obj.getString("data"), Green.class);
                data.clear();
                data.addAll(data1);
                SelectPlantActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    public void updateSelected(String id) {
        for (Green tmp : data) {
            if ((tmp.getId() + "").equals(id)) {
                green = tmp;
                break;
            }
        }
        String url = Global.BASE_URL + green.getImg();
        Picasso.with(this).load(url).into(currentImg);
        currentName.setText(green.getTitle());
    }
}
