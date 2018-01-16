package cn.wyh.bs.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.ActivityManager;
import cn.wyh.bs.activity.Login;
import cn.wyh.bs.adapter.ItemsAdapter;

public class TabPersonFragment extends Fragment {

    private TextView rt; //退出登录控件

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_person_fragment, container, false);

        initOne(); //设置上面头像
        initTwo(view); //设置下面列表

        this.rt = (TextView) view.findViewById(R.id.person_return);
        this.rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personReturn();
            }
        });
        return view;
    }

    private void initOne() {
    }

    /* 设置recyclerView */
    private void initTwo(View view) {
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.person_items);
        rv.removeAllViews();
        rv.removeAllViewsInLayout();
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(manager);
        ItemsAdapter adapter = new ItemsAdapter();
        rv.setAdapter(adapter);
    }

    /* 退出登录事件 */
    private void personReturn() {
        ActivityManager.finashAll();
        Intent intent = new Intent(this.getContext(), Login.class);
        startActivity(intent);
        return;
    }
}
