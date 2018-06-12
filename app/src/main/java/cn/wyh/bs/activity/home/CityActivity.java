package cn.wyh.bs.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.adapter.CityAdapter;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.entity.City;

public class CityActivity extends BaseActivity {

    private String[] cityNames;
    private ListView listView;
    private TextView currentCity;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_actvity);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");

        this.listView = (ListView) findViewById(R.id.city_list);
        this.currentCity = (TextView) findViewById(R.id.city_current);
        this.back = (ImageView) findViewById(R.id.city_back);
        this.currentCity.setText(cityName);
        initCityListData();

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.list_city_name);
                Intent intent1 = new Intent();
                intent1.putExtra("cityName", textView.getText().toString());
                setResult(RESULT_OK, intent1);
                finish();
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     *  加载城市列表
     */
    private void initCityListData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = Global.httpPost("/const/loadCityList.do", null);
                if (jsonObject.getInteger("code") == 1) {
                    String resp = jsonObject.getString("respStr");
                    List<City> citys = JSON.parseArray(resp, City.class);
                    cityNames = new String[citys.size()];
                    int i = 0;
                    for (City c : citys) {
                        cityNames[i] = c.getValue();
                        i++;
                    }
                    CityActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CityAdapter adapter = new CityAdapter(CityActivity.this, R.layout.city_list_view, cityNames);
                            listView.setAdapter(adapter);
                        }
                    });
                }
            }
        }).start();
    }
}
