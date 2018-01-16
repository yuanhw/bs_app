package cn.wyh.bs.activity.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.ActivityManager;
import cn.wyh.bs.activity.Login;
import cn.wyh.bs.activity.person.PersonDetail;
import cn.wyh.bs.adapter.ItemsAdapter;

import static android.content.Context.MODE_PRIVATE;

public class TabPersonFragment extends Fragment {

    private TextView rt; //退出登录控件
    private ImageView tou_img; //头像控件
    private TextView name; //姓名控件

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_person_fragment, container, false);

        initOne(view); //设置上面头像
        initTwo(view); //设置下面列表
        return view;
    }

    private void initOne(View view) {
        this.tou_img = (ImageView) view.findViewById(R.id.person_tou_img);
        this.name = (TextView) view.findViewById(R.id.person_name);

        SharedPreferences editor = this.getContext().getSharedPreferences("user", MODE_PRIVATE);
        String phone = editor.getString("phone", "");
        if (!phone.equals("")) {
            this.name.setText(phone);
        }

        this.tou_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PersonDetail.class);
                startActivity(intent);
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
        SharedPreferences.Editor editor = this.getContext().getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putString("phone", "");
        editor.putString("password", "");
        editor.apply();
        ActivityManager.finashAll();
        Intent intent = new Intent(this.getContext(), Login.class);
        startActivity(intent);
        return;
    }
}
