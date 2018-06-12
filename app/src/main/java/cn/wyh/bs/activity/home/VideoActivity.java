package cn.wyh.bs.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;

/**
 * Created by WYH on 2018/4/16.
 */

public class VideoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);
        String path = getIntent().getStringExtra("videoPath");
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        JZVideoPlayerStandard player = (JZVideoPlayerStandard) findViewById(R.id.video_player);
        player.setUp(path, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "农场介绍");
    }
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
