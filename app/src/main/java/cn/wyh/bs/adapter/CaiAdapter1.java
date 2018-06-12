package cn.wyh.bs.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.plant.order.CaiOrderInfo;
import cn.wyh.bs.bean.CaiOneDto;
import cn.wyh.bs.bean.TabAllOrder;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class CaiAdapter1 extends RecyclerView.Adapter<CaiAdapter1.ViewHolder> {
    private Activity context;
    private List<CaiOneDto> data;

    public CaiAdapter1(Activity context, List<CaiOneDto> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cai_1_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CaiOneDto order = data.get(position);
        String url = Global.BASE_URL + order.getCaiImg();
        //异步加载图片列表
        Picasso.with(this.context).load(url).into(holder.caiImg);
        holder.tv1.setText(order.getName());
        holder.tv2.setText("采摘编号：" + order.getCaiId());
        holder.tv3.setText("种植编号：" + order.getPlantId());
        holder.tv4.setText("生成时间：" + Global.convertDate(order.getCreateTime(), Const.DATE_TIME_FORMAT));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView caiImg;
        TextView tv1, tv2, tv3, tv4;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.name);
            tv2 = (TextView) itemView.findViewById(R.id.cai_id);
            tv3 = (TextView) itemView.findViewById(R.id.plant_id);
            tv4 = (TextView) itemView.findViewById(R.id.time);
            caiImg = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
