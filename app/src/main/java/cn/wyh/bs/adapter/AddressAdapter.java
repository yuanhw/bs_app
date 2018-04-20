package cn.wyh.bs.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.person.AddAddress;
import cn.wyh.bs.activity.person.AddressManager;
import cn.wyh.bs.bean.Address;
import cn.wyh.bs.bean.BlockPlantDto;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private AddressManager context;
    private List<Address> data;

    public AddressAdapter(AddressManager context, List<Address> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.del(holder.id.getText().toString());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Address obj = data.get(position);
        holder.id.setText("" + obj.getId());
        holder.tv1.setText("收货人：" + obj.getName());
        holder.tv2.setText(obj.getPhone());
        holder.tv3.setText("收货地址：" + obj.getAddress());
        if (obj.getSing() == 1) {
            holder.tv4.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View v1, v2;
        TextView tv1, tv2, tv3, tv4, id;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.name);
            tv2 = (TextView) itemView.findViewById(R.id.phone);
            tv3 = (TextView) itemView.findViewById(R.id.address);
            tv4 = (TextView) itemView.findViewById(R.id.sing);

            id = (TextView) itemView.findViewById(R.id.id);
            v1 = itemView.findViewById(R.id.edit);
            v2 = itemView.findViewById(R.id.del);
        }
    }
}
