package cn.wyh.bs.activity.person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.custom.CircleImageView;

/**
 * Created by WYH on 2018/1/16.
 */

public class PersonDetail extends BaseActivity {

    private ImageView w_back;
    private View w_item1, w_item2;
    private CircleImageView w_tou_img;
    private TextView w_name, w_phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail);

        this.w_back = (ImageView) findViewById(R.id.detail_back);
        this.w_item1 = findViewById(R.id.detail_item1);
        this.w_item2 = findViewById(R.id.detail_item2);
        this.w_tou_img = (CircleImageView) findViewById(R.id.detail_tou_img);
        this.w_name = (TextView) findViewById(R.id.detail_name);
        this.w_phone = (TextView) findViewById(R.id.detail_phone);

        this.w_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
