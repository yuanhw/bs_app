package cn.wyh.bs.activity.home.lease;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.adapter.BlockAdapter;
import cn.wyh.bs.bean.ShareOrderDto;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
import cn.wyh.bs.entity.BlockRuleShowList;
import cn.wyh.bs.storage.KeyValueTable;


/**
 * Created by WYH on 2018/3/29.
 */

public class RuleFarmActivity extends BaseActivity {
    private String id;
    private List<BlockRuleShowList> data;
    private BlockAdapter adapter;
    private RecyclerView list_rv;
    private List<String> s1_list, s2_list;
    private Spinner s1, s2, s3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rule_farm);

        ImageView back = (ImageView) findViewById(R.id.detail_back_3);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        //Log.i("mms_9", id);

        s1 = (Spinner) findViewById(R.id.spec_1);
        s2 = (Spinner) findViewById(R.id.spec_2);
        s3 = (Spinner) findViewById(R.id.spec_3);

        list_rv = (RecyclerView) findViewById(R.id.list_2);
        data = new ArrayList<>();
        adapter = new BlockAdapter(data, this);
        adapter.setActivity(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        list_rv.setLayoutManager(manager);
        list_rv.setAdapter(adapter);
        initListRV();

        initSpinner(s1, s2, s3);
        getSpinner(s1, s2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initListRV() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TableParam param = new TableParam();
                param.add("id", id);
                //Log.i("mms_param", param.toString());
                JSONObject jsonObject = Global.httpPost2("/block/loadRuleList.do", param.toString());
                String respStr = jsonObject.getString("respStr");
                JSONObject resp = (JSONObject) JSON.parse(respStr);
                KeyValueTable.addObject("rule_list", resp.getString("data"));
                List<BlockRuleShowList> list_s = JSONArray.parseArray(resp.getString("data"), BlockRuleShowList.class);
                if (list_s.size() == 0) {
                    Toast.makeText(RuleFarmActivity.this, "暂无数据", Toast.LENGTH_LONG).show();
                    return;
                }
                //Log.i("mms_12", jsonObject.toJSONString() + "@666@" + id);
                data.clear();
                data.addAll(list_s);
                RuleFarmActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void initSpinner(Spinner s1, Spinner s2, Spinner s3) {
        KeyValueTable.addObject("s1_v", "所有");
        KeyValueTable.addObject("s2_v", "所有");
        KeyValueTable.addObject("s3_v", "所有");

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = s1_list.get(position);
                KeyValueTable.updateObject("s1_v", key);
                String key2 = KeyValueTable.getObject("s2_v", String.class);
                String key3 = KeyValueTable.getObject("s3_v", String.class);
                String data_s = KeyValueTable.getObject("rule_list", String.class);
                List<BlockRuleShowList> list = JSONArray.parseArray(data_s, BlockRuleShowList.class);
                data.clear();
               flateData(key, key2, key3, list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key2 = s2_list.get(position);
                KeyValueTable.updateObject("s2_v", key2);
                String key = KeyValueTable.getObject("s1_v", String.class);
                String key3 = KeyValueTable.getObject("s3_v", String.class);
                String data_s = KeyValueTable.getObject("rule_list", String.class);
                List<BlockRuleShowList> list = JSONArray.parseArray(data_s, BlockRuleShowList.class);
                data.clear();
                flateData(key, key2, key3, list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final List<String> list3 = new ArrayList<String>();
        list3.add("所有");
        list3.add("仅自种");
        list3.add("可代种");
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(adapter3);
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key3 = list3.get(position);
                KeyValueTable.updateObject("s3_v", key3);
                String key2 = KeyValueTable.getObject("s2_v", String.class);
                String key = KeyValueTable.getObject("s1_v", String.class);
                String data_s = KeyValueTable.getObject("rule_list", String.class);
                List<BlockRuleShowList> list = JSONArray.parseArray(data_s, BlockRuleShowList.class);
                data.clear();
                flateData(key, key2, key3, list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private String converType(int code) {
        if (code == 0) {
            return "仅自种";
        }
        return "可代种";
    }
    private void getSpinner(final Spinner s1, final Spinner s2) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TableParam param = new TableParam();
                param.add("id", id);
                JSONObject jsonObject = Global.httpPost2("/block/getSpinnerP.do", param.toString());
                String respStr = jsonObject.getString("respStr");
                JSONObject resp = (JSONObject) JSON.parse(respStr);
                Map<String, List<String>> map = (Map<String, List<String>>) JSON.parseObject(resp.getString("data"), HashMap.class);
                //Log.i("mms_map", map.toString());
                s1_list = map.get("s2");
                s1_list.add(0, "所有");
                s2_list = map.get("s1");
                s2_list.add(0, "所有");
                final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(RuleFarmActivity.this, android.R.layout.simple_spinner_item, s1_list);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RuleFarmActivity.this, android.R.layout.simple_spinner_item, s2_list);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                RuleFarmActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        s1.setAdapter(adapter1);
                        s2.setAdapter(adapter2);
                    }
                });
            }
        }).start();
    }
    private void flateData(String key, String key2, String key3, List<BlockRuleShowList> list) {
        //Log.i("mms_test", key + key2 + key3);
        if (key.equals("所有") && key2.equals("所有") && key3.equals("所有") && list != null) {
            data.addAll(list);
        } else if (key.equals("所有") && !key2.equals("所有") && !key3.equals("所有")) {
            for (BlockRuleShowList item : list) {
                String max = item.getMaxLease() + "年";
                if (max.equals(key2) && converType(item.getType()).equals(key3))
                    data.add(item);
            }
        } else if (key.equals("所有") && key2.equals("所有") && !key3.equals("所有")) {
            for (BlockRuleShowList item : list) {
                //Log.i("mms_test2", item.getType() + "");
                if (converType(item.getType()).equals(key3)) {
                    data.add(item);
                }
            }
        } else if (key.equals("所有") && !key2.equals("所有") && key3.equals("所有")) {
            for (BlockRuleShowList item : list) {
                String max = item.getMaxLease() + "年";
                if (max.equals(key2))
                    data.add(item);
            }
        } else if (!key.equals("所有") && key2.equals("所有") && key3.equals("所有")) {
            for (BlockRuleShowList item : list) {
                if (item.getSpec().equals(key))
                    data.add(item);
            }
        } else if (!key.equals("所有") && !key2.equals("所有") && key3.equals("所有")) {
            for (BlockRuleShowList item : list) {
                String max = item.getMaxLease() + "年";
                if (max.equals(key2) && item.getSpec().equals(key))
                    data.add(item);
            }
        } else if (!key.equals("所有") && key2.equals("所有") && !key3.equals("所有")) {
            for (BlockRuleShowList item : list) {
                if (converType(item.getType()).equals(key3) && item.getSpec().equals(key))
                    data.add(item);
            }
        } else {
            for (BlockRuleShowList item : list) {
                String max = item.getMaxLease() + "年";
                if (converType(item.getType()).equals(key3) && item.getSpec().equals(key) && max.equals(key2))
                    data.add(item);
            }
        }
    }

    public void showD(final String batchNo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final TableParam param = new TableParam();
                param.add("batchNo", batchNo);
                String resp = Global.httpPost3("/block/getNumCan.do", param.toString());
                //Log.i("mms_4", resp + "2" + batchNo);
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                final int canNum = obj.getInteger("data");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.i("mms_n", resp + "");
                        AlertDialog.Builder dialog = new AlertDialog.Builder(RuleFarmActivity.this);
                        dialog.setMessage("可购买数量" + canNum + "个");
                        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (canNum > 0) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String resp = Global.httpPost3("/block/order/getShareOrder.do", param.toString());
                                            //Log.i("mms_r", resp + "1");
                                            JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                                            Intent intent = new Intent(RuleFarmActivity.this, OrderCreate.class);
                                            intent.putExtra("data", obj.getString("data"));
                                            startActivity(intent);
                                        }
                                    }).start();
                                }
                            }
                        });
                        dialog.show();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        final String key = (String) s1.getSelectedItem();
        final String key2 = (String) s2.getSelectedItem();
        final String key3 = (String) s3.getSelectedItem();
        new Thread(new Runnable() {
            @Override
            public void run() {
                TableParam param = new TableParam();
                param.add("id", id);
                JSONObject jsonObject = Global.httpPost2("/block/loadRuleList.do", param.toString());
                String respStr = jsonObject.getString("respStr");
                JSONObject resp = (JSONObject) JSON.parse(respStr);
                KeyValueTable.addObject("rule_list", resp.getString("data"));
                List<BlockRuleShowList> list_s = JSONArray.parseArray(resp.getString("data"), BlockRuleShowList.class);
                if (list_s.size() == 0) {
                    Toast.makeText(RuleFarmActivity.this, "暂无数据", Toast.LENGTH_LONG).show();
                    return;
                }
                flateData(key, key2, key3, list_s);
            }
        }).start();
    }
}
