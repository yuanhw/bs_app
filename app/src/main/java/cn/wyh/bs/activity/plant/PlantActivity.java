package cn.wyh.bs.activity.plant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.bean.CurrentStatus;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2018/4/12.
 */

public class PlantActivity extends BaseActivity implements View.OnClickListener{
    private String blockId;
    private int isHas;
    private TextView[] status = new TextView[4];
    private ImageView[] imgList = new ImageView[6];
    private View show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant);
        blockId = getIntent().getStringExtra("blockId");
        isHas = getIntent().getIntExtra("isHas", 0);
        /**
         *  获取控件
         */
        ImageView back = (ImageView) findViewById(R.id.plant_back);
        Button record = (Button) findViewById(R.id.record);
        show = findViewById(R.id.plant_g_s);
        /**
         * 种植状态控件
         */
        status[0] = (TextView) findViewById(R.id.name);
        status[1] = (TextView) findViewById(R.id.status);
        status[2] = (TextView) findViewById(R.id.day);
        status[3] = (TextView) findViewById(R.id.last_operate);
        /**
         * 6个相同蔬菜图片展示控件
         */
        imgList[0] = (ImageView) findViewById(R.id.block_img_1);
        imgList[1] = (ImageView) findViewById(R.id.block_img_2);
        imgList[2] = (ImageView) findViewById(R.id.block_img_3);
        imgList[3] = (ImageView) findViewById(R.id.block_img_4);
        imgList[4] = (ImageView) findViewById(R.id.block_img_5);
        imgList[5] = (ImageView) findViewById(R.id.block_img_6);
        /**
         * 底部三个按钮控件
         */
        ImageView[] btList = new ImageView[3];
        btList[0] =  (ImageView) findViewById(R.id.bt1);
        btList[1] =  (ImageView) findViewById(R.id.bt2);
        btList[2] =  (ImageView) findViewById(R.id.bt3);
        /**
         *  设置事件
         */
        back.setOnClickListener(this);
        record.setOnClickListener(this);
        btList[0].setOnClickListener(this);
        btList[1].setOnClickListener(this);
        btList[2].setOnClickListener(this);

        if (isHas == 1) {
            loadData();
        }
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resp = Global.httpPost3("/plant/app/currentPlantStatus.do", "blockId=" + blockId);
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                final CurrentStatus current = obj.getObject("data", CurrentStatus.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        status[0].setText(current.getName());
                        status[1].setText(current.getStatus());
                        if (current.getDay() != null) {
                            status[2].setText(current.getDay() + "天");
                        }
                        status[3].setText(current.getOperate());
                        show.setVisibility(View.VISIBLE);
                        String url = Global.BASE_URL + current.getImg();
                        for (ImageView tmp : imgList) {
                            Picasso.with(PlantActivity.this).load(url).into(tmp);
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        Integer code = Integer.parseInt(v.getTag().toString());
        switch (code) {
            case 1:
                if (isHas == 0) {
                    Intent intent = new Intent(PlantActivity.this, SelectPlantActivity.class);
                    intent.putExtra("blockId", blockId);
                    startActivityForResult(intent, 200);
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("您已种植~");
                    dialog.show();
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                finish();
                break;
            case 5:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200 && resultCode == RESULT_OK) {
            Log.i("mms_up", "更新");
        }
    }
}
