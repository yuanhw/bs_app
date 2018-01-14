package cn.wyh.bs.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.wyh.bs.entity.Farm;
import cn.wyh.bs.R;
/**
 * Created by WYH on 2017/12/24.
 */

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.ViewHolder> {
    private List<Farm> farms;
    public FarmAdapter(List<Farm> farms) {
        this.farms = farms;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("mms_FarmAdapter_click", ((TextView) v.findViewById(R.id.item_farm_name)).getText().toString());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Log.i("mms_FarmAdapter_onBindViewHolder", position + "");
        Farm farm = farms.get(position);
        holder.farmImg.setImageResource(farm.getImgId());
        holder.tv1.setText(farm.getName());
        holder.tv2.setText(farm.getSpec());
        holder.tv3.setText(farm.getConsumers());
        holder.tv4.setText(farm.getDistance());
    }

    @Override
    public int getItemCount() {
        return farms.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView farmImg;
        TextView tv1, tv2, tv3, tv4;
        public ViewHolder(View itemView) {
            super(itemView);
            farmImg = (ImageView) itemView.findViewById(R.id.item_img_farm);
            tv1 = (TextView) itemView.findViewById(R.id.item_farm_name);
            tv2 = (TextView) itemView.findViewById(R.id.item_spec);
            tv3 = (TextView) itemView.findViewById(R.id.item_consumer_num);
            tv4 = (TextView) itemView.findViewById(R.id.item_distance);
        }
    }
}
