package cn.wyh.bs.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.bean.Item;

/**
 * Created by WYH on 2017/12/24.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private List<Item> items = new ArrayList<>(5);

    public ItemsAdapter() {
        this.init();
    }

    private void init() {
        Item item1 = new Item(1, R.mipmap.person_pos, "地址管理");
        items.add(item1);
        Item item2 = new Item(2, R.mipmap.operate, "操作说明");
        items.add(item2);
        Item item3 = new Item(3, R.mipmap.suggest, "意见反馈");
        items.add(item3);
        Item item4 = new Item(4, R.mipmap.person_about, "关于我们");
        items.add(item4);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView id = (TextView) v.findViewById(R.id.person_item_id);
                Log.i("mms_click", id.getText().toString());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.img.setImageResource(item.getImgId());
        holder.title.setText(item.getTitle());
        holder.id.setText(item.getId() + "");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView id, title;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.person_item_img);
            id = (TextView) itemView.findViewById(R.id.person_item_id);
            title = (TextView) itemView.findViewById(R.id.person_item_title);
        }
    }
}
