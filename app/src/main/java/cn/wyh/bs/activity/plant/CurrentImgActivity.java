package cn.wyh.bs.activity.plant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.adapter.RecordAdapter;
import cn.wyh.bs.bean.TillageDto;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2018/4/17.
 */

public class CurrentImgActivity extends BaseActivity {
    /*
    private String plantId;
    private ImageView[] imgs = new ImageView[3];
    */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_img);
        //plantId = getIntent().getStringExtra("plantId");
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /*
        imgs[0] = (ImageView) findViewById(R.id.img1);
        imgs[1] = (ImageView) findViewById(R.id.img2);
        imgs[2] = (ImageView) findViewById(R.id.img3);
        */
        //loadData();

        String path = "/tillageVideo/001.mp4";
        JZVideoPlayerStandard player1 = (JZVideoPlayerStandard) findViewById(R.id.video_player1);
        player1.setUp(path, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "耕种操作");

        JZVideoPlayerStandard player2 = (JZVideoPlayerStandard) findViewById(R.id.video_player2);
        player2.setUp(path, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "耕种操作");

        JZVideoPlayerStandard player3 = (JZVideoPlayerStandard) findViewById(R.id.video_player3);
        player3.setUp(path, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "耕种操作");
    }

    /*
    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resp = Global.httpPost3("/plant/app/loadTillageImgList.do", "plantId=" + plantId);
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                String data_1 = obj.getString("data");
                final List<String> list = JSONArray.parseArray(data_1, String.class);
                CurrentImgActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (list != null) {
                            int i = 0;
                            for (String src : list) {
                                imgs[i].setVisibility(View.VISIBLE);
                                Picasso.with(CurrentImgActivity.this).load(Global.BASE_URL + src).into(imgs[i]);
                                i++;
                            }
                        }
                    }
                });
            }
        }).start();
    }
    */
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
