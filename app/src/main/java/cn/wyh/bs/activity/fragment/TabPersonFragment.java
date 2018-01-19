package cn.wyh.bs.activity.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.ActivityManager;
import cn.wyh.bs.activity.Login;
import cn.wyh.bs.activity.person.PersonDetail;
import cn.wyh.bs.adapter.ItemsAdapter;
import cn.wyh.bs.common.ImgProcess;
import cn.wyh.bs.common.Status;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

public class TabPersonFragment extends Fragment {

    private TextView rt; //退出登录控件
    private ImageView tou_img; //头像控件
    private TextView name; //姓名控件
    private TextView account; // 余额控件
    private View reCharge; //充值控件

    private Uri uri;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_person_fragment, container, false);

        initOne(view); //设置上面视图
        initTwo(view); //设置下面列表

        initData();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //Log.i("mms____", "onStart");
        if (Status.isImgUpdate) {
            Bitmap bm = BitmapFactory.decodeFile(uri.getPath());
            this.tou_img.setImageBitmap(bm);
            Status.isImgUpdate = false;
        }
    }

    private void initData() {
        User user = KeyValueTable.getObject("user", User.class);
        this.name.setText(user.getUserName());
        this.account.setText(user.getAccount() + "");
        String[] imgName = user.getTouImgPath().split("/");
         uri= ImgProcess.getImgPath(imgName[imgName.length - 1]);
        //Log.i("mms_initData", uri.toString());
        this.tou_img.setImageURI(uri);
    }

    private void initOne(View view) {
        this.tou_img = (ImageView) view.findViewById(R.id.person_tou_img);
        this.name = (TextView) view.findViewById(R.id.person_name);
        this.account = (TextView) view.findViewById(R.id.person_account);
        this.reCharge = view.findViewById(R.id.person_recharge);

        this.tou_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PersonDetail.class);
                startActivity(intent);
            }
        });

        this.reCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TabPersonFragment.this.getContext(), "充值", Toast.LENGTH_LONG);
            }
        });
    }

    /* 设置recyclerView */
    private void initTwo(View view) {
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.person_items);
        rv.removeAllViews();
        rv.removeAllViewsInLayout();
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(manager);
        ItemsAdapter adapter = new ItemsAdapter(this.getContext());
        rv.setAdapter(adapter);

        this.rt = (TextView) view.findViewById(R.id.person_return);
        this.rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personReturn();
            }
        });
    }

    /* 退出登录事件 */
    private void personReturn() {
        KeyValueTable.removeObject("user");
        ActivityManager.finashAll();
        Intent intent = new Intent(this.getContext(), Login.class);
        startActivity(intent);
        return;
    }
}
