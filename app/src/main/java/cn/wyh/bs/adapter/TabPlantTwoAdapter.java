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
import cn.wyh.bs.bean.BlockPlantDto;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class TabPlantTwoAdapter extends RecyclerView.Adapter<TabPlantTwoAdapter.ViewHolder> {
    private Activity context;
    private List<BlockPlantDto> data;

    public TabPlantTwoAdapter(Activity context, List<BlockPlantDto> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_status_two_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BlockPlantDto detail = data.get(position);
        holder.tv1.setText(detail.getGreenTitle());
        holder.tv2.setText("种植编号：" + detail.getPlantId() + "");
        holder.tv3.setText("地块编号：" + detail.getBlockId() + "");
        holder.tv4.setText("生成时间：" + Global.convertDate(detail.getCreateTime(), Const.DATE_TIME_FORMAT));
        holder.tv5.setText("受理时间：" + Global.convertDate(detail.getUpdateTime(), Const.DATE_TIME_FORMAT));
        holder.tv6.setText("操作员：" + detail.getOperator());
        holder.tv7.setText("手机号：" + detail.getPhone());
        Picasso.with(context).load(Global.BASE_URL + detail.getGreenImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.name);
            tv2 = (TextView) itemView.findViewById(R.id.plant_id);
            tv3 = (TextView) itemView.findViewById(R.id.block_id);
            tv4 = (TextView) itemView.findViewById(R.id.time);

            tv5 = (TextView) itemView.findViewById(R.id.update_time);
            tv6 = (TextView) itemView.findViewById(R.id.operate);
            tv7 = (TextView) itemView.findViewById(R.id.phone);

            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
