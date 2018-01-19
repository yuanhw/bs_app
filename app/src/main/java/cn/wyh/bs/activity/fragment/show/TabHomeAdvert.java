package cn.wyh.bs.activity.fragment.show;

/**
 * Created by WYH on 2017/12/24.
 *  首页广告栏设置
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.wyh.bs.R;

public class TabHomeAdvert {
    private ImageView mDefaultImage;
    private ViewPager mImagePager;
    private ImageView[] mImageViews;
    private AtomicInteger what;
    private boolean isContinue;
    private List<View> advPics;
    private Context mContext;
    private ImageView mImageView;
    private View contextView;
    private int touchFlag;
    float posX;
    float posY;

    public TabHomeAdvert(Context context, View contextView) {
        this.mContext = context;
        this.contextView = contextView;
    }
    public void exec() {
        this.what = new AtomicInteger(0);
        this.advPics = new ArrayList<>();
        this.isContinue = true;

        this.mDefaultImage = (ImageView) this.contextView.findViewById(R.id.home_default_image);
        this.mImagePager = (ViewPager) this.contextView.findViewById(R.id.adv_pager);

        initViewPager();
    }

    private void initViewPager() {
        mDefaultImage.setVisibility(View.GONE);
        mImagePager.setVisibility(View.VISIBLE);

        ImageView img1 = new ImageView(this.mContext);
        img1.setBackgroundResource(R.drawable.ad_img1);
        this.advPics.add(img1);

        ImageView img2 = new ImageView(this.mContext);
        img2.setBackgroundResource(R.drawable.ad_img2);
        this.advPics.add(img2);

        ImageView img3 = new ImageView(mContext);
        img3.setBackgroundResource(R.drawable.ad_img3);
        this.advPics.add(img3);

        ViewGroup group = (ViewGroup) this.contextView.findViewById(R.id.viewGroup);
        mImageViews = new ImageView[advPics.size()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(35, 35);
        layoutParams.setMargins(10, 0, 10, 0);

        for (int i = 0; i < advPics.size(); i++) {
            mImageView = new ImageView(this.mContext);
            mImageView.setLayoutParams(layoutParams);
            mImageViews[i] = mImageView;
            if (i == 0) {
                mImageViews[i].setBackgroundResource(R.drawable.dot_selected);
            }
            else {
                mImageViews[i].setBackgroundResource(R.drawable.dot_un);
            }
            group.addView(mImageViews[i]);
        }

        mImagePager.setAdapter(new AdvAdapter(advPics));
        mImagePager.addOnPageChangeListener(new GuidePageChangeListener());

        // 按下时不继续定时滑动,弹起时继续定时滑动
        mImagePager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchFlag = 1;
                        posX =  event.getX();
                        posY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        if (Math.abs(event.getX() - posX) > 3 || Math.abs(event.getY() - posY) > 3) {
                            touchFlag = 0;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        if (touchFlag == 1) {
                            //广告栏点击事件
                            Toast.makeText(mContext, "广告栏编号：" + mImagePager.getCurrentItem(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
        // 定时滑动线程
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        viewHandler.sendEmptyMessage(what.get());
                        whatOption();
                    }
                }
            }

        }).start();
    }

    /**
     * 操作圆点轮换变背景
     */
    private void whatOption() {
        what.incrementAndGet();
        if (what.get() > mImageViews.length - 1) {
            what.getAndAdd(-mImageViews.length);
        }
        try {
            if (what.get() == 1) {
                Thread.sleep(3000);
            }
            else {
                Thread.sleep(2000);
            }
        }
        catch (InterruptedException e) {
        }
    }

    /**
     * 处理定时切换广告栏图片的句柄
     */
    @SuppressLint("HandlerLeak")
    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            mImagePager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }
    };

    /** 指引页面改监听器 */
    private final class GuidePageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {}
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {}
        @Override
        public void onPageSelected(int arg0) {
            arg0 = arg0 % advPics.size();
            for (int i = 0; i < mImageViews.length; i++) {
                mImageViews[arg0].setBackgroundResource(R.drawable.dot_selected);
                if (arg0 != i) {
                    mImageViews[i].setBackgroundResource(R.drawable.dot_un);
                }
            }
            what.set(arg0);
        }
    }

    /**
     * 广告栏PaperView 图片适配器
     */
class AdvAdapter extends PagerAdapter {
        private List<View> views;

        public AdvAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }
}
