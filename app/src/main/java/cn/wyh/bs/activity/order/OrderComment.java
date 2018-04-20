package cn.wyh.bs.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.activity.home.lease.OrderPayStatus;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;

/**
 * Created by WYH on 2018/4/10.
 */

public class OrderComment extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pingjia_order);
        String orderId = getIntent().getStringExtra("orderId");
        ImageView back = (ImageView) findViewById(R.id.back);
        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);
        final EditText content = (EditText) findViewById(R.id.area);
        Button bt = (Button) findViewById(R.id.submit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final TableParam param = new TableParam();
        param.add("orderId", orderId);
        param.add("rating", 0 + "");
        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //Log.i("mms_bar", rating + "@" + fromUser);
                int ratings = (int) rating;
                if (fromUser) {
                    param.add("rating", ratings + "");
                }
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = content.getText().toString();
                if (param.get("rating").equals("0")) {
                    Toast.makeText(OrderComment.this, "没有评级", Toast.LENGTH_SHORT).show();
                } else if (comment == null || comment.trim().equals("")) {
                    Toast.makeText(OrderComment.this, "评级不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    param.add("comment", comment);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String resp = Global.httpPost3("/block/order/orderComment.do", param.toString());
                            JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                            int data = obj.getInteger("data");
                            String str = "评价失败";
                            if (data == 1) {
                                str = "评价成功";
                            }
                            Intent intent = new Intent(OrderComment.this, OrderPayStatus.class);
                            intent.putExtra("str", str);
                            OrderComment.this.startActivity(intent);
                        }
                    }).start();
                }
            }
        });
    }
}
