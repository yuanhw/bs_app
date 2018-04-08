package cn.wyh.bs.activity.home.lease;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.activity.home.CityActivity;
import cn.wyh.bs.adapter.FarmAdapter2;
import cn.wyh.bs.bean.LateLySimplyFarm;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;

/**
 * Created by WYH on 2018/3/23.
 */

public class SearchFarmActivity extends BaseActivity {
    private RecyclerView rv;
    private SearchView searchView;
    private View view;
    private TextView tv, tv2;
    private ImageView back;
    private FarmAdapter2 adapter;
    private List<LateLySimplyFarm> farms = new ArrayList<>();
    private ImageView px;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lease_farm);
        rv = (RecyclerView) findViewById(R.id.lease_list);
        view = findViewById(R.id.top_main_place_1);
        tv = (TextView) findViewById(R.id.top_main_city_2);
        tv2 = (TextView) findViewById(R.id.tv2);
        searchView = (SearchView) findViewById(R.id.search_1);
        px = (ImageView) findViewById(R.id.show_px_1);
        back = (ImageView) findViewById(R.id.back_3);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("农场名");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String cityName = tv.getText().toString();
                //Log.i("mms_search", cityName + " , " + query);
                TableParam param = new TableParam();
                param.add("city", cityName);
                param.add("query", query);
                updateFarm(param.toString());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    initFarms();
                }
                return true;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchFarmActivity.this, CityActivity.class);
                intent.putExtra("cityName", tv.getText().toString());
                startActivityForResult(intent, Const.REQUEST_CITY_ACTIVITY_CODE);
            }
        });

        px.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = (String) v.getTag();
                if (tag.equals("降序")) {
                    v.setTag("升序");
                    px.setImageResource(R.mipmap.sx_1);
                } else {
                    v.setTag("降序");
                    px.setImageResource(R.mipmap.jx_1);
                }
                updatePX();
                //Log.i("mms_5", tag);
            }
        });
        px.setTag("降序");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new FarmAdapter2(this, farms);
        rv.setAdapter(adapter);
        initFarms();
    }

    private void updatePX() {
        Collections.reverse(farms);
        adapter.notifyDataSetChanged();
    }

    private void initFarms() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TableParam param = new TableParam();
                param.add("city", tv.getText().toString());
                JSONObject jsonObject = Global.httpPost2("/farm/loadSFarmByCity.do", param.toString());
                //Log.i("mms_rt", jsonObject.toJSONString());
                String respStr = jsonObject.getString("respStr");
                JSONObject resp = (JSONObject) JSON.parse(respStr);
                final List<LateLySimplyFarm> farmss = JSONArray.parseArray(resp.getString("data"), LateLySimplyFarm.class);
                //Log.i("mms_1", farmss.size() + "");
                farms.clear();
                farms.addAll(farmss);
                SearchFarmActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv2.setText("总共有" + farmss.size() + "条记录");
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
    public void updateFarm(final String param) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = Global.httpPost2("/farm/loadSFarm2.do", param);
                //Log.i("mms_2", jsonObject.toJSONString());
                String respStr = jsonObject.getString("respStr");
                JSONObject resp = (JSONObject) JSON.parse(respStr);
                final List<LateLySimplyFarm> farmss = JSONArray.parseArray(resp.getString("data"), LateLySimplyFarm.class);
                //Log.i("mms_1", farmss.size() + "");
                farms.clear();
                farms.addAll(farmss);
                SearchFarmActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv2.setText("总共有" + farmss.size() + "条记录");
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Const.REQUEST_CITY_ACTIVITY_CODE) {
            if (resultCode == RESULT_OK) {
                this.tv.setText(data.getStringExtra("cityName"));
                initFarms();
            }
        }
    }
}
