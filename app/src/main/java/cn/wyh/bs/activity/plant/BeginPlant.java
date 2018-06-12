package cn.wyh.bs.activity.plant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
/**
 * Created by WYH on 2018/4/14.
 */

public class BeginPlant extends BaseActivity {
    private String blockId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_plant);
        blockId = getIntent().getStringExtra("blockId");
        ImageView begin = (ImageView) findViewById(R.id.begin);
        ImageView back = (ImageView) findViewById(R.id.back);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeginPlant.this, PlantActivity.class);
                intent.putExtra("blockId", blockId);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
