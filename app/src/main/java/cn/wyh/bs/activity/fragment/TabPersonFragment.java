package cn.wyh.bs.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.wyh.bs.R;
import cn.wyh.bs.adapter.FarmAdapter;
import cn.wyh.bs.adapter.ItemsAdapter;

public class TabPersonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_person_fragment, container, false);
        view.findViewById(R.id.person_items);
        Log.i("mms_Person", "123");
        initOne(); //设置上面头像
        initTwo(view); //设置下面列表
        return view;
    }

    private void initOne() {
    }

    private void initTwo(View view) {
        //设置recyclerView
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.person_items);
        rv.removeAllViews();
        rv.removeAllViewsInLayout();
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(manager);
        ItemsAdapter adapter = new ItemsAdapter();
        rv.setAdapter(adapter);
        rv.setNestedScrollingEnabled(false); //不可滚动
        //rv.setMinimumHeight(this.itemHeight * this.farms.size()); //设置长度
    }
}
