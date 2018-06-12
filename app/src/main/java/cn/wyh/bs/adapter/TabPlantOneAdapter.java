package cn.wyh.bs.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.bean.BlockPlantDto;
import cn.wyh.bs.bean.SelfBDetail;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class TabPlantOneAdapter extends RecyclerView.Adapter<TabPlantOneAdapter.ViewHolder> {
    private Activity context;
    private List<BlockPlantDto> data;

    public TabPlantOneAdapter(Activity context, List<BlockPlantDto> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_status_one_item, parent, false);
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

        Picasso.with(context).load(Global.BASE_URL + detail.getGreenImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv1, tv2, tv3, tv4;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.name);
            tv2 = (TextView) itemView.findViewById(R.id.plant_id);
            tv3 = (TextView) itemView.findViewById(R.id.block_id);
            tv4 = (TextView) itemView.findViewById(R.id.time);

            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
