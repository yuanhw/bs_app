package cn.wyh.bs.activity.home.lease;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.ActivityManager;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.activity.MainActivity;

/**
 * Created by WYH on 2018/4/8.
 */

public class OrderPayStatus extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_pay_status);
        String str = getIntent().getStringExtra("str");
        TextView context = (TextView) findViewById(R.id.b_s_context);
        context.setText(str);
        TextView bt = (TextView) findViewById(R.id.to_main);
        bt.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderPayStatus.this, MainActivity.class);
                startActivity(intent);
                ActivityManager.finishAll();
            }
        });
    }
}
