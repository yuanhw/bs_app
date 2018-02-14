package cn.wyh.bs.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.entity.Farm;

/**
 * Created by WYH on 2018/2/14.
 */

public class FarmDetailedActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farm_detail);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        ImageView back = (ImageView) findViewById(R.id.detail_back);
        final TextView fmTitle = (TextView) findViewById(R.id.f_detail_fmTitle);

        final ImageView fmImg = (ImageView) findViewById(R.id.fmImg);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Button bt = (Button) findViewById(R.id.f_detail_bt);
        final TextView fId = (TextView) findViewById(R.id.f_detail_id);

        final TextView address = (TextView) findViewById(R.id.f_detail_address);
        TextView map = (TextView) findViewById(R.id.f_detail_map);

        final TextView name = (TextView) findViewById(R.id.f_detail_name);
        final TextView phone = (TextView) findViewById(R.id.f_detail_phone);
        final TextView num = (TextView) findViewById(R.id.f_detail_num);
        final TextView key = (TextView) findViewById(R.id.f_detail_key);
        final TextView intro = (TextView) findViewById(R.id.f_detail_intro);
        TextView video = (TextView) findViewById(R.id.f_detail_video);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject param = new JSONObject();
                param.put("id", id);
                JSONObject jsonObject = Global.httpPost("/farm/loadFarmAppById.do", param.toJSONString());
                if (jsonObject.getInteger("code") == 1) {
                    String respStr = jsonObject.getString("respStr");
                    JSONObject resp = (JSONObject) JSON.parse(respStr);
                    final Farm fm = JSONObject.parseObject(resp.getString("data"), Farm.class);
                    //Log.i("mms_detail", respStr);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fmTitle.setText(fm.getFmTitle());
                            ratingBar.setRating(fm.getGrade() * 1.0f);
                            fId.setText(fm.getId() + "");
                            address.setText(fm.getFullAddress());
                            name.setText(fm.getContactName());
                            phone.setText(fm.getContactPhone());
                            num.setText(fm.getConsumerNum() + "");
                            key.setText(fm.getKeyVegetable());
                            intro.setText(fm.getFmIntroduce());
                            Picasso.with(FarmDetailedActivity.this).load(Global.BASE_URL + fm.getFmImg()).into(fmImg);
                        }
                    });
                }
            }
        }).start();
    }
}
