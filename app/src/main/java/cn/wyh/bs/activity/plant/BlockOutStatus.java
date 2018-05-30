package cn.wyh.bs.activity.plant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;

/**
 * Created by WYH on 2018/5/31.
 */

public class BlockOutStatus extends BaseActivity {
    static String[] t1s = {"19℃", "24℃", "29℃", "35℃", "37℃", "39℃", "40℃"};
    static String[] t2s = {"35%", "55%", "69%", "75%", "85%", "90%", "20%"};
    static String[] t3s = {"1.5", "26", "3.6", "4.9", "5.2", "6.8", "6.0"};
    static String[] t4s = {"777.0mb", "1027.0mb", "869.0mb", "950.0mb", "1011.0mb", "1024.0mb", "1251.0mb"};
    static Random random = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.block_out_status);
        TextView t1 = (TextView) findViewById(R.id.p1);
        TextView t2 = (TextView) findViewById(R.id.p2);
        TextView t3 = (TextView) findViewById(R.id.p3);
        TextView t4 = (TextView) findViewById(R.id.p4);
        ImageView img = (ImageView) findViewById(R.id.img1);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        t1.setText(t1s[random.nextInt(7)]);
        t2.setText(t2s[random.nextInt(7)]);
        t3.setText(t3s[random.nextInt(7)]);
        t4.setText(t4s[random.nextInt(7)]);
    }
}
