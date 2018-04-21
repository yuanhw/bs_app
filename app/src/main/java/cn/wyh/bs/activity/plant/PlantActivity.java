package cn.wyh.bs.activity.plant;

import android.content.DialogInterface;
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
import cn.wyh.bs.activity.home.lease.OrderCreate;
import cn.wyh.bs.activity.home.lease.RuleFarmActivity;
import cn.wyh.bs.bean.CurrentStatus;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

/**
 * Created by WYH on 2018/4/12.
 */

public class PlantActivity extends BaseActivity implements View.OnClickListener{
    private String blockId;
    private int isHas;
    private TextView[] status = new TextView[4];
    private ImageView[] imgList = new ImageView[6];
    private View show;
    private int plantId;
    private CurrentStatus current;

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
                current = obj.getObject("data", CurrentStatus.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        plantId = current.getPlantId();
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
                if (isHas == 0 || "完成".equals(status[1].getText().toString())) {
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
                if (isHas == 0) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("您尚未种植~");
                    dialog.show();
                } else {
                    Intent intent = new Intent(PlantActivity.this, CurrentImgActivity.class);
                    intent.putExtra("plantId", plantId + "");
                    startActivity(intent);
                }
                break;
            case 3:
                if (current != null && current.getStatus().contains("成熟")) {
                    sendOrder();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("该状态不能采摘~");
                    dialog.show();
                }
                break;
            case 4:
                finish();
                break;
            case 5:
                Intent intent2 = new Intent(PlantActivity.this, RecordActivity.class);
                intent2.putExtra("plantId", plantId + "");
                startActivity(intent2);
                break;
        }
    }

    private void sendOrder() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TableParam param = new TableParam();
                param.add("plantId", plantId + "");
                String resp = Global.httpPost3("/cai/app/isHasCaiOrder.do", param.toString());
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                int i = obj.getInteger("data");
                if (i > 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(PlantActivity.this);
                            dialog.setMessage("您已发送过~");
                            dialog.show();
                        }
                    });
                } else if (i == 0) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int userId = KeyValueTable.getObject("user", User.class).getId();
                            String resp = Global.httpPost3("/cai/app/isHasDefaultAddress.do", "userId=" + userId);
                            JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                            int i = obj.getInteger("data");
                            if (i == 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder dialog = new AlertDialog.Builder(PlantActivity.this);
                                        dialog.setMessage("将发送采摘指令，采摘完成后发快递送达~");
                                        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        TableParam param = new TableParam();
                                                        param.add("plantId", plantId + "");
                                                        param.add("userId", KeyValueTable.getObject("user", User.class).getId() + "");
                                                        String resp = Global.httpPost3("/cai/app/createCaiOrder.do", param.toString());
                                                        JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                                                        int i = obj.getInteger("data");
                                                        if (i == 1) {
                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    AlertDialog.Builder dialog = new AlertDialog.Builder(PlantActivity.this);
                                                                    dialog.setMessage("指令发送成功~");
                                                                    dialog.show();
                                                                }
                                                            });
                                                        }
                                                    }
                                                }).start();
                                            }
                                        });
                                        dialog.show();
                                    }
                                });
                            } else if (i == 0) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder dialog = new AlertDialog.Builder(PlantActivity.this);
                                        dialog.setMessage("请先设置默认收货地址~");
                                        dialog.show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200 && resultCode == RESULT_OK) {
            loadData();
        }
    }
}
